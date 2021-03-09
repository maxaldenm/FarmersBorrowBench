package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class DefaultModelAttributeController {

    @ModelAttribute("user")
    public User getDefaultUser(){
        return new User();
    }
}
