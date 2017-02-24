package com.zl.myappbasicframe.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ray on 2016-05-13.
 */
public class TimeUtil {

    public static String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);

    }
    public static String getCurrentTimeForFileName(){
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd-HH-mm-ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);

    }

    public static String getCurrentdate(){
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);

    }
}
