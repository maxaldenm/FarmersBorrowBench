package com.miw.farmersborrowbench.beans;

import java.math.BigInteger;
import java.util.Locale;

public class Iban {

    public static final int IBANNUMBER_MIN_SIZE = 15;
    public static final int IBANNUMBER_MAX_SIZE = 34;
    public static final BigInteger IBANNUMBER_MAGIC_NUMBER = new BigInteger("97");

    public static String generateIban(String iban){
        iban = iban.replaceAll("\\s+", "");
        iban = iban.toUpperCase(Locale.ROOT);
        do{
            String nrsIban = iban.substring(8);
            Long ibanLong = Long.parseLong(nrsIban);
            ibanLong++;
            iban = iban.substring(0,8) + String.valueOf(ibanLong);
        }while(!isIban(iban));
        return iban;
    }

    public static boolean isIban(String iban){
        String newAccountNumber = iban.replaceAll("\\s+", "");
        newAccountNumber = newAccountNumber.toUpperCase(Locale.ROOT);
        if (newAccountNumber.length() < IBANNUMBER_MIN_SIZE || newAccountNumber.length() > IBANNUMBER_MAX_SIZE) {
            return false;
        }
        newAccountNumber = newAccountNumber.substring(4) + newAccountNumber.substring(0, 4);
        StringBuilder numericAccountNumber = new StringBuilder();
        for (int i = 0;i < newAccountNumber.length();i++) {
            numericAccountNumber.append(Character.getNumericValue(newAccountNumber.charAt(i)));
        }
        BigInteger ibanNumber = new BigInteger(numericAccountNumber.toString());
        return ibanNumber.mod(IBANNUMBER_MAGIC_NUMBER).intValue() == 1;

        /*Client client = Client.create();
        WebResource webResource = client.resource("https://openiban.com/v2/calculate/NL/RABO/324");

        ClientResponse response = webResource.accept("").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);

        System.out.println(output);
        System.out.println();

        return false;*/
    }
}
