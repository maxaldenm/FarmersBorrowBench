package com.miw.farmersborrowbench.beans;

import javax.validation.constraints.NotEmpty;

public class Login {

    @NotEmpty(message = "Enter username")
    private String username;
    @NotEmpty(message = "Enter password")
    private String password;

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
}
