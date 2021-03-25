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
    HttpSession session;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MoneyTransactionRepository moneyTransactionRepository;

    public List<Account> populateAccountList() {
        return accountRepository.findAllByUsersContains((User) session.getAttribute("user"));
    }

    public List<MoneyTransaction> populateMoneyTransactionList(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        session.setAttribute("account", account);
        List<MoneyTransaction> moneyTransactions = moneyTransactionRepository.findMoneyTransactionsByDebitAccountAccountNumberOrCreditAccountAccountNumber(account.getAccountNumber(), account.getAccountNumber());
        MoneyTransaction.Comparators.BY_DATE_REVERSED.sort(moneyTransactions);
        return moneyTransactions;
    }

    public Account fetchAccount(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }

    public List<Account> populateSearchAccountList(String search) {
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

    public List<MoneyTransaction> populateSearchTransactionList(String search) {
        Account account = (Account) session.getAttribute("account");
        List<MoneyTransaction> transactions = moneyTransactionRepository.findMoneyTransactionsByDebitAccountAccountNumberOrCreditAccountAccountNumber(account.getAccountNumber(), account.getAccountNumber());
        List<MoneyTransaction> foundTransactions = new ArrayList<>();
        String searchLower = search.toLowerCase(Locale.ROOT);
        for (MoneyTransaction t : transactions
        ) {
            if (t.toSearchString().toLowerCase().contains(searchLower)) foundTransactions.add(t);
        }
        return foundTransactions;
    }

    public List<MoneyTransaction> populateSortedMoneyTransactionList(String accountNumber, String sort) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        session.setAttribute("account", account);
        List<MoneyTransaction> moneyTransactions = moneyTransactionRepository.findMoneyTransactionsByDebitAccountAccountNumberOrCreditAccountAccountNumber(account.getAccountNumber(), account.getAccountNumber());
        switch (sort) {
            case "date": MoneyTransaction.Comparators.BY_DATE_REVERSED.sort(moneyTransactions); break;
            case "account": MoneyTransaction.Comparators.BY_ACCOUNT.sort(moneyTransactions); break;
            case "description": MoneyTransaction.Comparators.BY_DESCRIPTION.sort(moneyTransactions); break;
            case "amount": MoneyTransaction.Comparators.BY_AMOUNT_REVERSED.sort(moneyTransactions); break;
            default: MoneyTransaction.Comparators.BY_DATE_REVERSED.sort(moneyTransactions); break;
        }
        return moneyTransactions;
    }
}
