package com.zl.myappbasicframe.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 根据当前时间返回一年的日期集合
 *
 * @author 桐丘
 */
public class YearMouthDayWeek {
    public static String[] WEEK = new String[]{"天", "一", "二", "三", "四", "五",
            "六"};

    public static List<Month> mouth;
    public static Month themonth;
    //保存第一次的年
    public static int svaeYear;
    //保存第一次的月
    public static int svaeMonth;
    //保存第一次的日期
    public static int svaeDate;

    /**
     * 是否增加年
     */
    private static boolean isAdd = false;

    /**
     * 保存当前的月份
     */
    private static int currentMouth;

    /**
     * 保存年
     */
    private static int currentYear;

    /**
     * 保存当前月 共有多少天
     */
    private static int theMouthDay;

    private static int temp;

    /**
     * 存放周几
     */
    private static int currentweek;


    /**
     * 返回12个月的数据
     *
     * @return
     */
    public static List<Month> getmoth() {
        //第一次进来改为false
        isAdd = false;
        //获得当前时间的年月日   如： 20151111
        String dateString = DateUtil.getDateString(System.currentTimeMillis());
        System.out.println(dateString);
        //取出月份
        currentMouth = Integer.parseInt(dateString.substring(4, 6));
        //取出年份
        currentYear = Integer.parseInt(dateString.substring(0, 4));
        //本月多少天
        theMouthDay = getTheMouthDay(currentYear, currentMouth);
        //得到本月第一天是周几
        Date firstDate = DateUtil.string2Date(currentYear + " " + currentMouth + " " + 1, "yyyy MM d");
        String yyyyMMddWeek = DateUtil.date2yyyyMMddWeek(firstDate);
        System.out.print("------------------------" + yyyyMMddWeek + "------------------------");
        String weekday = yyyyMMddWeek.substring(yyyyMMddWeek.length() - 1);
        //创建存放天的集合
        themonth = new Month(currentYear, currentMouth);
        themonth.days = new ArrayList<Day>();
        currentweek = 0;
        for (int i = 0; i < 7; i++) {
            if (weekday.equals(WEEK[i])) {
                currentweek = i;
                break;
            }
        }
        temp = currentweek;
        for (int i = 0; i < 42; i++) {
            //不会显示的
            if (i < currentweek) {
                Day e = new Day(0, 0);
                themonth.days.add(e);
            } else if (i >= currentweek + theMouthDay) {
                //超出了当前月的天数
                Day e = new Day(0, 0);
                themonth.days.add(e);
            } else {

                Day e = new Day(temp % 7, i + 1 - currentweek);// 存放周几 和月份的第几天开始计算天数
                themonth.days.add(e);
                temp++;
            }
        }

        //把第一月的集合添加到月份集合中
        mouth = new ArrayList<Month>();
        mouth.add(themonth);

        //下个月的第一天 周几  月份增加
        currentweek = temp % 7;
        temp = currentweek;
        currentMouth++;
        //计算下一个月的 天数 并添加到集合中
        for (int j = 0; j < 11; j++) {
            if (currentMouth <= 12) {//没有跨年
                String strMounth;
                if (currentMouth < 10) {
                    // 月份为两位
                    strMounth = "0" + String.valueOf(currentMouth);
                } else {
                    strMounth = String.valueOf(currentMouth);
                }

                addList(strMounth, currentYear, currentweek, temp);

            } else {
                if (!isAdd) {
                    //第一次进来才增加
                    isAdd = true;
                    currentMouth = 1;
                    currentYear++;
                }
                // 月份为两位
                String strMounth;
                if (currentMouth < 10) {
                    strMounth = "0" + String.valueOf(currentMouth);
                } else {
                    strMounth = String.valueOf(currentMouth);
                }
                //当月总天数
                addList(strMounth, currentYear, currentweek, temp);
            }

        }

        initMonth(mouth);

        return mouth;
    }

