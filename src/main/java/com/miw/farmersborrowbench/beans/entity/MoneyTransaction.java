package com.miw.farmersborrowbench.beans.entity;

import com.miw.farmersborrowbench.beans.entity.Account;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Entity
public class MoneyTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double amount;

    private String description;

    @ManyToOne
    private Account debitAccount;

    @ManyToOne
    private Account creditAccount;

    private LocalDateTime date;

    public MoneyTransaction() {
    }

    public MoneyTransaction(double amount, String description, Account debitAccount, Account creditAccount, LocalDateTime date) {
        this.amount = amount;
        this.description = description;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MoneyTransaction{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", debitAccount=" + debitAccount.toString() +
                ", creditAccount=" + creditAccount.toString() +
                ", date=" + date +
                '}';
    }
}

