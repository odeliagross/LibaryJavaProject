package com.example.demo.Service;

import com.example.demo.ModelsEntity.Customer;
import com.example.demo.Repository.RepCostumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCustomer {


    RepCostumer rep;

    @Autowired
    public ServiceCustomer(RepCostumer rep){this.rep=rep;}

    //שליפת כל הלקוחות
    public List<Customer> findAll() {return (List<Customer>) rep.findAll();}

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
    public Customer getCustomerById(String id)
    {
        for(Customer cos:(List<Customer>) rep.findAll())
        {
            if(cos.getId().equals(id))
                return cos;
        }
        return null;
    }


}
