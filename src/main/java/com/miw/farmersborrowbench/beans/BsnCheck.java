package com.miw.farmersborrowbench.beans;

public class BsnCheck {

//    Het burgerservicenummer bestaat uit 9 cijfers en voldoet aan een variant op de elfproef. De variant is in het
//    laatste cijfer, dat in plaats van met 1, met -1 wordt vermenigvuldigd. Dit verschil is er opzettelijk ingebracht
//    zodat een abusievelijk ingevoerd bankrekeningnummer als foutief wordt aangemerkt (dit gaat natuurlijk niet op als
//    het nummer eindigt op een 0, in welk geval een nummer zowel een geldig bankrekeningnummer als BSN kan zijn. Voor
//    het burgerservicenummer geldt in ieder geval dat het wel degelijk op een 0 mag eindigen). Als het
//    burgerservicenummer wordt voorgesteld door ABCDEFGHI, dan moet
//
//    (9 × A) + (8 × B) + (7 × C) + (6 × D) + (5 × E) + (4 × F) + (3 × G) + (2 × H) + (-1 × I)
//
//    een veelvoud van 11 zijn. Er kunnen met deze combinatie bijna 91 miljoen nummers gecreëerd worden.
//    Geldige voorbeelden zijn: 111222333 en 123456782.
//
//    @NH oftewel:
//
//    ((9 × A) + (8 × B) + (7 × C) + (6 × D) + (5 × E) + (4 × F) + (3 × G) + (2 × H) + (-1 × I)) % 11 = 0

    public boolean checkBsnIsReal(int bsnNummer) {
        int tempNum = 0;
        String asString = Integer.toString(bsnNummer);
        for (int i = 0; i < (asString.length() - 1); i++) {
            int num = Integer.parseInt(String.valueOf(asString.charAt(i)));
            tempNum += (num * (asString.length() - i));
        }
        int num = Integer.parseInt(String.valueOf(asString.charAt(asString.length())));
        tempNum += (num * -1);
        return tempNum % 11 == 0;
    }
}
