package com.miw.farmersborrowbench.beans.forms;

import javax.validation.constraints.AssertTrue;
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


    @AssertTrue(message = "{debitaccount.equals.creditaccount}")
    public boolean isCreditEqualsDebitTrue() {
        if(senderAccount != null) {
            if(senderAccount.equals(receiverAccount)) {
                return false;
            }
        }
        return true;
    }


//    @AssertTrue(message = "cannot be equal")
//    private boolean isCreditEqualToDebit() {
//        if(debitIban != null) {
//            if (creditIban.equals(debitIban)) {
//                System.out.println("inside false");
//                return false;
//            }
//        }
//        System.out.println("inside true");
//        return true;
//    }

    @NotEmpty
    private String description;

    @NotEmpty
    private String senderAccount;

    @NotEmpty
    private String receiverAccount;

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

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
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
