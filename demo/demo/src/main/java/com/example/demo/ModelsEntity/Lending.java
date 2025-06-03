package com.example.demo.ModelsEntity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table
public class Lending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerId;
    private Integer bookId;
    private LocalDate lendingDate;
    private boolean returned;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    public Lending(String customerId, Integer bookId, LocalDate lendingDate, boolean returned) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.lendingDate = lendingDate;
        this.returned = returned;
    }

    public Lending() { }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setLendingDate(LocalDate lendingDate) {
        this.lendingDate = lendingDate;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Integer getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public LocalDate getLendingDate() {
        return lendingDate;
    }

    public boolean isReturned() {
        return returned;
    }
}
