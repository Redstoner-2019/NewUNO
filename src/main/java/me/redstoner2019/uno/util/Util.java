package me.redstoner2019.uno.util;

import java.util.Random;

public class Util {
    public static String stacktraceToString(StackTraceElement[] ste){
        String s = "";
        for(StackTraceElement st : ste) s+= st.toString();
        return s;
    }
    public static String generateRandomCode(int len){
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < len; i++){
            code.append(s.toCharArray()[random.nextInt(s.length() - 1)]);
        }
        return code.toString();
    }
}
