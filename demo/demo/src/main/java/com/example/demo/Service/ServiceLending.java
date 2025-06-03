package com.example.demo.Service;

import com.example.demo.ModelsEntity.Lending;
import com.example.demo.Repository.RepLending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLending {

    RepLending rep;

    @Autowired
    public ServiceLending(RepLending rep){this.rep=rep;}

    public List<Lending> findAll() {return (List<Lending>) rep.findAll();}


}
