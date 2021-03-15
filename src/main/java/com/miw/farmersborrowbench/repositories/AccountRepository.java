package com.miw.farmersborrowbench.repositories;

import com.miw.farmersborrowbench.beans.Account;
import com.miw.farmersborrowbench.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

//    @Query(value = "select max(accountNumber) from Account", nativeQuery = true)
//    int searchLastaccountNumber();

    @Query(value = "SELECT MAX(a.accountNumber) FROM Account a")
    String searchLastAccountNumber();

//    @Query (value = "select a from Account a where a.accountNumber=:accoutnNumer")
//    Account searchAccountByAccountNumber(String accountNumber);

    Account findAccountByAccountNumber(String accountNumber);

    List<Account> findAllByUsersContains(User user);

    Account findByUsersContainsAndAccountNumberEquals(User user, String accountnumber);



//    List<Account> getAccountsByUserListContains(User user);

//    @Query("SELECT a FROM Account a WHERE a.user.id = :id")
//    Account searchAccountByUserId(int id);
}
