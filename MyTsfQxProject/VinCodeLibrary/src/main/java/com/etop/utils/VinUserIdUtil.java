package com.etop.utils;

public class VinUserIdUtil {
    public static final String INTENT_VIN_CONFIG = "vincodeConfig";
    private static String UserId = "";
    public static String getUserId() {
        return UserId;
    }
    public static void setUserId(String userId) {
        UserId = userId;
    }
}
