package com.miw.farmersborrowbench.beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double balance;
    private String accountNumber;
    private String name;
    private int pinNumber;
    private boolean MKB;


    @ManyToMany(mappedBy = "accounts")
    private List<User> users = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public List<User> getUserList() {
        return users;
    }

    public void setUser(User user) {
        users.add(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMKB() {
        return MKB;
    }

    public void setMKB(boolean MKB) {
        this.MKB = MKB;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                '}';
    }

    public String toSearchString() {
        return balance+" "+accountNumber+" "+name;
    }
}
