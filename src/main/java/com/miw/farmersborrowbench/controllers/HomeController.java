package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

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

}
