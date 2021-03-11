package com.miw.farmersborrowbench.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class MoneyTransactionForm {

    @Pattern(regexp = "(^[0-9]*$)", message = "Only enter numbers")
    private String amount;

    @NotEmpty
    private String description;

    @NotEmpty
    private String debitIban;

    private String creditIban;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDebitIban() {
        return debitIban;
    }

    public void setDebitIban(String debitIban) {
        this.debitIban = debitIban;
    }

    public String getCreditIban() {
        return creditIban;
    }

    public void setCreditIban(String creditIban) {
        this.creditIban = creditIban;
    }
}
