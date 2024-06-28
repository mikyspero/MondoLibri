package com.azienda.shop.utils;

public class StringUtils {
    public static String isNotBlank(String str) {
        return str != null && str.trim().length() > 0 ? str : null;
    };
}
