package com.example.demo.Service;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.DTO.LendingDTO;
import com.example.demo.ModelsEntity.Book;
import com.example.demo.ModelsEntity.Customer;
import com.example.demo.ModelsEntity.Lending;
import com.example.demo.Repository.RepBook;
import com.example.demo.Repository.RepCostumer;
import com.example.demo.Repository.RepLending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceLending {

    RepLending repLending;
    RepBook repBook;
    RepCostumer repCustomer;

    @Autowired
    public ServiceLending(RepLending repLending,RepBook repBook, RepCostumer repCustomer)
    {
        this.repLending=repLending;
        this.repBook=repBook;
        this.repCustomer=repCustomer;
    }

    public List<LendingDTO> findAll() {
        return ((List<Lending>) repLending.findAll()).stream()
                .map(this::mapToDTO)
                .toList();
    }
    public boolean addLending(LendingDTO dto)
    {
        if(dto.getCustomerId()==null || dto.getBookId()==null)
            return false;
        Optional<Customer> customerOpt = repCustomer.findById(dto.getCustomerId());
        Optional<Book> bookOpt = repBook.findById(dto.getBookId());

        if (customerOpt.isEmpty() || bookOpt.isEmpty()) {
            return false;
        }
        //בדיקה האם הספר כבר בהשאלה
        boolean bookAlreadyLent = ((List<Lending>) repLending.findAll()).stream()
                .anyMatch(l -> l.getBook().getId().equals(dto.getBookId()) && !l.isReturned());
        System.out.println("exist?"+bookAlreadyLent);
        if (bookAlreadyLent) return false;

        Lending lending = new Lending();
        lending.setCustomer(customerOpt.get());
        lending.setBook(bookOpt.get());
        lending.setLendingDate(LocalDate.now());
        lending.setReturned(false);
        bookOpt.get().getLendingList().add(lending);
          repLending.save(lending);
        return true;
    }

    public boolean updateReturned(Integer id)
    {
        Optional<Lending> optLending = repLending.findById(id);
        if(optLending.isEmpty())
            return false;
        Lending foundLending=optLending.get();
        foundLending.setReturned(true);
        repLending.save(foundLending);
        return true;
    }

    public List<LendingDTO> getAllustComerLendings(String id)
    {
        List<LendingDTO> lendings=new ArrayList<LendingDTO>();
        for (Lending ld : (List<Lending>) repLending.findAll()) {
            Customer customer = ld.getCustomer();
            if (customer != null && customer.getId().equals(id)) {
                lendings.add(mapToDTO(ld));
            }
        }
        return lendings;
    }

    public List<Lending> getAllInfoFromLending()
    {
        return ((List<Lending>) repLending.findAll());
    }

    public double getFine(String id)
    {
        List<LendingDTO> cusLending=getAllustComerLendings(id);
        double fine=0;
        for(LendingDTO l: cusLending) {
            if (l.getLendingDate().plusDays(14).isBefore(LocalDate.now()))
                fine += (l.getLendingDate().until(LocalDate.now(), ChronoUnit.DAYS) - 14) * 0.5;
        }
        return fine;
    }

    //מיפוי
    private LendingDTO mapToDTO(Lending lending) {
        LendingDTO dto = new LendingDTO();
        dto.setLendingDate(lending.getLendingDate());
        dto.setReturned(lending.isReturned());

        if (lending.getBook() != null) {
            dto.setBookId(lending.getBook().getId());
        } else {
            dto.setBookId(-1);
        }
        if (lending.getCustomer() != null) {
            dto.setCustomerId(
                    lending.getCustomer().getId());
        } else {
            dto.setCustomerId("---------");
        }
        return dto;
    }


}
