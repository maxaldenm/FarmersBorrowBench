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
    public void processSubmitLastname(@Valid @ModelAttribute("moneytransactionform") MoneyTransactionForm moneyTransactionForm, HttpSession session, BindingResult result, Model model) {
        System.out.println("submit iban name checker ");
        model.addAttribute("accountNumber",moneyTransactionForm.getCreditIban());
        System.out.println(model.getAttribute("accountNumber"));

        Account debitAccount = accountRepository.findAccountByAccountNumber(moneyTransactionForm.getDebitIban());
        User checkUser = null;
        List<User> userList = null;

        if (debitAccount != null) {
            checkUser = userRepository.findByLnameLikeAndAccountsContains(moneyTransactionForm.getLastName(),
                    debitAccount);
            userList = userRepository.getAllByAccountsContains(debitAccount);
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

//        exact check
//        if (debitAccount != null) {
//            checkUser = userRepository.findByLnameEqualsAndAccountsContains(moneyTransactionForm.getLastName(),
//                    debitAccount);
//        }
//
//        if (checkUser != null) {
//            moneyTransactionForm.setCheckLname(true);
//        } else {
//            moneyTransactionForm.setCheckLname(false);
//        }

    }

    @PostMapping(value = "/moneyTransaction", params = "submit")
    public String processSubmitMoneyTransaction(@Valid @ModelAttribute("moneytransactionform") MoneyTransactionForm moneyTransactionForm,
                                                BindingResult result, HttpSession session, Model model) {



        System.out.println("submit money transaction");
        model.addAttribute("accountNumber",moneyTransactionForm.getCreditIban());

        System.out.println(model.getAttribute("accountNumber"));

        if (result.hasErrors()) return "moneyTransaction";

        //variable declaration
        Account debitAccount = new Account();
        Account creditAccount = accountRepository.findAccountByAccountNumber(moneyTransactionForm.getCreditIban());

        //check form for correct input IBAN serverside
        if (accountRepository.findAccountByAccountNumber(moneyTransactionForm.getDebitIban()) != null) {
            //if iban exists in this bank: set debit account
            debitAccount = accountRepository.findAccountByAccountNumber(moneyTransactionForm.getDebitIban());
        } else {
            //todo add errormessage that debitAccount does not exist in this bank
            return "moneyTransaction";
        }


        //add amount to debit account
        debitAccount.setBalance(debitAccount.getBalance() + Double.parseDouble(moneyTransactionForm.getAmount()));
        accountRepository.save(debitAccount);

        //subtract amount from credit account
        creditAccount.setBalance(creditAccount.getBalance() - Double.parseDouble(moneyTransactionForm.getAmount()));
        accountRepository.save(creditAccount);

        //set Entity bean moneyTransaction to the values of the moneyTransactionFrom
        MoneyTransaction moneyTransaction = new MoneyTransaction();
        moneyTransaction.setAmount(Double.parseDouble(moneyTransactionForm.getAmount()));
        moneyTransaction.setDescription(moneyTransactionForm.getDescription());
        moneyTransaction.setDebitAccount(debitAccount);
        moneyTransaction.setCreditAccount(creditAccount);
        moneyTransaction.setDate(LocalDateTime.now());
        moneyTransactionRepository.save(moneyTransaction);

        //add creditAccount to the model to pass it back to accountTransactions overview

        List<MoneyTransaction> moneyTransactions =
                moneyTransactionRepository.findMoneyTransactionsByDebitAccountAccountNumberOrCreditAccountAccountNumber
                (creditAccount.getAccountNumber(), creditAccount.getAccountNumber());

        User creditUser = (User) session.getAttribute("user");
        creditUser = userRepository.searchByBsn(creditUser.getBsn());

        session.setAttribute("user", creditUser);
        model.addAttribute("moneyTransactions", moneyTransactions);
        model.addAttribute("account", creditAccount);
        return "accountTransactions";




       /* System.out.println(debitAccount.getBalance());

        String lastName = moneyTransactionForm.getLastName();
        System.out.println("receiver " + user.getLname());*/
    }
}
