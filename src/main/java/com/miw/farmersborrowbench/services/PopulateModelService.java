package com.miw.farmersborrowbench.services;

import com.miw.farmersborrowbench.beans.dto.MoneyTransactionDTO;
import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.entity.MoneyTransaction;
import com.miw.farmersborrowbench.beans.entity.User;
import com.miw.farmersborrowbench.repositories.AccountRepository;
import com.miw.farmersborrowbench.repositories.MoneyTransactionRepository;
import org.modelmapper.ModelMapper;
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

    public List<MoneyTransactionDTO> populateMoneyTransactionList(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        session.setAttribute("account", account);
        List<MoneyTransaction> moneyTransactions = moneyTransactionRepository.findMoneyTransactionsBySenderAccountAccountNumberOrReceiverAccountAccountNumber(account.getAccountNumber(), account.getAccountNumber());
        List<MoneyTransactionDTO> moneyTransactionDTOS = changeMoneyTransactionToMoneyTransactionDTOList(account, moneyTransactions);
        System.out.println(moneyTransactions.toString());
        System.out.println(moneyTransactionDTOS.toString());
        MoneyTransactionDTO.Comparators.BY_DATE_REVERSED.sort(moneyTransactionDTOS);
        return moneyTransactionDTOS;
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

    public List<MoneyTransactionDTO> populateSearchTransactionList(String search) {
        Account account = (Account) session.getAttribute("account");
        List<MoneyTransaction> moneyTransactions = moneyTransactionRepository.findMoneyTransactionsBySenderAccountAccountNumberOrReceiverAccountAccountNumber(account.getAccountNumber(), account.getAccountNumber());
        List<MoneyTransactionDTO> moneyTransactionDTOS = changeMoneyTransactionToMoneyTransactionDTOList(account, moneyTransactions);
        List<MoneyTransactionDTO> foundTransactions = new ArrayList<>();
        String searchLower = search.toLowerCase(Locale.ROOT);
        for (MoneyTransactionDTO t : moneyTransactionDTOS
        ) {
            if (t.toSearchString().toLowerCase().contains(searchLower)) foundTransactions.add(t);
        }
        return foundTransactions;
    }

    public List<MoneyTransactionDTO> populateSortedMoneyTransactionList(String accountNumber, String sort) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        session.setAttribute("account", account);
        List<MoneyTransaction> moneyTransactions = moneyTransactionRepository.findMoneyTransactionsBySenderAccountAccountNumberOrReceiverAccountAccountNumber(account.getAccountNumber(), account.getAccountNumber());
        List<MoneyTransactionDTO> moneyTransactionDTOS = changeMoneyTransactionToMoneyTransactionDTOList(account, moneyTransactions);
        switch (sort) {
            case "date": MoneyTransactionDTO.Comparators.BY_DATE_REVERSED.sort(moneyTransactionDTOS); break;
            case "account": MoneyTransactionDTO.Comparators.BY_ACCOUNT.sort(moneyTransactionDTOS); break;
            case "description": MoneyTransactionDTO.Comparators.BY_DESCRIPTION.sort(moneyTransactionDTOS); break;
            case "amount": MoneyTransactionDTO.Comparators.BY_AMOUNT_REVERSED.sort(moneyTransactionDTOS); break;
            default: MoneyTransactionDTO.Comparators.BY_DATE_REVERSED.sort(moneyTransactionDTOS); break;
        }
        return moneyTransactionDTOS;
    }

    private List<MoneyTransactionDTO> changeMoneyTransactionToMoneyTransactionDTOList(Account currentAccount, List<MoneyTransaction> moneyTransactions) {
        List<MoneyTransactionDTO> moneyTransactionDTOS = new ArrayList<>();
        for (MoneyTransaction t: moneyTransactions
        ) {
            ModelMapper modelMapper = new ModelMapper();
            MoneyTransactionDTO moneyTransactionDTO = modelMapper.map(t, MoneyTransactionDTO.class);
            moneyTransactionDTO.setSenderAccount(currentAccount.getAccountNumber());
            if(currentAccount.getAccountNumber().equals(t.getReceiverAccount().getAccountNumber())){
                moneyTransactionDTO.setReceiverAccount(t.getSenderAccount().getAccountNumber());
                moneyTransactionDTO.setAmount(moneyTransactionDTO.getAmount()*-1);
            }else{
                moneyTransactionDTO.setReceiverAccount(t.getReceiverAccount().getAccountNumber());
            }
            moneyTransactionDTOS.add(moneyTransactionDTO);
        }
        return moneyTransactionDTOS;
    }
}
