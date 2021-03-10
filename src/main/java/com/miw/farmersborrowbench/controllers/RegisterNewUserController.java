package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.Account;
import com.miw.farmersborrowbench.beans.Iban;
import com.miw.farmersborrowbench.beans.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterNewUserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;


    @GetMapping("/registerNewUser")

    public String goToregisterNewUser() {
        System.out.println("go to open Account");
        return "registerNewUser";
    }

    @PostMapping("/registerNewUser")
    public String processtSubmitregisterNewUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        System.out.println("submit open Account");
        if (result.hasErrors()) {
            BeanPropertyBindingResult result2 = new BeanPropertyBindingResult(user, result.getObjectName());
            for(ObjectError error: result.getGlobalErrors()) {
                result2.addError(error);
            }
            for (FieldError error: result.getFieldErrors()) {
                if(error.getField().equals("password")) result2.addError(new FieldError(error.getObjectName(), error.getField(), null, error.isBindingFailure(), error.getCodes(), error.getArguments(), error.getDefaultMessage()));
                else result2.addError(new FieldError(error.getObjectName(), error.getField(), error.getRejectedValue(), error.isBindingFailure(), error.getCodes(), error.getArguments(), error.getDefaultMessage()));
            }
            model.addAllAttributes(result2.getModel());
            return "registerNewUser";
        }
        User testuser = userRepository.searchByBsn(user.getBsn());
        Account account = new Account();
        if (testuser == null) {
            userRepository.save(user);
            account.setUser(user);
            account.setBalance(0);
            String lastBankNumber = accountRepository.searchLastAccountNumber();
            if (lastBankNumber == null) {
                lastBankNumber = "NL69 FBBA 6969697055";
                account.setAccountNumber(lastBankNumber);
                accountRepository.save(account);
            } else {
                account.setAccountNumber(Iban.ibanGenerator(lastBankNumber));
                accountRepository.save(account);
            }
        } else {
            //bestaat wel
        }
        model.addAttribute("user", user);
        model.addAttribute("account", account);
        return "accountDetails";
    }
}

