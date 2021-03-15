package com.miw.farmersborrowbench.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccountNewForm {

    @NotEmpty(message = "{not.empty}")
    private String name;
    @NotEmpty(message = "{not.empty}")
    @Pattern(regexp = "^[0-9]*$", message = "Pin number can only contain numbers")
    @Size(min = 4, max=4, message = "Pin number must be 4 numbers long")
    private String pinNumber;
    private boolean MKB;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public boolean isMKB() {
        return MKB;
    }

    public void setMKB(boolean MKB) {
        this.MKB = MKB;
    }
}
