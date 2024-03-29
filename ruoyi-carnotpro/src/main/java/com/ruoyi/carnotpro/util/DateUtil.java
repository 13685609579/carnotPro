package com.ruoyi.activity.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtil {

    public static void main(String[] args) {
        List<String> months = getMonthsBetween("2021-03", "2021-07");
        System.out.println(months);
        String firstDayOfMonth = firstDayOfMonth("2021-03");
        String lastDayOfMonth = lastDayOfMonth("2021-03");

        System.out.println("First day of month: " + firstDayOfMonth);
        System.out.println("Last day of month: " + lastDayOfMonth);
        System.out.println("getYearMonth(): " + getYearMonth());
        System.out.println("getBeforeYearMonth(): " + getBeforeYearMonth());

        String lastMonth = getLastMonth(1);
        String lastTwoMonth = getLastMonth(2);
        String lastThreeMonth = getLastMonth(3);
        System.out.println("lastMonth: " + lastMonth);
        System.out.println("lastTwoMonth: " + lastTwoMonth);
        System.out.println("lastThreeMonth: " + lastThreeMonth);
    }

    public static List<String> getMonthsBetween(String startMonth, String endMonth) {
        List<String> months = new ArrayList<>();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        int startYear = Integer.parseInt(startMonth.split("-")[0]);
        int startMonthValue = Integer.parseInt(startMonth.split("-")[1]) - 1;
        int endYear = Integer.parseInt(endMonth.split("-")[0]);
        int endMonthValue = Integer.parseInt(endMonth.split("-")[1]) - 1;

        startCalendar.set(startYear, startMonthValue, 1);
        endCalendar.set(endYear, endMonthValue, 1);

        Calendar tempCalendar = (Calendar) startCalendar.clone();
        while (tempCalendar.before(endCalendar) || tempCalendar.equals(endCalendar)) {
            int year = tempCalendar.get(Calendar.YEAR);
            int month = tempCalendar.get(Calendar.MONTH) + 1;
            String monthFormatted = String.format("%04d-%02d", year, month);
            months.add(monthFormatted);
            tempCalendar.add(Calendar.MONTH, 1);
        }
        return months;
    }

    //获取指定月份第一天
    public static String firstDayOfMonth(String month){
        YearMonth yearMonth = YearMonth.parse(month, DateTimeFormatter.ofPattern("yyyy-MM"));
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateAsString = formatter.format(firstDayOfMonth);
        return dateAsString;
    }

    //获取指定月份最后一天
    public static String lastDayOfMonth(String month){
        YearMonth yearMonth = YearMonth.parse(month, DateTimeFormatter.ofPattern("yyyy-MM"));
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateAsString = formatter.format(lastDayOfMonth);
        return dateAsString;
    }

    //获取当前时间精确到月份
    public static String getYearMonth(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM"); // 设置日期时间格式
        String formattedDate = currentDate.format(formatter); // 格式化日期
        return formattedDate;
    }

    //获取一年前时间精确到月份
    public static String getBeforeYearMonth(){
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        // 设置当前时间为一年前
        calendar.add(Calendar.MONTH, -11);
        // 格式化日期为只包含月份的字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String monthOnly = sdf.format(calendar.getTime());
        return monthOnly;
    }

    public static String getLastMonth(int i){
        LocalDate today = LocalDate.now(); // 获取当前日期
        LocalDate lastMonth = today.minusMonths(i);
        String lastMonths = lastMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        return lastMonths;
    }

}
