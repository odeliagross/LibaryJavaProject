package com.example.demo.Repository;

import com.example.demo.ModelsEntity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepCostumer extends CrudRepository<Customer,String>{
}
