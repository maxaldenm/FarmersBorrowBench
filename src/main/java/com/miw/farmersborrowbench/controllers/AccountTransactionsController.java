package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.Account;
import com.miw.farmersborrowbench.beans.Login;
import com.miw.farmersborrowbench.beans.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class AccountTransactionsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/accountTransactions")
    public String getAccountTransactions(@SessionAttribute("account") Account account, Model model) {
        model.addAttribute("account", account);
        return "accountTransactions";
    }

    @GetMapping("/goToMoneyTransaction")
    public String goToAccountTransactions(@RequestParam("accountNumber") String accountNumber, Model model) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        model.addAttribute("accountNumber", accountNumber);
        return "moneyTransaction";
    }

}
