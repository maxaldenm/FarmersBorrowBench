package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.Account;
import com.miw.farmersborrowbench.beans.Login;
import com.miw.farmersborrowbench.beans.MoneyTransaction;
import com.miw.farmersborrowbench.beans.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.MoneyTransactionRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountOverviewController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MoneyTransactionRepository moneyTransactionRepository;

    @PostMapping("/accountOverview")
    public String getAccountOverview(@SessionAttribute("user") User user, Model model) {
        List<Account> accountList = accountRepository.findAllByUsersContains(user);

        model.addAttribute("accountList", accountList);
        return "accountOverview";
    }

    @GetMapping("/goToAccountTransactions")
    public String goToAccountTransactions(@RequestParam("accountNumber") String accountNumber, Model model) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        List<MoneyTransaction> moneyTransactions =
                moneyTransactionRepository.findMoneyTransactionsByDebitAccountAccountNumberOrCreditAccountAccountNumber(account.getAccountNumber(), account.getAccountNumber());

        model.addAttribute("moneyTransactions", moneyTransactions);
        model.addAttribute("account", account);
        return "accountTransactions";
    }





}


