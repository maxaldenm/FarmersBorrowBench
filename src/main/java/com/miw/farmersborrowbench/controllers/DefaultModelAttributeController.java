package com.miw.farmersborrowbench.controllers;

import com.mit.farmersborrowbank.beans.Login;
import com.mit.farmersborrowbank.beans.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class DefaultModelAttributeController {

    @ModelAttribute("newuser")
    public User getDefaultUser(){
        return new User();
    }

    @ModelAttribute("login")
    public Login getDefaultLogin(){
        return new Login();
    }
}
