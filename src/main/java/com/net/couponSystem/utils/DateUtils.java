package com.net.couponSystem.utils;


import java.sql.Date;
import java.time.LocalDateTime;

public class DateUtils {
    public static Date toSqlDate(LocalDateTime time) {
        return Date.valueOf(time.toLocalDate());
    }
}
