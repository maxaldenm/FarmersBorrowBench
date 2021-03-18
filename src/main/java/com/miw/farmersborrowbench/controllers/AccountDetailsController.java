package com.miw.farmersborrowbench.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountDetailsController {

    @GetMapping("/accountDetails")
    public String goToAccountDetails() {
        System.out.println("go to account details");
        return "accountDetails";
    }



}
