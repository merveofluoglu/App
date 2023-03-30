package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * It contains a bunch of static methods that return true or false depending on whether the input string matches a certain
 * regex
 */
public class Validator {
    public static boolean isValidGuid(String guid) {
        return guid.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
    }
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return !StringUtils.isEmpty(password);
    }

    public static boolean isValidCardNumber(String cardNumber) {return cardNumber.matches("(^4[0-9]{12}(?:[0-9]{3})?$)|(^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$)|(3[47][0-9]{13})|(^3(?:0[0-5]|[68][0-9])[0-9]{11}$)|(^6(?:011|5[0-9]{2})[0-9]{12}$)|(^(?:2131|1800|35\\d{3})\\d{11}$)");}

    public static boolean isValidCvv(String cvv) {
        return cvv.matches("^[0-9]{3,4}$");
    }

    public static boolean isValidReviewTitle(String title) {
        return !StringUtils.isEmpty(title);
    }

    public static boolean isValidReviewRating(String rating) {
        return Integer.parseInt(rating) >= 1
                && Integer.parseInt(rating) <= 5
                && !StringUtils.isEmpty(rating);
    }
    public static boolean isWordOrNumber(String val) {  return val.matches("^[a-zA-Z0-9_ ]*$"); }

    public static boolean isAlphabetical(String val) {  return val.matches("^[a-zA-Z]*$"); }

    public static boolean isListOfWordOrNumber(List<String> val) {
          if(val.size()==0){
            return false;
        }
        for (String temp : val) {
            if(!temp.matches("^[a-zA-Z0-9_ ]*$")){
                return false;
            }
        }
        return true;
    }

}
