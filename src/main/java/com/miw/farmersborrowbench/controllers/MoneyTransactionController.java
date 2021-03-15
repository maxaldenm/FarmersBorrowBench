package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.Account;
import com.miw.farmersborrowbench.beans.MoneyTransaction;
import com.miw.farmersborrowbench.beans.MoneyTransactionForm;
import com.miw.farmersborrowbench.beans.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.MoneyTransactionRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MoneyTransactionController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MoneyTransactionRepository moneyTransactionRepository;

    @PostMapping(value = "/moneyTransaction", params = "checker")
    public void processSubmitLastname(@Valid @ModelAttribute("moneytransactionform") MoneyTransactionForm moneyTransactionForm, HttpSession session, BindingResult result, Model model) {
        System.out.println("submit iban name checker ");
        Account debitAccount = accountRepository.findAccountByAccountNumber(moneyTransactionForm.getDebitIban());
        User checkUser = null;

        if(debitAccount != null) {
            checkUser = userRepository.findByLnameEqualsAndAccountsContains(moneyTransactionForm.getLastName(),debitAccount);
        }

        if(checkUser != null) {
            moneyTransactionForm.setCheckLname(true);
        } else {
            moneyTransactionForm.setCheckLname(false);
        }

    }

    @PostMapping(value = "/moneyTransaction", params = "submit")
    public String processSubmitMoneyTransaction(@Valid @ModelAttribute("moneytransactionform") MoneyTransactionForm moneyTransactionForm, HttpSession session, BindingResult result, Model model) {
        System.out.println("submit money transaction");
        if (result.hasErrors()) return "moneyTransaction";
        Account debitAccount = accountRepository.findAccountByAccountNumber(moneyTransactionForm.getDebitIban());

        System.out.println(debitAccount.getBalance());

        Boolean lnameCheck = false;
        String lastName = moneyTransactionForm.getLastName();

        User receiver = userRepository.findUserByLnameContains(lastName);

        System.out.println("receiver " + receiver.getLname());

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
