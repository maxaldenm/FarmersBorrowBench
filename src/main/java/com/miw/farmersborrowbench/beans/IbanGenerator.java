package com.miw.farmersborrowbench.beans;

public class IbanGenerator {

    public String ibanGenerator(String iban) {
        IbanCheck ibanCheck = new IbanCheck();

        do {
            String sub = iban.substring(10);
            sub = sub.replaceAll("\\s+", "");
            Long plus = Long.parseLong(sub);
            plus++;
            iban = iban.substring(0, 10) + plus;
        } while (!ibanCheck.ibanTest(iban));
        iban = iban.replaceAll("\\s+", "");
        iban = iban.substring(0, 4) + " " + iban.substring(4, 8) + " " + iban.substring(8, 12) + " " + iban.substring(12, 16) + " " + iban.substring(16);

        return iban;
    }
}

