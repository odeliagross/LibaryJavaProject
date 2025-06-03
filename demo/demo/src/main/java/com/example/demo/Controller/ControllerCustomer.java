package com.example.demo.Controller;

import com.example.demo.ModelsEntity.Customer;
import com.example.demo.Service.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerCustomer {

    ServiceCustomer service;

    @Autowired
    public ControllerCustomer(ServiceCustomer service){this.service=service;}

    @GetMapping("/getCustomerById/{id}")
    public Customer getCostumerById(@PathVariable String id){return service.getCustomerById(id);}

    @GetMapping("/getAllCostumers/")
    public List<Customer> getAllCostumers(){return service.findAll();}

    @PostMapping("/addCostumer/")
    public boolean addCostumer(@RequestBody Customer customer){return service.addCustomer(customer);}
}
