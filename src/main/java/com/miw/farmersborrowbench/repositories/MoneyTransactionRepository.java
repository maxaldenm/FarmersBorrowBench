package com.miw.farmersborrowbench.repositories;

import com.miw.farmersborrowbench.beans.entity.MoneyTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoneyTransactionRepository extends CrudRepository<MoneyTransaction, Integer> {

    List<MoneyTransaction> findMoneyTransactionsByDebitAccountAccountNumber(String debitIban);

    List<MoneyTransaction> findMoneyTransactionsByCreditAccountAccountNumber(String creditIban);

    List<MoneyTransaction> findMoneyTransactionsByDebitAccountAccountNumberOrCreditAccountAccountNumber(String creditIban, String debitIban);
}
