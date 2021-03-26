package com.miw.farmersborrowbench.repositories;

import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "SELECT MAX(a.accountNumber) FROM Account a")
    String searchLastAccountNumber();

    Account findAccountByAccountNumber(String accountNumber);

    List<Account> findAllByUsersContains(User user);

    Account findByUsersContainsAndAccountNumberEquals(User user, String accountnumber);

}
