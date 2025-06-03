package com.example.demo.Repository;

import com.example.demo.ModelsEntity.Lending;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepLending extends CrudRepository<Lending,Integer> {
}
