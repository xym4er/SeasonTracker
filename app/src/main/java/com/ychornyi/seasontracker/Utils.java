package com.ychornyi.seasontracker;

/**
 * Created by y.chornyi on 17.06.2016.
 */
public class Utils {
    public static String translit(String input){
        String output = input.toLowerCase();
        output.replace("а","a");
        output.replace("б","b");
        output.replace("в","v");
        output.replace("г","g");
        output.replace("д","d");
        output.replace("е","e");
        output.replace("ё","yo");
        output.replace("ж","zh");
        output.replace("з","z");
        output.replace("и","i");
        output.replace("й","y");
        output.replace("к","k");
        output.replace("л","l");
        output.replace("м","m");
        output.replace("н","n");
        output.replace("о","a");
        output.replace("п","p");
        output.replace("р","r");
        output.replace("с","s");
        output.replace("т","t");
        output.replace("у","u");
        output.replace("ф","f");
        output.replace("х","h");
        output.replace("ц","c");
        output.replace("ч","ch");
        output.replace("ш","sh");
        output.replace("щ","sch");
        output.replace("ъ","");
        output.replace("ы","a");
        output.replace("ь","a");
        output.replace("э","a");
        output.replace("ю","a");
        output.replace("я","a");
        return output;
    }
}
