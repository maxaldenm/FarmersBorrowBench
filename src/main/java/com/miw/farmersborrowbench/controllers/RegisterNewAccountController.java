package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.forms.AccountNewForm;
import com.miw.farmersborrowbench.services.Iban;
import com.miw.farmersborrowbench.beans.entity.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterNewAccountController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @ModelAttribute("accountnewform")
    public AccountNewForm getDefaultAccountNewForm() {
        return new AccountNewForm();
    }

    @GetMapping("/goToRegisterNewAccount")
    public String goToRegisterNewAccount() {
        return "registerNewAccount";
    }

    @PostMapping("/registerNewAccount")
    public String goToRegisterNewAccount(@Valid @ModelAttribute("accountnewform") AccountNewForm accountNewForm, BindingResult result, HttpSession session, Model model) {
        System.out.println("submit register new account");

        if (result.hasErrors()) {
            return "registerNewAccount";
        }

        User user = (User) session.getAttribute("user");

        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountNewForm, Account.class);
        if (user != null) {
            account.setUser(user);
            account.setBalance(0);
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
        }
        return "forward:/accountOverview";
    }
}
