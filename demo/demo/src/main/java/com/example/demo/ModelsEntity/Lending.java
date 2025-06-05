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
    private LocalDate lendingDate;
    private boolean returned;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;

    public Lending(Customer customer, Book book) {
        this.lendingDate = LocalDate.now();
        this.returned = false;
        this.book=book;
        this.customer=customer;
    }

    public Lending() { }

    public void setLendingDate(LocalDate lendingDate) {
        this.lendingDate = lendingDate;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Integer getBookId() {
        return id;
    }

    public LocalDate getLendingDate() {
        return lendingDate;
    }

    public Integer getId() {
        return id;
    }

    public boolean isReturned() {
        return returned;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

}
