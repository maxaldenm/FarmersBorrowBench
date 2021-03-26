package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.entity.MoneyTransaction;
import com.miw.farmersborrowbench.beans.entity.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.MoneyTransactionRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import com.miw.farmersborrowbench.services.PopulateModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class AccountTransactionsController {

    @Autowired
    PopulateModelService populateModelService;

    @PostMapping("/accountTransactions")
    public String getAccountTransactions(@SessionAttribute("account") Account account, Model model) {
        System.out.println("get account transactions");
        model.addAttribute("moneyTransactions",
                populateModelService.populateMoneyTransactionList(account.getAccountNumber()));
        model.addAttribute("account", account);
        return "accountTransactions";
    }

    @GetMapping("/sortAccountTransactions")
    public String getSortAccountTransactions(@SessionAttribute("account") Account account, @Param("sort") String sort
            , Model model) {
        System.out.println("sort account transactions");
        model.addAttribute("moneyTransactions",
                populateModelService.populateSortedMoneyTransactionList(account.getAccountNumber(), sort));
        model.addAttribute("account", account);
        return "accountTransactions";
    }

    @GetMapping("/goBackToAccountOverview")
    public String goBackToAccountOverview(Model model) {
        model.addAttribute("accountList", populateModelService.populateAccountList());
        return "accountOverview";
    }

    @PostMapping("/searchTransactionOverview")
    public String searchAccountOverview(@SessionAttribute("account") Account account,
                                        @RequestParam("search") String search, Model model) {
        System.out.println("search in transactions");
        model.addAttribute("moneyTransactions", populateModelService.populateSearchTransactionList(search));
        model.addAttribute("account", account);
        model.addAttribute("search", search);
        return "accountTransactions";
    }
}
