package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.Account;
import com.miw.farmersborrowbench.beans.AccountNewForm;
import com.miw.farmersborrowbench.beans.Iban;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterNewAccountController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

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

        Account account = new Account();
        if (user != null) {
            account.setUser(user);
            account.setBalance(0);
            account.setName(accountNewForm.getName());
            account.setPinNumber(Integer.parseInt(accountNewForm.getPinNumber()));
            account.setMKB(accountNewForm.isMKB());
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
            session.setAttribute("user", user);
        }
        return "forward:/accountOverview";
    }
}
