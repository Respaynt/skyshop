package org.skypro.skyshop.service;

public class ShopError {
    private static String code;
    private static String message;

    public ShopError(String code,String message) {
        this.code = code;
        this.message = message;
    }

    public static String getCode() {
        return code;
    }

    public static String getMessage() {
        return message;
    }
}
