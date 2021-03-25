package com.miw.farmersborrowbench.services;

public class DecimalPlacesConverter {

    public String amountWithoutDecimalToString(Double amount) {
        String amountString = String.valueOf(amount);
        String f;
        String[] parts = amountString.split("\\.");
        f = parts[0];

        return f;
    }

    public String amountOnlyDecimalPlacesToString(Double amount) {
        String amountString = String.valueOf(amount);
        String[] parts = amountString.split("\\.");
        String l;
        if (parts[1].isEmpty()) {
            return "00";
        } else if (parts[1].length() == 1) {
            return parts[1] + "0";
        } else
            l = parts[1].substring(0, 2);
        return l;
    }
}
