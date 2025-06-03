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
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Lending(Customer customer, Book book, LocalDate lendingDate, boolean returned) {
        this.lendingDate = lendingDate;
        this.returned = returned;
        this.book=book;
        this.customer=customer;
    }

    public Lending() { }

    public void setId(Integer id) {
        this.id = id;
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


    public LocalDate getLendingDate() {
        return lendingDate;
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
