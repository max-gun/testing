package com.enterprise.testings.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author max.gun
 * @since 05/09/2024
 */
public class RegexExample {
    public static void main(String[] args) {
        String regex = "[^\\d]+";
        Pattern pattern = Pattern.compile(regex);

        String target = "There are 26 Shishlik, so put it on the Mangal";
        Matcher matcher = pattern.matcher(target);

        boolean isExist = matcher.find();

        if (isExist) {
            System.out.println("RESULT: " + matcher.group());
        }
        else {
            System.out.println("Ain't found sh**!!");
        }
    }
}
