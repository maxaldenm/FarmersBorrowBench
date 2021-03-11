package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.*;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.MoneyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MoneyTransactionController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MoneyTransactionRepository moneyTransactionRepository;

    @PostMapping("/moneyTransaction")
    public String processSubmitLogin(@Valid @ModelAttribute("moneytransactionform") MoneyTransactionForm moneyTransactionForm, BindingResult result, Model model) {
        System.out.println("submit money transaction");
        if (result.hasErrors()) return "moneyTransaction";
        Account debitAccount = accountRepository.findAccountByAccountNumber(moneyTransactionForm.getDebitIban());
        Account creditAccount = accountRepository.findAccountByAccountNumber(moneyTransactionForm.getCreditIban());
        if(debitAccount!=null && creditAccount!=null){
            MoneyTransaction moneyTransaction = new MoneyTransaction();
            moneyTransaction.setAmount(Integer.parseInt(moneyTransactionForm.getAmount()));
            moneyTransaction.setDescription(moneyTransactionForm.getDescription());
            moneyTransaction.setDebitAccount(debitAccount);
            moneyTransaction.setCreditAccount(creditAccount);
            moneyTransactionRepository.save(moneyTransaction);
        }
        return "accountTransactions";
    }
}
