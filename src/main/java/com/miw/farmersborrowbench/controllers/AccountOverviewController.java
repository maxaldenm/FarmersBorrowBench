package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.entity.MoneyTransaction;
import com.miw.farmersborrowbench.beans.entity.User;
import com.miw.farmersborrowbench.beans.forms.Login;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.MoneyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class AccountOverviewController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MoneyTransactionRepository moneyTransactionRepository;

    @ModelAttribute("login")
    public Login getDefaultLogin() {
        return new Login();
    }

    @RequestMapping(value = "/accountOverview", method = { RequestMethod.GET, RequestMethod.POST })
    public String getAccountOverview(HttpSession session, Model model) {
        System.out.println("postAccountOverview");
        if(session.getAttribute("isLoggedIn") != null){
            List<Account> accountList = accountRepository.findAllByUsersContains((User)session.getAttribute("user"));

            model.addAttribute("accountList", accountList);
            return "accountOverview";
        }else{
            return "login";
        }
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

    @PostMapping("/searchAccountOverview")
    public String searchAccountOverview(@RequestParam("search") String search, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Account> accounts = user.getAccounts();
        List<Account> foundAccounts = new ArrayList<>();
        String searchLower = search.toLowerCase(Locale.ROOT);
        for (Account a: accounts
             ) {
            if(a.toSearchString().toLowerCase().contains(searchLower)) foundAccounts.add(a);
        }
        model.addAttribute("accountList", foundAccounts);
        model.addAttribute("search", search);
        return "accountOverview";
    }

}


