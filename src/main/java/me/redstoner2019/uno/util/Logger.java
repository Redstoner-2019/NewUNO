package me.redstoner2019.uno.util;

import java.util.Calendar;

public class Logger {
    private String prefix = "DEFAULT";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Logger(String prefix) {
        this.prefix = prefix;
    }
    public Logger(){}

    public void log(String msg){
        System.out.println(format(msg));
    }
    public void log(Object o){
        log(o.toString());
    }
    public void warn(String msg){
        System.err.println(format(msg));
    }
    public void warn(Object o){
        warn(o.toString());
    }
    public void err(String msg){
        System.err.println(format(msg));
    }
    public void err(Object o){
        err(o.toString());
    }
    private String format(String msg){
        Calendar calendar = Calendar.getInstance();
        return String.format("[%02d:%02d.%03d] ",calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.MILLISECOND)) + "[" + prefix + "]: " + msg;
    }
}
