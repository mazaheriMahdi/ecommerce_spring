package com.ui.ac.shop.ir.shop.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexController {
    private final static Pattern emailPattern;
    private final static Pattern cardNumberRegex;
    private final static Pattern cvv2Patter;
    private final static Pattern passwordPattern;
    private final static Pattern phoneNumberPattern;


    static {
        emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        cardNumberRegex = Pattern.compile("\\d{4}(-\\d{4}){3}");
        cvv2Patter = Pattern.compile("\\d{4}");
        passwordPattern = Pattern.compile(".{4,}");
        phoneNumberPattern = Pattern.compile("^0(\\d){10}");
    }

    public static boolean checkPassword(String password) {
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.find();
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
        return matcher.find();
    }

    public static boolean checkEmailRegex(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }

    public static boolean checkCardNumber(String cardNumber) {
        Matcher matcher = cardNumberRegex.matcher(cardNumber);
        return matcher.find();
    }

    public static boolean checkCvv2Pattern(String cvv2) {
        Matcher matcher = cvv2Patter.matcher(cvv2);
        return matcher.find();
    }

}
