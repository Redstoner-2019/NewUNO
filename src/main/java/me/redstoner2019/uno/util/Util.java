package me.redstoner2019.uno.util;

public class Util {
    public static String stacktraceToString(StackTraceElement[] ste){
        String s = "";
        for(StackTraceElement st : ste) s+= st.toString();
        return s;
    }
}
