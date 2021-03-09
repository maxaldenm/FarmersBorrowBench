package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.Account;
import com.miw.farmersborrowbench.beans.IbanGenerator;
import com.miw.farmersborrowbench.beans.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class OpenRekeningController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;


    @GetMapping("/openRekening")

    public String goToOpenRekening() {
        System.out.println("go to open rekening");
        return "openRekening";
    }

    @PostMapping("/openRekening")
    public String verwerktSubmitOpenRekening(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        System.out.println("submit open rekening");
        if (result.hasErrors()) {
            //todo: errormessages worden niet meer getoondin de openRekeningController
            BeanPropertyBindingResult result2 = new BeanPropertyBindingResult(user, result.getObjectName());
            for (FieldError error : result.getFieldErrors())
                if (error.getField().equals("password")) result2.addError(new FieldError(error.getObjectName(),
                        error.getField(), null, error.isBindingFailure(), error.getCodes(),
                        error.getArguments(), error.getDefaultMessage()));
            model.addAllAttributes(result2.getModel());
            return "openRekening";
        }

        User testuser = userRepository.searchByBsn(user.getBsn());
        Account account = new Account();
        if (testuser == null) {
            userRepository.save(user);
            account.setUser(user);
            account.setSaldo(0);
            String lastBankNumber = accountRepository.searchLastRekeningNummer();
            if (lastBankNumber == null) {
                lastBankNumber = "NL69 FBBA 6969697055";
                accountRepository.save(account);
            } else {
                account.setRekeningNummer(new IbanGenerator().ibanGenerator(lastBankNumber));
                accountRepository.save(account);
            }
        } else {
            //bestaat wel
        }

        model.addAttribute("user", user);
        model.addAttribute("account", account);
        return "toonRekening";
    }
}

