package com.miw.farmersborrowbench.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountOverviewController {

    @GetMapping("/rekeningOverzicht")
    public String goToRekeningOverzicht() {
        System.out.println("in rekening overzicht controller");
        return "accountOverview";
    }
}
