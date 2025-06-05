package com.example.demo.Controller;

import com.example.demo.DTO.BookDTO;
import com.example.demo.ModelsEntity.Book;
import com.example.demo.Service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ControllerBook {

    ServiceBook service;

    @Autowired
    public ControllerBook(ServiceBook service){this.service=service;}


    @GetMapping("/getAllBooks/")
    public List<BookDTO> getAll()
    {
        return service.findAll();
    }

    @PostMapping("/addBook/")
        public boolean add(@RequestBody Book book) {
        return service.add(book);
    }

    @GetMapping("/getBookById/{id}")
    public BookDTO getBookById(@PathVariable Integer id){return service.findById(id);}

    @PatchMapping("/updateBook/{id}")
    public boolean update(@PathVariable Integer id, @RequestBody Map<String,String> name) {return service.update(id,name.get("bookName")); }

    @GetMapping("/findBookByName/{name}")
    public List<BookDTO> findByName(@PathVariable String name){return service.findByName(name);}

    @GetMapping("/findBookByYear/{year}")
    public List<BookDTO> findByName(@PathVariable int year){return service.findByYear(year);}
}

