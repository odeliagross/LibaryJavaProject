package com.example.demo.Controller;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.ModelsEntity.Customer;
import com.example.demo.Service.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // או 5173, תלוי בפורט של הקליינט שלך
@RestController
public class ControllerCustomer {

    ServiceCustomer service;

    @Autowired
    public ControllerCustomer(ServiceCustomer service){this.service=service;}

    @GetMapping("/getCustomerById/{id}")
    public CustomerDTO getCustomerById(@PathVariable String id){return service.getCustomerById(id);}

    @GetMapping("/getAllCustomers/")
    public List<CustomerDTO> getAllCustomers(){return service.findAll();}

    @PostMapping("/addCustomer/")
    public boolean addCustomer(@RequestBody Customer customer){return service.addCustomer(customer);}
}
