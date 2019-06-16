package com.restaurant.management.utils;

import com.restaurant.management.exception.ExceptionMessage;
import com.restaurant.management.exception.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(phoneNumber);

        if (!matcher.matches()) {
            throw new ValidationException(ExceptionMessage.INVALID_PHONE_NUMBER.getMessage());
        }

        return matcher.matches();
    }
}
