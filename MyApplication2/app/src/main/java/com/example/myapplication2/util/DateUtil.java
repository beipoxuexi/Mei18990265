package com.example.myapplication2.util;
import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    @SuppressLint("SimpleDateFormat")
    public static String getNowDateTime(){
        SimpleDateFormat s_format = new SimpleDateFormat( "yyyyMMddhhmmss");
        return s_format.format(new Date());
    }

    @SuppressLint("SimpleDateFormat")
    public static String getNowTime(){
        SimpleDateFormat s_format = new SimpleDateFormat( "yyyyMMddhhmmss");
        return s_format.format(new Date());
    }

}
