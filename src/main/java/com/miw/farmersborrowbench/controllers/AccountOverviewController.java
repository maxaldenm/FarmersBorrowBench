package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.forms.Login;
import com.miw.farmersborrowbench.services.PopulateModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class AccountOverviewController {

    @Autowired
    PopulateModelService populateModelService;

    @ModelAttribute("login")
    public Login getDefaultLogin() {
        return new Login();
    }

    @RequestMapping(value = "/accountOverview", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAccountOverview(HttpSession session, Model model) {
        System.out.println("postAccountOverview");
        if (session.getAttribute("isLoggedIn") != null) {
            model.addAttribute("accountList", populateModelService.populateAccountList());
            return "accountOverview";
        } else {
            return "login";
        }
    }

    @GetMapping("/goToAccountTransactions")
    public String goToAccountTransactions(@RequestParam("accountNumber") String accountNumber, Model model) {
        System.out.println("Go to account transactions");
        model.addAttribute("moneyTransactions", populateModelService.populateMoneyTransactionList(accountNumber));
        model.addAttribute("account", populateModelService.fetchAccount(accountNumber));
        return "accountTransactions";
    }

    @PostMapping("/searchAccountOverview")
    public String searchAccountOverview(@RequestParam("search") String search, Model model) {
        System.out.println("search account in accountoverview");
        model.addAttribute("accountList", populateModelService.populateSearchAccountList(search));
        model.addAttribute("search", search);
        return "accountOverview";
    }
}


