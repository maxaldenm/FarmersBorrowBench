package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.Account;
import com.miw.farmersborrowbench.beans.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/login")
    public String goToLogin() {
        System.out.println("go to login");
        return "login";
    }

    @PostMapping("/login")
    public String processtSubmitLogin(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        System.out.println("submit login");

        //NH: get user from database through userRepository
        User testuser = userRepository.searchByBsn(user.getBsn());

        //NH: get user password from database through userRepository
        String userPassword = testuser.getPassword();
        String inputPassword = user.getPassword();

        //NH: check user password against input password
        if (userPassword.equals(inputPassword)) {

            //NH: get accountiformation for user if credentials are correct
            Account account = accountRepository.searchAccountByUserId(testuser.getId());

            //NH: fill model with user and account and show accountDetails
            model.addAttribute("user", testuser);
            model.addAttribute("account", account);
            return "accountDetails";
        } else {
            //todo: show errormessage credentials are incoorrect and remove input from password field

        }
        return "login";
    }
}
