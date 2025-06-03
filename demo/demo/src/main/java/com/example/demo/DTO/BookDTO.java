package com.example.demo.DTO;

import java.time.LocalDate;
import java.util.List;

public class BookDTO {

    private String bookName;
    private LocalDate publishDate;
    List<LocalDate> lendingList;

    public BookDTO(String bookName, LocalDate publishDate, List<LocalDate> lendingList) {
        this.bookName = bookName;
        this.publishDate = publishDate;
        this.lendingList = lendingList;
    }
    public BookDTO(){}

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public void setLendingList(List<LocalDate> lendingList) {
        this.lendingList = lendingList;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public List<LocalDate> getLendingList() {
        return lendingList;
    }
}
