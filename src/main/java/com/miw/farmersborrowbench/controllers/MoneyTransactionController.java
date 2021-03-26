package com.miw.farmersborrowbench.controllers;

import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.entity.MoneyTransaction;
import com.miw.farmersborrowbench.beans.entity.User;
import com.miw.farmersborrowbench.beans.forms.MoneyTransactionForm;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.MoneyTransactionRepository;
import com.miw.farmersborrowbench.repositories.UserRepository;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MoneyTransactionController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MoneyTransactionRepository moneyTransactionRepository;

    @ModelAttribute("moneytransactionform")
    public MoneyTransactionForm getDefaultMoneyTransactionForm() {
        return new MoneyTransactionForm();
    }

    @GetMapping("/goToMoneyTransaction")
    public String goToAccountTransactions(@RequestParam("accountNumber") String accountNumber, Model model) {
        /*Account account = accountRepository.findAccountByAccountNumber(accountNumber);*/
        model.addAttribute("accountNumber", accountNumber);
        return "moneyTransaction";
    }

    @PostMapping(value = "/moneyTransaction", params = "checker")
    public String processSubmitLastname(@Valid @ModelAttribute("moneytransactionform") MoneyTransactionForm moneyTransactionForm, BindingResult result, HttpSession session, Model model) {
        System.out.println("submit iban name checker ");
        //model.addAttribute("accountNumber",moneyTransactionForm.getReceiverAccount());
        System.out.println(model.getAttribute("accountNumber"));
        Account receiverAccount = accountRepository.findAccountByAccountNumber(moneyTransactionForm.getSenderAccount());
        List<User> userList = null;


        if (result.hasErrors()) {
            System.out.println("handling error:");
            return "moneyTransaction";
        }

        System.out.println("@PostMapping(value = \"/moneyTransaction\", params = \"submit\")");

        model.addAttribute("accountNumber", moneyTransactionForm.getSenderAccount());

        if (receiverAccount != null) {
            userList = userRepository.getAllByAccountsContains(receiverAccount);
        }

        if (userList != null) {
            //HashMap<Integer,String> hashMap = new HashMap<>();
            int FUZZY_MATCH_SUCCESS = 80;
            int fuzzyInt = 0;
            String fuzzyString = null;
            moneyTransactionForm.setCheckIban(true);

            for (User user: userList) {
//                Integer fuzzyInt = FuzzySearch.ratio(user.getLname(), moneyTransactionForm.getLastName());
//                String fuzzyString = user.getLname();
                int fuzzy = (FuzzySearch.tokenSetRatio(user.getLname(), moneyTransactionForm.getLastName()));
                if(fuzzy > FUZZY_MATCH_SUCCESS) {
                    fuzzyInt = fuzzy;
                    fuzzyString = user.getLname();
                    moneyTransactionForm.setCheckLname(true);
                }
            }
            moneyTransactionForm.setLastName(moneyTransactionForm.getLastName() + " (" + fuzzyInt + "% match)");
            //moneyTransactionForm.setCheckLnameText(" match: " + fuzzyInt + " with " + fuzzyString);
        } else {
            moneyTransactionForm.setCheckLname(false);
            moneyTransactionForm.setCheckIban(false);
        }
        return "moneyTransaction";
    }

    @PostMapping(value = "/moneyTransaction", params = "submit")
    public String processSubmitMoneyTransaction(@Valid @ModelAttribute("moneytransactionform") MoneyTransactionForm moneyTransactionForm,
                                                BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            System.out.println("result has errors");
            return "moneyTransaction";
        }

        System.out.println("@PostMapping(value = \"/moneyTransaction\", params = \"submit\")");

        model.addAttribute("accountNumber", moneyTransactionForm.getSenderAccount());

        //variable declaration
        System.out.println(model.getAttribute("accountNumber"));


        Account senderAccount = accountRepository.findAccountByAccountNumber((String) model.getAttribute("accountNumber"));
        Account receiverAccount = accountRepository.findAccountByAccountNumber(moneyTransactionForm.getReceiverAccount());

        if(receiverAccount == null) {
            result.rejectValue("creditIban","account.invalid");
            System.out.println("credit account does not exist");
            return "moneyTransaction";
        }


        senderAccount.setBalance(senderAccount.getBalance() - Double.parseDouble(moneyTransactionForm.getAmount()));
        accountRepository.save(senderAccount);

        receiverAccount.setBalance(receiverAccount.getBalance() + Double.parseDouble(moneyTransactionForm.getAmount()));
        accountRepository.save(receiverAccount);

        //set Entity bean moneyTransaction to the values of the moneyTransactionFrom
        MoneyTransaction moneyTransaction = new MoneyTransaction(Double.parseDouble(moneyTransactionForm.getAmount()),
                moneyTransactionForm.getDescription(),
                senderAccount,
                receiverAccount,
                LocalDateTime.now());
        moneyTransactionRepository.save(moneyTransaction);

        //add receiverAccount to the model to pass it back to accountTransactions overview

        List<MoneyTransaction> moneyTransactions =
                moneyTransactionRepository.findMoneyTransactionsBySenderAccountAccountNumberOrReceiverAccountAccountNumber
                (senderAccount.getAccountNumber(), senderAccount.getAccountNumber());

//        User creditUser = (User) session.getAttribute("user");
//        creditUser = userRepository.searchByBsn(creditUser.getBsn());
//
//        session.setAttribute("user", creditUser);
        User creditUser = (User) session.getAttribute("user");
        creditUser = userRepository.searchByBsn(creditUser.getBsn());

        session.setAttribute("user", creditUser);
        session.setAttribute("account", receiverAccount);
        model.addAttribute("moneyTransactions", moneyTransactions);
        model.addAttribute("account", receiverAccount);

        return "redirect:/goToAccountTransactions";


       /* System.out.println(senderAccount.getBalance());

        String lastName = moneyTransactionForm.getLastName();
        System.out.println("receiver " + user.getLname());*/
    }
}
