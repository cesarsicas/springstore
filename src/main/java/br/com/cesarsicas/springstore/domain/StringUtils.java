package br.com.cesarsicas.springstore.domain;

public class StringUtils {

    public static String maskCreditCard(String creditCard) {
        try {
            return "############" + creditCard.substring(creditCard.length() - 4);
        }catch (Exception e){
            return "";
        }
    }
}
