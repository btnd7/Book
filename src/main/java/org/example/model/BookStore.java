package org.example.model;

import jakarta.persistence.*;

import java.util.Map;

public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;

    private String address;

    private String phoneNumber;

    private boolean isActive;
    @ElementCollection
    private Map<String, Integer> booksInStock;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Map<String, Integer> getBooksInStock() {
        return booksInStock;
    }

    public void setBooksInStock(Map<String, Integer> booksInStock) {
        this.booksInStock = booksInStock;
    }
}
