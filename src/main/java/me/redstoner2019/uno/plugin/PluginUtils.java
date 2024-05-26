package me.redstoner2019.uno.plugin;

import java.lang.reflect.InvocationTargetException;
import java.net.URLClassLoader;
import java.util.Arrays;

public class PluginUtils {
    public static Object loadClass(URLClassLoader loader, String classname){
        try {
            Object o = loader.loadClass(classname).getDeclaredConstructor().newInstance();
            /*System.out.println(Arrays.toString(o.getClass().getDeclaredMethods()));
            System.out.println(o.getClass().getDeclaredMethods().length);
            o.getClass().getDeclaredMethod("main",String[].class).invoke(o,new String[1]);*/
            return o;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