    /**
     * 返回到当前日期，前12个月的数据
     *
     * @return
     */
    public static List<Month> getLastmoth() {
        //第一次进来改为false
        isAdd = false;
        //获得当前时间的年月日   如： 20151111
        String dateString = DateUtil.getDateString(System.currentTimeMillis());
        System.out.println(dateString);
        //取出月份
        currentMouth = Integer.parseInt(dateString.substring(4, 6))+1;
        //取出年份
        currentYear = Integer.parseInt(dateString.substring(0, 4))-1;
        //本月多少天
        theMouthDay = getTheMouthDay(currentYear, currentMouth);
        //得到本月第一天是周几
        Date firstDate = DateUtil.string2Date(currentYear + " " + currentMouth + " " + 1, "yyyy MM d");
        String yyyyMMddWeek = DateUtil.date2yyyyMMddWeek(firstDate);
        System.out.print("------------------------" + yyyyMMddWeek + "------------------------");
        String weekday = yyyyMMddWeek.substring(yyyyMMddWeek.length() - 1);
        //创建存放天的集合
        themonth = new Month(currentYear, currentMouth);
        themonth.days = new ArrayList<Day>();
        currentweek = 0;
        for (int i = 0; i < 7; i++) {
            if (weekday.equals(WEEK[i])) {
                currentweek = i;
                break;
            }
        }
        temp = currentweek;
        for (int i = 0; i < 42; i++) {
            //不会显示的
            if (i < currentweek) {
                Day e = new Day(0, 0);
                themonth.days.add(e);
            } else if (i >= currentweek + theMouthDay) {
                //超出了当前月的天数
                Day e = new Day(0, 0);
                themonth.days.add(e);
            } else {

                Day e = new Day(temp % 7, i + 1 - currentweek);// 存放周几 和月份的第几天开始计算天数
                themonth.days.add(e);
                temp++;
            }
        }

        //把第一月的集合添加到月份集合中
        mouth = new ArrayList<Month>();
        mouth.add(themonth);

        //下个月的第一天 周几  月份增加
        currentweek = temp % 7;
        temp = currentweek;
        currentMouth++;
        //计算下一个月的 天数 并添加到集合中
        for (int j = 0; j < 11; j++) {
            if (currentMouth <= 12) {//没有跨年
                String strMounth;
                if (currentMouth < 10) {
                    // 月份为两位
                    strMounth = "0" + String.valueOf(currentMouth);
                } else {
                    strMounth = String.valueOf(currentMouth);
                }

                addList(strMounth, currentYear, currentweek, temp);

            } else {
                if (!isAdd) {
                    //第一次进来才增加
                    isAdd = true;
                    currentMouth = 1;
                    currentYear++;
                }
                // 月份为两位
                String strMounth;
                if (currentMouth < 10) {
                    strMounth = "0" + String.valueOf(currentMouth);
                } else {
                    strMounth = String.valueOf(currentMouth);
                }
                //当月总天数
                addList(strMounth, currentYear, currentweek, temp);
            }

        }

        initMonth(mouth);

        return mouth;
    }


    /**
     * 对数据整合
     */
    private static void initMonth(List<Month> mouths) {
        //遍历月
        for (int i = 0; i < mouths.size(); i++) {
            Month month = mouths.get(i);
            List<Day> dayArr = month.days;
            //取出第六行的第一个元素
            Day day = dayArr.get(35);
            if (day.day == 0) {
                for (int j = 0; j < 7; j++) {
                    //移除本行数据
                    month.days.remove(35);
                }

            }

        }

    }

    /**
     * 集合添加参数
     */
    protected static void addList(String currentMouth, int currentYear,
                                  int currentweek, int temp) {
        int theMouthDay;
        theMouthDay = getTheMouthDay(currentYear, Integer.parseInt(currentMouth));
        //创建集合
        themonth = new Month(currentYear, Integer.parseInt(currentMouth));
        themonth.days = new ArrayList<Day>();
        //添加元素
        for (int i = 0; i < 42; i++) {
            //不会显示的
            if (i < currentweek) {
                Day e = new Day(0, 0);
                themonth.days.add(e);
            } else if (i >= currentweek + theMouthDay) {
                //超出了当前月的天数
                Day e = new Day(0, 0);
                themonth.days.add(e);
            } else {

                Day e = new Day(temp % 7, i + 1 - currentweek);// 存放周几 和月份的第几天
                themonth.days.add(e);
                temp++;
            }
        }
        //添加到月份中
        mouth.add(themonth);
        //初始化数据
        YearMouthDayWeek.currentweek = temp % 7;
        YearMouthDayWeek.temp = YearMouthDayWeek.currentweek;
        YearMouthDayWeek.currentMouth++;

    }

    //实现序列化
    public static class Month implements Serializable {

        private static final long serialVersionUID = 1567898766789L;
        //保存本月数据
        public List<Day> days;
        //保存年
        public int year;
        //保存月
        public int month;

        public Month(int year, int month) {
            super();
            this.year = year;
            this.month = month;
        }
    }

    public static class Day implements Serializable {
        private static final long serialVersionUID = 1987789876L;
        //周几
        public int weekDay;
        //本月第几天
        public int day;

        public Day(int weekDay, int day) {
            super();
            this.weekDay = weekDay;
            this.day = day;
        }

        @Override
        public String toString() {
            return "Day [weekDay=" + weekDay + ", day=" + day + "]";
        }
    }


    /**
     * 输入日期 返回后面的月份的天数
     */
    public static int getTheMouthDay(int year, int mouth) {
        int y = year;
        int n = mouth;
        int total = 0;
        switch (n) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println(n + "月份有：31天");
                total = 31;
                break;
            // 对于2月份需要判断是否为闰年
            case 2:
                if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)) {
                    System.out.println(n + "月份有：29天");
                    total = 29;
                    break;
                } else {
                    System.out.println(n + "月份有：28天");
                    total = 28;
                    break;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println(n + "月份有：30天");
                total = 30;
                break;

        }
        return total;
    }

}
