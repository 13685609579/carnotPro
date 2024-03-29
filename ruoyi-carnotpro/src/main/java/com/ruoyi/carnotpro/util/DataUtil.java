package com.ruoyi.activity.util;

import java.util.HashMap;

public class DataUtil {

    public static final String ZERO = "zero";
    public static final String NEGATIVE = "negative";
    public static final String SPACE = " ";
    public static final String MILLION = "million";
    public static final String THOUSAND = "thousand";
    public static final String HUNDRED = "hundred";
    public static final String[] INDNUM = {"zero", "one", "two", "three", "four", "five", "six",
            "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen",
            "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    public static final String[] DECNUM = {"twenty", "thirty", "forty", "fifty", "sixty",
            "seventy", "eighty", "ninety"};

    //数字转英文
    public static String numberToWordsConverter(int i){
        StringBuilder sb = new StringBuilder();
        if(i == 0) {
            return ZERO;
        }
        if(i < 0) {
            sb.append(NEGATIVE).append(SPACE);
            i *= -1;
        }
        if(i >= 1000000) {
            sb.append(numFormat(i / 1000000)).append(SPACE).append(MILLION).append(SPACE);
            i %= 1000000;
        }
        if(i >= 1000) {
            sb.append(numFormat(i / 1000)).append(SPACE).append(THOUSAND).append(SPACE);
            i %= 1000;
        }
        if(i < 1000){
            sb.append(numFormat(i));
        }
        return sb.toString();
    }

    public static String numFormat(int i) {
        StringBuilder sb = new StringBuilder();
        if(i >= 100) {
            sb.append(INDNUM[i / 100]).append(SPACE).append(HUNDRED).append(SPACE);
        }
        i %= 100;
        if(i != 0) {
            if(i >= 20) {
                sb.append(DECNUM[i / 10 -2]).append(SPACE);
                if(i % 10 != 0) {
                    sb.append(INDNUM[i % 10]);
                }
            }else {
                sb.append(INDNUM[i]);
            }
        }
        return sb.toString();
    }

    //英文转数字
    public int parse(String str) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("zero", 0);
        hm.put("one", 1);
        hm.put("two", 2);
        hm.put("three", 3);
        hm.put("four", 4);
        hm.put("five", 5);
        hm.put("six", 6);
        hm.put("seven", 7);
        hm.put("eight", 8);
        hm.put("nine", 9);
        hm.put("ten", 10);
        hm.put("eleven", 11);
        hm.put("twelve", 12);
        hm.put("thirteen", 13);
        hm.put("fourteen", 14);
        hm.put("fifteen", 15);
        hm.put("sixteen", 16);
        hm.put("seventeen", 17);
        hm.put("eighteen", 18);
        hm.put("nineteen", 19);
        hm.put("twenty", 20);
        hm.put("thirty", 30);
        hm.put("forty", 40);
        hm.put("fifty", 50);
        hm.put("sixty", 60);
        hm.put("seventy", 70);
        hm.put("eighty", 80);
        hm.put("ninety", 90);
        hm.put("hundred", 100);
        hm.put("thousand", 1000);
        hm.put("million", 1000000);
        int i = 0;
        int b = 0;
        int c = 0;
        String[] k = str.split(" ");
        for (String string : k) {
            if("hundred".equals(string)){
                i *= hm.get("hundred");
            }else if("thousand".equals(string)){
                b = i;
                b *= hm.get("thousand");
                i = 0;
            }else if("million".equals(string)){
                c = i;
                c *= hm.get("million");
                i = 0;
            }else if("negative".equals(string)){
                i = 0;
            }else {
                i += hm.get(string);
            }
        }
        i += c + b;
        for (String string2 : k) {
            if("negative".equals(string2)){
                i = -i;
            }
        }
        return i;
    }

    /**
     * 数字转英文
     * @param str
     * @return
     */
    public static String firstToUpperCase(String str){
        char firstChar = Character.toUpperCase(str.charAt(0)); // 获取首字母并转换为大写
        String restOfStr = str.substring(1).toLowerCase(); // 获取除了首字母之外的部分并全部转换为小写
        String result = ("" + firstChar + restOfStr).replace(" ", ""); // 合并结果
        return result;
    }

    /**
     * 数字转英文驼峰格式
     * @param i
     * @return
     */
    public static String numberToStrUpperCase(int i){
        String firstToUpperCase1 = "";
        String firstToUpperCase2 = "";
        if(i<=20){
            firstToUpperCase1 = DataUtil.firstToUpperCase(DataUtil.numberToWordsConverter(i));
        }
        if(i>20){
            firstToUpperCase1 = DataUtil.firstToUpperCase(DataUtil.numberToWordsConverter(20));
            firstToUpperCase2 = DataUtil.firstToUpperCase(DataUtil.numberToWordsConverter(i-20));
        }
        String numberUpperCase = firstToUpperCase1 + firstToUpperCase2;
        return numberUpperCase;
    }

}
