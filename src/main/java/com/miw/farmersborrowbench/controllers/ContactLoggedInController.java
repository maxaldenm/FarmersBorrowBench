package com.miw.farmersborrowbench.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactLoggedInController {

    @GetMapping("/goToContactLoggedIn")
    public String goToContactLoggedIn(){
        System.out.println("to contact logged in");
        return("contactloggedin");
    }
}
