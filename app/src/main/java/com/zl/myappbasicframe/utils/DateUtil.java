package com.zl.myappbasicframe.utils;

import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/6/8.
 */
public class DateUtil {

    public static final String[] MONTH = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};

    public static String[] WEEK = new String[]{"天", "一", "二", "三", "四", "五",
            "六"};

    private static final long ONE_SECOND = 1000;
    private static final long ONE_MINUTE = ONE_SECOND * 60;
    private static final long ONE_HOUR = ONE_MINUTE * 60;
    private static final long ONE_DAY = ONE_HOUR * 24;


    public static String string2String(String str, String format) {
        try {
            return new SimpleDateFormat(format).format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * String 转换 Date
     *
     * @param str
     * @param format
     * @return
     */
    public static Date string2Date(String str, String format) {
        try {
            return new SimpleDateFormat(format).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * Date（long） 转换 String
     *
     * @param time
     * @param format
     * @return
     */
    public static String date2String(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(time);
        return s;
    }

    /**
     * long 去除 时分秒 时分秒全部为0
     *
     * @param date
     * @return
     */
    public static long getYearMonthDay(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取目标时间和当前时间之间的差距
     *
     * @param date
     * @return
     */
    public static String getTimestampString(Date date) {
        Date curDate = new Date();
        long splitTime = curDate.getTime() - date.getTime();
        if (splitTime < (30 * ONE_DAY)) {
            if (splitTime > ONE_MINUTE) {
                return "刚刚";
            }
            if (splitTime < ONE_HOUR) {
                return String.format("%d分钟前", splitTime / ONE_MINUTE);
            }

            if (splitTime < ONE_DAY) {
                return String.format("%d小时前", splitTime / ONE_HOUR);
            }

            return String.format("%d天前", splitTime / ONE_DAY);
        }
        String result;
        result = "M月d日 HH:mm";
        return (new SimpleDateFormat(result, Locale.CHINA)).format(date);
    }

    /**
     * 24小时制 转换 12小时制
     *
     * @param time
     * @return
     */
    public static String time24To12(String time) {
        String str[] = time.split(":");
        int h = Integer.valueOf(str[0]);
        int m = Integer.valueOf(str[1]);
        String sx;
        if (h < 1) {
            h = 12;
            sx = "上午";
        } else if (h < 12) {
            sx = "上午";
        } else if (h < 13) {
            sx = "下午";
        } else {
            sx = "下午";
            h -= 12;
        }
        return String.format("%d:%02d%s", h, m, sx);
    }

    /**
     * Date 转换 HH
     *
     * @param date
     * @return
     */
    public static String date2HH(Date date) {
        return new SimpleDateFormat("HH").format(date);
    }

    /**
     * Date 转换 HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String date2HHmm(Date date) {
        return new SimpleDateFormat("HH:mm").format(date);
    }

    /**
     * Date 转换 HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String date2HHmmss(Date date) {
        return new SimpleDateFormat("HH:mm:ss").format(date);
    }

    /**
     * Date 转换 MM.dd
     *
     * @param date
     * @return
     */
    public static String date2MMdd(Date date) {
        return new SimpleDateFormat("MM.dd").format(date);
    }

    /**
     * Date 转换 yyyy.MM.dd
     *
     * @param date
     * @return
     */
    public static String date2yyyyMMdd(Date date) {
        return new SimpleDateFormat("yyyy.MM.dd").format(date);
    }

    /**
     * Date 转换 MM月dd日 星期
     *
     * @param date
     * @return
     */
    public static String date2MMddWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return new SimpleDateFormat("MM月dd日 星期").format(date)
                + WEEK[dayOfWeek - 1];
    }

    /**
     * Date 转换 yyyy年MM月dd日 星期
     *
     * @param date
     * @return
     */
    public static String date2yyyyMMddWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return new SimpleDateFormat("yyyy年MM月dd日 星期").format(date)
                + WEEK[dayOfWeek - 1];
    }

    private static final String TAG = DateUtil.class.getSimpleName();

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault());
    public static SimpleDateFormat mDateFormat = new SimpleDateFormat(
            "yyyy年M月d日", Locale.getDefault());
    private final static SimpleDateFormat dateFormater = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm", Locale.getDefault());
    private final static SimpleDateFormat dateFormater3 = new SimpleDateFormat(
            "HH:mm", Locale.getDefault());
    private static final long MINUTE_AGO = 1 * 60 * 1000; // 1分钟以前
    private static final long HOUR_AGO = 1 * 60 * 60 * 1000; // 1小时以前
    private static final long DAY_AGO = 24 * 60 * 60 * 1000; // 24小时以前
    private static final long FIVE_DAY_AGO = 5 * 24 * 60 * 60 * 1000; // 5天以前
    private static final String HTML_FONT_STYLE_START = "<font color='#ffffff'><big><big>";
    private static final String HTML_FONT_STYLE_END = "</big></big></font>";

    /**
     * 获取年
     *
     * @param timestamp ：时间戳。1970年1月1日后的毫秒数
     * @return
     */
    public static int getYear(long timestamp) {
        Calendar currentCalendar = Calendar.getInstance(Locale.CHINA);
        currentCalendar.setTimeInMillis(timestamp);
        return currentCalendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     *
     * @param timestamp ：时间戳。1970年1月1日后的毫秒数
     * @return
     */
    public static int getMonth(long timestamp) {
        Calendar currentCalendar = Calendar.getInstance(Locale.CHINA);
        currentCalendar.setTimeInMillis(timestamp);
        return currentCalendar.get(Calendar.MONTH);
    }

    /**
     * 获取几号，每月的第一天返回1
     *
     * @param timestamp ：时间戳。1970年1月1日后的毫秒数
     * @return
     */
    public static int getDayOfMonth(long timestamp) {
        Calendar currentCalendar = Calendar.getInstance(Locale.CHINA);
        currentCalendar.setTimeInMillis(timestamp);
        return currentCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算两个时间的的时间差
     *
     * @param timestamp1
     * @param timestamp2
     * @return xx小时xx分
     */
    public static String getTimeDiff(long timestamp1, long timestamp2) {

        long diff = Math.abs(timestamp1 - timestamp2);// 这样得到的差值是微秒级别
        long hours = diff / (1000 * 60 * 60);
        long minutes = (diff - hours * (1000 * 60 * 60)) / (1000 * 60);
        return hours + "小时" + minutes + "分";
    }

    /**
     * 计算两个时间的的时间差
     *
     * @param timestamp1
     * @param timestamp2
     * @return xx小时
     */
    public static String getTimeHourDiff(long timestamp1, long timestamp2) {

        String dataStr = "";
        long hours;
        long minutes;

        long diffMinutes = Math.abs(timestamp1 / MINUTE_AGO - timestamp2
                / MINUTE_AGO)
                * MINUTE_AGO;// 这样得到的差值是分钟级别
        long diffSeconds = Math.abs(timestamp1 / 1000 - timestamp2 / 1000) * 1000;// 这样得到的差值是
        // 秒
        // 级别

        hours = diffMinutes / HOUR_AGO;
        minutes = (diffMinutes - hours * HOUR_AGO) / MINUTE_AGO;

        if (1 <= hours) {
            if (0 == minutes) {
                dataStr = hours + "小时";
            } else {
                dataStr = hours + "小时" + minutes + "分";
            }
        } else {

            minutes = diffMinutes / MINUTE_AGO;
            if (1 <= minutes) {
                dataStr = minutes + "分钟";
            } else {
                long seconds = diffSeconds / 1000;
                dataStr = seconds + "秒";
            }
        }
        return dataStr;
    }

    /**
     * 返回当前是 周几
     *
     * @param timestamp
     * @return
     */
    public static String getWeek(long timestamp) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(timestamp);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 格式化日期
     *
     * @param template  ：格式。"yyyy-MM-dd HH:mm"等
     * @param timestamp ：时间戳。1970年1月1日后的毫秒数
     * @return
     */
    public static String format(String template, long timestamp) {
        SimpleDateFormat formate = new SimpleDateFormat(template, Locale.CHINA);
        return formate.format(new Date(timestamp));
    }

    public static String format1(long timestamp) {
        SimpleDateFormat formate = new SimpleDateFormat("y年M月d日", Locale.CHINA);
        return formate.format(new Date(timestamp));
    }

    public static String format2(long timestamp) {
        SimpleDateFormat formate = new SimpleDateFormat("y年M月d日", Locale.CHINA);
        return formate.format(new Date(timestamp * 1000));
    }

    /**
     * 计算两个时间的间隔天数
     *
     * @param timestamp1 毫秒数
     * @param timestamp2 毫秒数
     * @return
     */
    public static int getBetweenDays(long timestamp1, long timestamp2) {
        // 当前时间
        Calendar currentCalendar = Calendar.getInstance(Locale.CHINA);
        currentCalendar.setTimeInMillis(timestamp1);
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);

        // 预产期
        Calendar birthdayCalendar = Calendar.getInstance(Locale.CHINA);
        birthdayCalendar.setTimeInMillis(timestamp2);
        birthdayCalendar.set(Calendar.HOUR_OF_DAY, 0);
        birthdayCalendar.set(Calendar.MINUTE, 0);
        birthdayCalendar.set(Calendar.SECOND, 0);
        birthdayCalendar.set(Calendar.MILLISECOND, 0);

        long absTime = Math.abs(currentCalendar.getTimeInMillis()
                - birthdayCalendar.getTimeInMillis());

        int day = (int) (absTime / (24 * 60 * 60 * 1000L));

        return day;
    }

    /**
     * 大于2天显示完整时间 2天内显示昨天 HH:mm 1天内显示 HH:mm
     *
     * @param time 时间秒数
     * @return
     */
    public static String formatTimestampForNotice(long time) {
        long currentTime = System.currentTimeMillis();
        long serverTime = time * 1000;
        long intervalTime = currentTime - serverTime;
        if (intervalTime / DAY_AGO < 1) {
            return dateFormater3.format(new Date(serverTime));
        } else if (intervalTime / DAY_AGO < 2) {
            return "昨天 " + dateFormater3.format(new Date(serverTime));
        } else {
            return dateFormater.format(new Date(serverTime));
        }
    }

    /**
     * 大于2天显示完整时间 2天内显示昨天 HH:mm 1天内显示 HH:mm
     *
     * @param time 时间秒数
     * @return
     */
    public static String formatTimestampForNoticeMills(long time) {

        long curTime = System.currentTimeMillis();
        long yesterTime = curTime - DAY_AGO;

        if (isSameDay(curTime, time)) {
            return "今天" + dateFormater3.format(new Date(time));
        } else if (isSameDay(yesterTime, time)) {
            return "昨天 " + dateFormater3.format(new Date(time));
        } else {
            return dateFormater.format(new Date(time));
        }
    }

    public static String timestempToStringMore(String time) {
        return dateFormater.format(new Date(parseLong(time) * 1000));
    }

    public static String timestempToStringMore2(String time) {
        return dateFormater.format(new Date(parseLong(time)));
    }

    /**
     * 毫秒数转时间字符(2011-01-01格式)
     *
     * @param time
     * @return
     */
    public static String timestempToString(long time) {
        try {
            return FORMAT.format(new Date(time));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 比较是否是同一天
     *
     * @return true相同的一天 false不同的一天
     */
    public static boolean isSameDay(long time) {
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault());
        String sp_time = sf.format(time);
        String current_time = sf.format(timeMillis);

        if (!sp_time.equals(current_time)) {
            // 不同一天
            return false;
        }
        return true;
    }

    public static boolean isSameDay(String remoteTime, long localTime) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault());
        String sp_time = sf.format(localTime);
        if (!sp_time.equals(remoteTime)) {
            // 不同一天
            return false;
        }
        return true;
    }

    public static boolean isSameDay(long remoteTime, long localTime) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault());
        String r_time = sf.format(remoteTime);
        String l_time = sf.format(localTime);
        if (!l_time.equals(r_time)) {
            // 不同一天
            return false;
        }
        return true;
    }

    /**
     * 是否为同一个月
     *
     * @param remoteTime
     * @param localTime
     * @return
     */
    public static boolean isSameMonth(long remoteTime, long localTime) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM",
                Locale.getDefault());
        String r_time = sf.format(remoteTime);
        String l_time = sf.format(localTime);
        if (!l_time.equals(r_time)) {
            // 不同月
            return false;
        }
        return true;
    }

    /**
     * 获取当天日期 20130901
     *
     * @param timeMills
     * @return
     */
    public static String getDateString(long timeMills) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd",
                Locale.CHINA);
        return dateFormat.format(new Date(timeMills));
    }

    /**
     * 返回当前日期毫秒数
     *
     * @return
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取毫秒数
     *
     * @param dateString yyyy-MM-dd
     * @return long mills
     */
    public static long getTimeMills(String dateString) {
        String date[] = dateString.split("-");
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        try {
            calendar.set(Calendar.YEAR, parseInt(date[0]));
            calendar.set(Calendar.MONTH, parseInt(date[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, parseInt(date[2]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }

    public static long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    // ======================好用的方法=================================

    /**
     * 时间戳--->String<br/>
     * add zhangxin
     */
    public static String timestampToString(Integer time, String format) {
        String formatStr = format;
        String timeStr = "";
        long temp = (long) time * 1000;// int转long时，先进行转型再进行计算，否则会是计算结束后在转型
        Timestamp ts = new Timestamp(temp);
        if (TextUtils.isEmpty(formatStr)) {
            formatStr = "yyyy-MM-dd HH:mm:ss";
        }
        DateFormat dateFormat = new SimpleDateFormat(formatStr);
        try {
            timeStr = dateFormat.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeStr;
    }

    /**
     * 根据时间戳计算距离当前时间<br/>
     * add zhangxin
     */
    public static String getPassByTime(long time) {
        long currentTime = System.currentTimeMillis();
        long serverTime = time * 1000;
        long intervalTime = currentTime - serverTime;
        if (intervalTime < MINUTE_AGO) {
            long l = (intervalTime % (1000 * 60)) / 1000;
            if (l > 0)
                return l + "秒前";
            else
                return "1秒前";
        } else if (intervalTime < HOUR_AGO) {
            return (intervalTime % (1000 * 60 * 60)) / (1000 * 60) + "分钟前";
        } else if (intervalTime < DAY_AGO) {
            return (intervalTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
                    + "小时前";
        } else if (intervalTime < FIVE_DAY_AGO) {
            return intervalTime / (1000 * 60 * 60 * 24) + "天前";
        } else {
            return FORMAT.format(new Date(serverTime));
        }
    }


}
