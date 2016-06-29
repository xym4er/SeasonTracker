package com.ychornyi.seasontracker;

import android.util.Base64;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by y.chornyi on 17.06.2016.
 */
public class Utils {
    private static Map<Character, String> encryptMap = new HashMap<Character, String>() {
        {
            put('й', "01");
            put('ц', "02");
            put('у', "03");
            put('к', "04");
            put('е', "05");
            put('н', "06");
            put('г', "07");
            put('ш', "08");
            put('щ', "09");
            put('з', "10");
            put('х', "11");
            put('ъ', "12");
            put('ф', "13");
            put('ы', "14");
            put('в', "15");
            put('а', "16");
            put('п', "17");
            put('р', "18");
            put('о', "19");
            put('л', "20");
            put('д', "21");
            put('ж', "22");
            put('э', "23");
            put('я', "24");
            put('ч', "25");
            put('с', "26");
            put('м', "27");
            put('и', "28");
            put('т', "29");
            put('ь', "30");
            put('б', "31");
            put('ю', "32");
            put('ё', "33");
            put(' ', "34");
            put('.', "35");
            put(',', "36");
            put('!', "38");

        }};
    private static Map<String, Character> decryptMap = new HashMap<String, Character>() {
        {
            put("01", 'й');
            put("02", 'ц');
            put("03", 'у');
            put("04", 'к');
            put("05", 'е');
            put("06", 'н');
            put("07", 'г');
            put("08", 'ш');
            put("09", 'щ');
            put("10", 'з');
            put("11", 'х');
            put("12", 'ъ');
            put("13", 'ф');
            put("14", 'ы');
            put("15", 'в');
            put("16", 'а');
            put("17", 'п');
            put("18", 'р');
            put("19", 'о');
            put("20", 'л');
            put("21", 'д');
            put("22", 'ж');
            put("23", 'э');
            put("24", 'я');
            put("25", 'ч');
            put("26", 'с');
            put("27", 'м');
            put("28", 'и');
            put("29", 'т');
            put("30", 'ь');
            put("31", 'б');
            put("32", 'ю');
            put("33", 'ё');
            put("34", ' ');
            put("35", '.');
            put("36", ',');
            put("37", '?');
            put("38", '!');


        }};

    public static String encode(String src) {
        StringBuilder rez = new StringBuilder();
        rez.append('t');
        char[] srcChars = src.toLowerCase().toCharArray();
        for (int i = 0; i < srcChars.length; i++) {
            rez.append(getCode(srcChars[i]));
        }
        return rez.toString();
    }

    public static String decode (String src){
        StringBuilder rez = new StringBuilder();
        for (int i = 1; i < src.length(); i=i+2) {
            rez.append(getDeCode(src.substring(i,i+2)));
        }
        return rez.toString();
    }



    private static String getCode(char c) {
        return encryptMap.get(c);
    }
    private static Character getDeCode(String c) {
        return decryptMap.get(c);
    }
}
