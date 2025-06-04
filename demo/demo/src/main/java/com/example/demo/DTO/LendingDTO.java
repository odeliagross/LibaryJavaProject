package com.example.demo.DTO;

import com.example.demo.ModelsEntity.Book;
import com.example.demo.ModelsEntity.Customer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class LendingDTO {

    private String customerId;
    private Integer bookId;
    private LocalDate lendingDate;
    private boolean returned;



    public void setLendingDate(LocalDate lendingDate) {
        this.lendingDate = lendingDate;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public LocalDate getLendingDate() {
        return lendingDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public LendingDTO(){}


    public String getCustomerId() {
        return customerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public LendingDTO(String customerId, Integer bookId) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.lendingDate = LocalDate.now();
        this.returned = false;
    }
}
