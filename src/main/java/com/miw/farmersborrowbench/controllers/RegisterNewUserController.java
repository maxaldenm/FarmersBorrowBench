package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.entity.User;
import com.miw.farmersborrowbench.beans.forms.Login;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import com.miw.farmersborrowbench.services.Iban;
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
import java.util.Arrays;
import java.util.List;

@Controller
public class RegisterNewUserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @ModelAttribute("user")
    public User getDefaultUser() {
        return new User();
    }

    @ModelAttribute("sectorItems")
    public List<String> getSectorItems() {
        return Arrays.asList(new String[]{"Weed", "XTC", "Meth", "Adult", "Crypto"});
    }

    @ModelAttribute("login")
    public Login getDefaultLogin() {
        return new Login();
    }

    @GetMapping("/goToRegisterNewUser")
    public String goToRegisterNewUser() {
        System.out.println("go to register");
        return "registerNewUser";
    }

    @PostMapping("/registerNewUser")
    public String processtSubmitregisterNewUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        System.out.println("submit New User");
        if (result.hasErrors()) {
            BeanPropertyBindingResult result2 = new BeanPropertyBindingResult(user, result.getObjectName());
            for (ObjectError error : result.getGlobalErrors()) {
                result2.addError(error);
            }
            for (FieldError error : result.getFieldErrors()) {
                if (error.getField().equals("password"))
                    result2.addError(new FieldError(error.getObjectName(), error.getField(), null, error.isBindingFailure(), error.getCodes(), error.getArguments(), error.getDefaultMessage()));
                else
                    result2.addError(new FieldError(error.getObjectName(), error.getField(), error.getRejectedValue(), error.isBindingFailure(), error.getCodes(), error.getArguments(), error.getDefaultMessage()));
            }
            model.addAllAttributes(result2.getModel());
            return "registerNewUser";
        }
        System.out.println("user object bsn:" + user.getBsn());
        User testuser = userRepository.searchByBsn(user.getBsn());
        Account account = new Account();
        if (testuser == null) {
            account.setUser(user);
            account.setBalance(0);
            account.setPinNumber(1234);
            String lastBankNumber = accountRepository.searchLastAccountNumber();
            if (lastBankNumber == null) {
                lastBankNumber = "NL69FBBA6969697055";
                account.setAccountNumber(lastBankNumber);

            } else {
                account.setAccountNumber(Iban.generateIban(lastBankNumber));
            }
            user.addAccount(account);
            accountRepository.save(account);
            userRepository.save(user);

        } else {
            //bestaat wel
        }

        return "login";
    }
}

