package com.miw.farmersborrowbench.repositories;

import com.miw.farmersborrowbench.beans.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

//    @Query(value = "select max(accountNumber) from Account", nativeQuery = true)
//    int searchLastaccountNumber();

    @Query(value = "SELECT MAX(a.accountNumber) FROM Account a")
    String searchLastAccountNumber();

//    @Query (value = "select a from Account a where a.accountNumber=:accoutnNumer")
//    Account searchAccountByAccountNumber(String accountNumber);

    Account findAccountByAccountNumber(String accountNumber);

//    @Query("SELECT a FROM Account a WHERE a.user.id = :id")
//    Account searchAccountByUserId(int id);
}
