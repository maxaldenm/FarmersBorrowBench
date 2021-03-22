package com.miw.farmersborrowbench.beans.forms;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotEmpty;

public class MoneyTransactionForm {

    //@Pattern(regexp = "(^[0-9]*$)", message = "Only enter numbers")
    private String amount;

    private String lastName;

    private boolean checkLname;

    private boolean checkIban;


    public String getCheckLnameText() {
        return checkLnameText;
    }

    public void setCheckLnameText(String checkLnameText) {
        this.checkLnameText = checkLnameText;
    }

    private String checkLnameText;

    @AssertFalse(message = "wtf LOL")
    private boolean isOk() {
        return debitIban.equals(creditIban);
    }


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

    public boolean isCheckLname() {
        return checkLname;
    }

    public void setCheckLname(boolean checkLname) {
        this.checkLname = checkLname;
    }

    public boolean isCheckIban() {
        return checkIban;
    }

    public void setCheckIban(boolean checkIban) {
        this.checkIban = checkIban;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
