package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AccountDetailsController {

    @GetMapping("/accountDetails")
    public String goToAccountDetails() {
        System.out.println("go to account details");
        return "accountDetails";
    }



}
