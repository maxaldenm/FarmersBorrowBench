package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.forms.Login;
import com.miw.farmersborrowbench.beans.entity.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
/*@SessionAttributes("login")*/
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @ModelAttribute("login")
    public Login getDefaultLogin() {
        return new Login();
    }

    @GetMapping("/goToLogin")
    public String goToLogin() {
        System.out.println("go to login");
        return "login";
    }

    @GetMapping("/login")
    public String getToLogin(HttpSession session, Model model) {
        if(session.getAttribute("isLoggedIn") != null){
            List<Account> accountList = accountRepository.findAllByUsersContains((User)session.getAttribute("user"));

            model.addAttribute("accountList", accountList);
            return "accountOverview";
        }
        System.out.println("login");
        return "login";
    }

    @PostMapping("/login")
    public String processSubmitLogin(@Valid @ModelAttribute("login") Login login, BindingResult result, HttpSession session,  Model model) {

        System.out.println("submit login");
        User user = userRepository.searchByName(login.getUsername());

        if (result.hasErrors()) {
            return "login";
        }

        if (user == null || !user.getPassword().equals(login.getPassword())) {
            model.addAttribute("loginError", "Login details incorrect");
            return "login";
        }
        //Account account = accountRepository.searchAccountByUserId(user.getId());
//
//        Optional<Account> account = accountRepository.findById(user.getId()+1);
//        user.addAccount(account.get());
//        userRepository.save(user);


        //model.addAttribute("user", user);

        //model.addAttribute("account", account);
        //NH return "accountDetails";
        session.setAttribute("user", user);
        session.setAttribute("isLoggedIn", true);
        return "forward:/accountOverview";
    }
}
