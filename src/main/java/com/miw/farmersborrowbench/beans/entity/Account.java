package com.miw.farmersborrowbench.beans.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account implements Comparable<Account> {
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

    public Account(Integer id, double balance, String accountNumber, String name, int pinNumber, boolean MKB,
                   List<User> users) {
        this.id = id;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.name = name;
        this.pinNumber = pinNumber;
        this.MKB = MKB;
        this.users = users;
    }

    public Account() {
    }

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
        return balance + " " + accountNumber + " " + name;
    }

    @Override
    public int compareTo(Account otherAccount) {
        return Double.compare(otherAccount.getBalance(), this.balance);
    }
}
