package com.miw.farmersborrowbench.beans.entity;

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
    private Account senderAccount;

    @ManyToOne
    private Account receiverAccount;

    private LocalDateTime date;

    public MoneyTransaction() {
    }

    public MoneyTransaction(double amount, String description, Account senderAccount, Account receiverAccount, LocalDateTime date) {
        this.amount = amount;
        this.description = description;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
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

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account debitAccount) {
        this.senderAccount = debitAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account creditAccount) {
        this.receiverAccount = creditAccount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String amountWithoutDecimalToString() {
        String amountString = String.valueOf(this.amount);
        String[] parts = amountString.split("\\.");
        return parts[0];
    }

    public String amountOnlyDecimalPlacesToString() {
        String amountString = String.valueOf(this.amount);
        String[] parts = amountString.split("\\.");
        if (parts[1].isEmpty()) {
            return "00";
        } else if (parts[1].length() == 1) {
            return parts[1] + "0";
        } else
            return parts[1].substring(0, 2);
    }

    public String localDateTimeToDateWithSlash() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(this.date);
    }
    
    @Override
    public String toString() {
        return "MoneyTransaction{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", debitAccount=" + senderAccount.toString() +
                ", creditAccount=" + receiverAccount.toString() +
                ", date=" + date +
                '}';
    }
}

