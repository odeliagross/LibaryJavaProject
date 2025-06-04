package com.example.demo.Controller;

import com.example.demo.DTO.LendingDTO;
import com.example.demo.ModelsEntity.Lending;
import com.example.demo.Service.ServiceCustomer;
import com.example.demo.Service.ServiceLending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.chrono.IsoEra;
import java.util.List;

@RestController
public class ControllerLending {

ServiceLending service;

    @Autowired
    public ControllerLending(ServiceLending service){this.service=service;}

    @GetMapping("/getAllLendings/")
    public List<LendingDTO> getAllLendings(){return service.findAll();}

    @GetMapping("/getAllCustomerLendings/{id}")
    public List<LendingDTO> getAllustomerLendings(@PathVariable String id){return service.getAllustComerLendings(id);}

    @GetMapping("/getAllInfoFromLending/")
    public List<Lending> getAllInfoFromLending(){return service.getAllInfoFromLending();}

    @GetMapping("/getFine/{id}")
    public double getFine(@PathVariable String id){return service.getFine(id);}

    @PostMapping("/addLending/")
    public boolean addLending(@RequestBody LendingDTO lending){return service.addLending(lending);}

    @PatchMapping("/updateReturned/{id}")
    public boolean updateReturned(@PathVariable Integer id){return service.updateReturned(id);}






}
