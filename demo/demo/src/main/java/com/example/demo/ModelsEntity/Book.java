package com.example.demo.ModelsEntity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String authorId;
    private String bookName;
    private LocalDate publishDate;

    @OneToMany (mappedBy = "book")
    List<Lending> lendingList;

    @Version
    private Integer version;

    public Book() {}

    public Book(String authorId,String bookName,LocalDate publishDate)
    {
        this.authorId=authorId;
        this.bookName=bookName;
        this.publishDate=publishDate;
    }


    public Integer getId() {
        return id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
