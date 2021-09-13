package com.net.couponSystem.filters;

public class FilterHelper {

    public static String getType(String url) {
        String[] step1 = url.split("/");
        return step1[1].toLowerCase();
    }
}