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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MoneyTransactionController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MoneyTransactionRepository moneyTransactionRepository;

    @PostMapping("/moneyTransaction")
    public String processSubmitMoneyTransaction(@Valid @ModelAttribute("moneytransactionform") MoneyTransactionForm moneyTransactionForm, HttpSession session, BindingResult result, Model model) {
        System.out.println("submit money transaction");
        if (result.hasErrors()) return "moneyTransaction";
        Account debitAccount = accountRepository.findAccountByAccountNumber(moneyTransactionForm.getDebitIban());
        debitAccount.setBalance(debitAccount.getBalance()+Integer.parseInt(moneyTransactionForm.getAmount()));
        accountRepository.save(debitAccount);
        Account creditAccount = accountRepository.findAccountByAccountNumber(moneyTransactionForm.getCreditIban());
        creditAccount.setBalance(creditAccount.getBalance()-Integer.parseInt(moneyTransactionForm.getAmount()));
        accountRepository.save(creditAccount);
        if (debitAccount != null && creditAccount != null) {
            MoneyTransaction moneyTransaction = new MoneyTransaction();
            moneyTransaction.setAmount(Integer.parseInt(moneyTransactionForm.getAmount()));
            moneyTransaction.setDescription(moneyTransactionForm.getDescription());
            moneyTransaction.setDebitAccount(debitAccount);
            moneyTransaction.setCreditAccount(creditAccount);
            moneyTransactionRepository.save(moneyTransaction);
        }
        System.out.println(creditAccount.toString());
        session.setAttribute("account", creditAccount);
        return "forward:/accountTransactions";
    }
}
