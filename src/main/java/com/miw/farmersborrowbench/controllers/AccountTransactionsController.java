package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.entity.MoneyTransaction;
import com.miw.farmersborrowbench.beans.entity.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.MoneyTransactionRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountTransactionsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MoneyTransactionRepository moneyTransactionRepository;

    @PostMapping("/accountTransactions")
    public String getAccountTransactions(@ModelAttribute("account") Account account, Model model) {
        System.out.println("get account transactions");
        List<MoneyTransaction> moneyTransactions =
                moneyTransactionRepository.findMoneyTransactionsByDebitAccountAccountNumberOrCreditAccountAccountNumber(account.getAccountNumber(), account.getAccountNumber());
        System.out.println(account);
        model.addAttribute("moneyTransactions", moneyTransactions);
        model.addAttribute("account", account);
        return "accountTransactions";
    }

    @GetMapping("/goBackToAccountOverview")
    public String goBackToAccountOverview(@SessionAttribute("user") User user, Model model) {
        List<Account> accountList = accountRepository.findAllByUsersContains(user);
        model.addAttribute("accountList", accountList);
        return "accountOverview";
    }

}
