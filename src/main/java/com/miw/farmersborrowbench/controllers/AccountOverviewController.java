package com.miw.farmersborrowbench.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountOverviewController {

    @GetMapping("/accountOverview")
    public String goToAccountOverview() {
        System.out.println("in account overview controller");
        return "accountOverview";
    }
}


