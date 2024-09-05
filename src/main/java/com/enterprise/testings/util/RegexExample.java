package com.enterprise.testings.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author max.gun
 * @since 05/09/2024
 */
public class RegexExample {

    public static boolean isValidEmail(String emailTarget) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(emailTarget);
        return matcher.find();
    }

    public static void main(String[] args) {
        String email = "max.gun17@gmail.com";
        boolean isValid = isValidEmail(email);
        System.out.println("Result: " + isValid);

        /**
         * input: "1?-12" => output: "12-12"
         * input: "?4-?1" => output: "04-21"
         * input: "?3-?1" => output: "03-31"
         * input: "02-3?" => output: "02-19"
         * input: "??-??" => output: "12-31"
         * input: "5?-??" => output: "xx-xx"
         *
         * "MM-DD"
         * "?M-DD"
         * "M?-DD"
         * "??-DD"
         * "MM-?D"
         * "?M-?D"
         * "M?-?D"
         * "??-?D"
         * "MM-D?"
         * "?M-D?"
         * "M?-D?"
         * "??-D?"
         * "MM-??"
         * "?M-??"
         * "M?-??"
         * "??-??"
         *
         * */

        /*String regex = "[^\\d]+";
        Pattern pattern = Pattern.compile(regex);

        String target = "There are 26 Shishlik and 153 Kebabs";
        Matcher matcher = pattern.matcher(target);

        while (matcher.find()) {
            System.out.println("RESULT: " + matcher.group());
        }*/
    }
}
