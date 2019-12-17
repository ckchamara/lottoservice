package com.bingo.lottoservice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static long parse(String format, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date).getTime();
    }
}