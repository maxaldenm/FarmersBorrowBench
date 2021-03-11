package com.miw.farmersborrowbench.beans;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, message = "size.longer.than.2")
    private String username;
    @Size(min = 3, message = "size.longer.than.2")
    private String password;
    //todo password opslaan als hashcode

    @Size(min = 3, message = "size.longer.than.2")
    private String bsn;
    private String fname;
    private String suffix;
    private String lname;


    private boolean MKB;

    @Enumerated(EnumType.STRING)
    private Sector sector;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Account> accounts = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Integer getAccountId() {
        return accounts.get(0).getId();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String BSN) {
        this.bsn = BSN;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getsuffix() {
        return suffix;
    }

    public void setsuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public boolean isMKB() {
        return MKB;
    }

    public void setMKB(boolean MKB) {
        this.MKB = MKB;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }


}
