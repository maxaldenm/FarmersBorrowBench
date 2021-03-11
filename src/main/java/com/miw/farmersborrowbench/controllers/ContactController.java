package com.miw.farmersborrowbench.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping("/goToContact")
    public String goToContact(){
        System.out.println("go to contact");
        return "contact";
    }

    //todo contact pagina functionaliteit toevoegen
}
