package com.ychornyi.seasontracker;

/**
 * Created by y.chornyi on 17.06.2016.
 */
public class Utils {
    private final int key = 999;
    public static String encode(String input){
        input = input.toLowerCase();
        String output = "t";
        Character ch;
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            ch = (char)(ch - key);
            output = output + (ch);
        }
        return output;
    }

    public static String decode (String input){
        input = input.toLowerCase();
        String output = "";
        Character ch;
        for (int i = 1; i < input.length(); i++) {
            ch = input.charAt(i);
            ch = (char)(ch + key);
            output = output + (ch);
        }
        return output;
    }
}
