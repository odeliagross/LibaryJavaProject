package com.example.demo.Service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.CustomerDTO;
import com.example.demo.DTO.LendingDTO;
import com.example.demo.ModelsEntity.Book;
import com.example.demo.ModelsEntity.Customer;
import com.example.demo.ModelsEntity.Lending;
import com.example.demo.Repository.RepBook;
import com.example.demo.Repository.RepCostumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ServiceCustomer {


    RepCostumer rep;

    @Autowired
    public ServiceCustomer(RepCostumer rep){this.rep=rep;}

    //שליפת כל הלקוחות
    public List<CustomerDTO> findAll() {
        return ((List<Customer>) rep.findAll()).stream()
                .map(this::mapToDTO)
                .toList();
    }

    //הוספת לקוח
    public boolean addCustomer(Customer customer)
    {
        if(customer.getId()==null)
            return false;
        if(customer.getId().length()!=9)
            return false;
        for(Customer cos:(List<Customer>) rep.findAll())
        {
            if(cos.getId().equals(customer.getId()))
                return false;
        }
        rep.save(customer);
        return true;
    }

    //קבלת לקוח לפי תעודת זהות
    public CustomerDTO getCustomerById(String id)
    {
        for(Customer cos:(List<Customer>) rep.findAll())
        {
            if(cos.getId().equals(id))
                return mapToDTO(cos);
        }
        return null;
    }

    //מיפוי
    private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setPhone(customer.getPhone());
        List<LendingDTO> lendings = new ArrayList<LendingDTO>();
        if (customer.getLendingList() != null) {
            for (Lending lending : customer.getLendingList()) {
                if (lending.getBook() != null) {
                    LendingDTO ld=new LendingDTO();
                    ld.setCustomerId(lending.getCustomer().getId());
                    ld.setReturned(lending.isReturned());
                    ld.setBookId(lending.getBookId());
                    ld.setLendingDate(lending.getLendingDate());
                    lendings.add(ld);
                }
            }
        }
        dto.setLendingList(lendings);
        return dto;
    }


}
