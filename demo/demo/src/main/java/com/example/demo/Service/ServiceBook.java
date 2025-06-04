package com.example.demo.Service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.ModelsEntity.Book;
import com.example.demo.ModelsEntity.Lending;
import com.example.demo.Repository.RepBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceBook{

    RepBook rep;

    @Autowired
    public ServiceBook(RepBook rep){this.rep=rep;}

    public List<BookDTO> findAll() {
        return ((List<Book>) rep.findAll()).stream()
                .map(this::mapToDTO)
                .toList();
    }


    public boolean add(Book b)
    {
        if (b.getPublishDate().isAfter(LocalDate.now()))
            return false;
        if(b.getAuthorId().length()!=9)
            return false;
//        for (Book book : (List<Book>) rep.findAll()) {
//            if(book.getBookName().equals(b.getBookName()))
//                if(!book.getPublishDate().equals(b.getPublishDate()) ||
//                        !book.getAuthorId().equals(b.getAuthorId()))
//                    return false;
//        }
        b.setId(null);
        rep.save(b);
        return true;
    }

    public boolean delete(Book book)
    {
        if(!rep.existsById(book.getId()))
            return false;
        rep.deleteById(book.getId());
        return true;
    }

    public BookDTO findById(Integer id)
    {
        for (Book book : (List<Book>) rep.findAll()) {
            if (book.getId().equals(id))
                return mapToDTO(book);
        }
        return null;
    }

    public boolean update(Integer id, Book book)
    {
        Optional<Book> optBook = rep.findById(id);
        if(optBook.isEmpty())
            return false;
        Book foundBook=optBook.get();
        foundBook.setBookName(book.getBookName());
        rep.save(foundBook);
        return true;
    }

    public List<BookDTO> findByName(String bookName)
    {
        List<BookDTO> found=new ArrayList<BookDTO>();
        for(Book book:((List<Book>)rep.findAll()))
        {
            if(book.getBookName().equals(bookName))
                found.add(mapToDTO(book));
        }
        return found;
    }

    public List<BookDTO> findByYear(int year)
    {
        List<BookDTO> found=new ArrayList<BookDTO>(
        );
        for(Book book:((List<Book>)rep.findAll()))
        {
            if(book.getPublishDate()!=null)
            if(book.getPublishDate().getYear()==(year))
                found.add(mapToDTO(book));
        }
        return found;
    }

    //מיפוי
    private BookDTO mapToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setBookName(book.getBookName());
        dto.setPublishDate(book.getPublishDate());
        List<LocalDate> lendingDates = new ArrayList<>();
        if (book.getLendingList() != null) {
            dto.setLendingList(
                    book.getLendingList().stream()
                            .map(Lending::getLendingDate)
                            .filter(ld -> ld != null)
                            .toList());
        } else {
            dto.setLendingList(new ArrayList<>());}
        return dto;
    }


}

