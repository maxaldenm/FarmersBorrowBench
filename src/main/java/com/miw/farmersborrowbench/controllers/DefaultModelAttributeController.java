package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.*;
import com.miw.farmersborrowbench.forms.AccountNewForm;
import com.miw.farmersborrowbench.forms.MoneyTransactionForm;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class DefaultModelAttributeController {

    @ModelAttribute("user")
    public User getDefaultUser() {
        return new User();
    }

    @ModelAttribute("sectorItems")
    public List<String> getSectorItems() {
        return Arrays.asList(new String[]{"Weed", "XTC", "Meth", "Adult", "Crypto"});
    }

    @ModelAttribute("login")
    public Login getDefaultLogin() {
        return new Login();
    }

    @ModelAttribute("account")
    public Account getDefaultAccount() {
        return new Account();
    }

    @ModelAttribute("moneytransactionform")
    public MoneyTransactionForm getDefaultMoneyTransactionForm() {
        return new MoneyTransactionForm();
    }

    @ModelAttribute("accountnewform")
    public AccountNewForm getDefaultAccountNewForm() {
        return new AccountNewForm();
    }
}
