package DTO;

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

    public LendingDTO(String customerId, Integer bookId, LocalDate lendingDate, boolean returned) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.lendingDate = lendingDate;
        this.returned = returned;
    }
}
