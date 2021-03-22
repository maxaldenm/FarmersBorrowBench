package com.miw.farmersborrowbench.beans.entity;

import com.miw.farmersborrowbench.beans.entity.Account;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public String amountWithoutDecimalToString() {
        String amountString = String.valueOf(this.amount);
        String f;
        String[] parts = amountString.split("\\.");
        f = parts[0];

        return f;
    }

    public String amountOnlyDecimalPlacesToString() {
        String amountString = String.valueOf(this.amount);
        String[] parts = amountString.split("\\.");
        String l;
        if (parts[1].isEmpty()) {
            return "00";
        } else if (parts[1].length() == 1) {
            return parts[1] + "0";
        } else
            l = parts[1].substring(0, 2);
        return l;
    }

    public String localDateTimeToDateWithSlash() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.date);
    }

}

