package com.miw.farmersborrowbench.services;

import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.entity.MoneyTransaction;
import com.miw.farmersborrowbench.beans.entity.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.MoneyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PopulateModelService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MoneyTransactionRepository moneyTransactionRepository;

    public List<Account> populateAccountList(HttpSession session) {
        return accountRepository.findAllByUsersContains((User) session.getAttribute("user"));
    }

    public List<MoneyTransaction> populateMoneyTransactionList(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        return moneyTransactionRepository.findMoneyTransactionsByDebitAccountAccountNumberOrCreditAccountAccountNumber(account.getAccountNumber(), account.getAccountNumber());
    }

    public Account fetchAccount(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }

    public List<Account> populateSearchAccountList(HttpSession session, String search) {
        User user = (User) session.getAttribute("user");
        List<Account> accounts = user.getAccounts();
        List<Account> foundAccounts = new ArrayList<>();
        String searchLower = search.toLowerCase(Locale.ROOT);
        for (Account a : accounts
        ) {
            if (a.toSearchString().toLowerCase().contains(searchLower)) foundAccounts.add(a);
        }
        return foundAccounts;
    }

}
