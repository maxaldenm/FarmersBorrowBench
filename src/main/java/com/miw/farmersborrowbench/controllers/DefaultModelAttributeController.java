package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.Login;
import com.miw.farmersborrowbench.beans.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class DefaultModelAttributeController {

    @ModelAttribute("user")
    public User getDefaultUser(){
        return new User();
    }

    @ModelAttribute("sectorItems")
    public List<String> getSectorItems(){
        return Arrays.asList(new String[]{"Weed","XTC","Meth","Adult","Crypto"});
    }

    @ModelAttribute("login")
    public Login getDefaultLogin(){
        return new Login();
    }
}
