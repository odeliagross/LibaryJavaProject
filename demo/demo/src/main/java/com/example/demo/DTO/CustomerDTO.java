package com.example.demo.DTO;

import com.example.demo.ModelsEntity.Lending;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashMap;
import java.util.List;

public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private List<LendingDTO> lendingList;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public List<LendingDTO> getLendingList() {
        return lendingList;
    }

    public void setLendingList(List<LendingDTO> lendingList) {
        this.lendingList = lendingList;
    }

    public CustomerDTO(String firstName, String lastName, String phone, List<LendingDTO> lendingList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.lendingList = lendingList;
    }

    public CustomerDTO(){}
}
