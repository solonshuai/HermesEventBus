package com.etop.utils;

public class PlateUserIdUtil {
    public static final String INTENT_PLATE_CONFIG = "plateConfig";
    private static String UserId = "";
    public static String getUserId() {
        return UserId;
    }
    public static void setUserId(String userId) {
        UserId = userId;
    }
}
