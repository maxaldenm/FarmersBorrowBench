package com.miw.farmersborrowbench.beans.entity;

import com.miw.farmersborrowbench.beans.Sector;
import com.miw.farmersborrowbench.beans.entity.Account;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, message = "{name.size}")
    private String username;
    @Size(min = 3, message = "{password.size}")
    private String password;
    //todo password opslaan als hashcode

    @Size(min = 9, max = 9, message = "{bsn.size}")
    private String bsn;
    @NotEmpty(message = "{not.empty}")
    @Pattern(regexp = "^[a-zA-Z-]*$", message = "{name.invalid}")
    private String fname;
    private String suffix;
    @NotEmpty(message = "{not.empty}")
    @Pattern(regexp = "^[a-zA-Z-]*$", message = "{name.invalid}")
    private String lname;
    @NotEmpty(message = "{not.empty}")
    private String postcode;
    @NotEmpty(message = "{not.empty}")
    private String city;
    @NotEmpty(message = "{not.empty}")
    private String streetname;
    @NotEmpty(message = "{not.empty}")
    @Pattern(regexp = "^[0-9]*$", message = "{houseNr.invalid}")
    private String houseNr;

    private boolean MKB;

    @Enumerated(EnumType.STRING)
    private Sector sector;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Account> accounts = new ArrayList<>();

    public User() {
    }

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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String straatnaam) {
        this.streetname = straatnaam;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(String huisNr) {
        this.houseNr = huisNr;
    }


}
