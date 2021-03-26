package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.entity.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ViewCustomersController {

    @Autowired
    AccountRepository accountRepository;

    @ModelAttribute("user")
    public User getDefaultUser() {
        return new User();
    }

    @ModelAttribute("account")
    public Account getDefaultAccount() {
        return new Account();
    }


    @GetMapping("/viewCustomers")
    public String toViewCustomers() {
        System.out.println("to View Customers");
        return "viewCustomers";
    }

    @GetMapping("/getTopAccountBalance")
    public String getTopAccountBalance(Model model) {
        List<Account> accountList = new ArrayList<>(accountRepository.findAll());
        List<Account> sortedAccountList = new ArrayList<>();
        Collections.sort(accountList);
        for (int i = 0; i < 10; i++) {
            sortedAccountList.add(accountList.get(i));
        }

        model.addAttribute("sortedAccountList", sortedAccountList);
        return "viewCustomers";
    }

}
