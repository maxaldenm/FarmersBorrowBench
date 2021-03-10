package com.miw.farmersborrowbench.controllers;


import com.miw.farmersborrowbench.beans.Login;
import com.miw.farmersborrowbench.beans.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/login")
    public String goToLogin() {
        System.out.println("go to login");
        return "login";
    }

    @PostMapping("/login")
    public String processSubmitLogin(@Valid @ModelAttribute("login") Login login, BindingResult result, Model model) {
        System.out.println("submit login");
        User user = userRepository.searchByName(login.getUsername());

        if(result.hasErrors())return "login";

        if(user==null || !user.getPassword().equals(login.getPassword())){
            model.addAttribute("loginError", "Login details incorrect");
            return "login";
        }
        //Account account = accountRepository.searchAccountByUserId(user.getId());
//
//        Optional<Account> account = accountRepository.findById(user.getId()+1);
//        user.addAccount(account.get());
//        userRepository.save(user);

        model.addAttribute("user", user);

        //model.addAttribute("account", account);
        //NH return "accountDetails";
        return "accountOverview";

    }
}
