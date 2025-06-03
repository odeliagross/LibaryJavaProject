package com.example.demo.DTO;

import com.example.demo.ModelsEntity.Lending;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashMap;
import java.util.List;

public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String phone;
    HashMap<String,Boolean> lendingList;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLendingList(HashMap<String, Boolean> lendingList) {
        this.lendingList = lendingList;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhone() {
        return phone;
    }

    public HashMap<String, Boolean> getLendingList() {
        return lendingList;
    }

    public CustomerDTO(String firstName, String lastName, String phone, HashMap<String, Boolean> lendingList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.lendingList = lendingList;
    }
}
