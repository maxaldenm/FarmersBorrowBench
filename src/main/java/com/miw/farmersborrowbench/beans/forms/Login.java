package com.miw.farmersborrowbench.beans.forms;

import javax.validation.constraints.NotEmpty;

public class Login {
    @NotEmpty(message = "{name.empty}")
    private String username;
    @NotEmpty(message = "{password.empty}")
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
