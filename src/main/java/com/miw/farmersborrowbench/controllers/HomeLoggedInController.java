package com.miw.farmersborrowbench.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeLoggedInController {

    @GetMapping("/goToHomeLoggedIn")
    public String goToHomeLoggedIn(){
        System.out.println("to home logged in");
        return("homeloggedin");
    }
}
