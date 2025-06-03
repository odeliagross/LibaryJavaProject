package com.example.demo.Controller;

import DTO.BookDTO;
import com.example.demo.ModelsEntity.Book;
import com.example.demo.Service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerBook {

    ServiceBook service;

    @Autowired
    public ControllerBook(ServiceBook service){this.service=service;}


    @GetMapping("/getAllBooks/")
    public List<Book> getAll()
    {
        return service.findAll();
    }

    @PostMapping("/addBook/")
        public boolean add(@RequestBody Book book) {
        return service.add(book);
    }

    @DeleteMapping("/deleteBook/")
    public boolean delete(@RequestBody Book book) {
        return service.delete(book);
    }

    @GetMapping("/getBookById/{id}")
    public Book getBookById(@PathVariable Integer id){return service.findById(id);}

    @PatchMapping("/updateBook/{id}")
    public boolean update(@PathVariable Integer id, @RequestBody Book book) {return service.update(id,book); }

    @GetMapping("/findBookByName/{name}")
    public List<BookDTO> findByName(@PathVariable String name){return service.findByName(name);}

    @GetMapping("/findBookByYear/{year}")
    public List<BookDTO> findByName(@PathVariable int year){return service.findByYear(year);}
}

