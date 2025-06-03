package com.example.demo.Repository;

import com.example.demo.ModelsEntity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepBook extends CrudRepository<Book,Integer> {
}
