package com.miw.farmersborrowbench.repositories;


import com.miw.farmersborrowbench.beans.entity.Account;
import com.miw.farmersborrowbench.beans.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query ("select u from User u where u.bsn = :bsn")
    User searchByBsn(@Param("bsn") String bsn);

    @Query("select u from User u where u.username = :name")
    public User searchByName(@Param("name") String username);


    User findByLnameEqualsAndAccountsContains(String lname, Account account);

    User findByLnameLikeAndAccountsContains(String lname, Account account);

    List<User> getAllByAccountsContains(Account account);

}
