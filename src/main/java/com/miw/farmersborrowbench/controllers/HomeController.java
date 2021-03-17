package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.Account;
import com.miw.farmersborrowbench.beans.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/home")
    public String goHome() {
        System.out.println("in home controller");
        return "home";
    }

    @GetMapping("/goToLogin")
    public String goToLogin() {
        System.out.println("go to login");
        return "login";
    }

    @GetMapping("/goToRegisterNewUser")
    public String goToRegisterNewUser() {
        System.out.println("go to register");
        return "registerNewUser";
    }

    @GetMapping("/goToAccountOverview")
    public String goToAccountOverview() {
        System.out.println("go to register");
        return "accountOverview";
    }

    @GetMapping("/test")
    public String goToAccountOverviewTestAccount(@RequestParam("user") String username, HttpSession session, Model model) {
        System.out.println("go to accountvoerview test");
        User user = userRepository.searchByName(username);
        session.setAttribute("user", user);
        return "forward:/accountOverview";
    }
}
