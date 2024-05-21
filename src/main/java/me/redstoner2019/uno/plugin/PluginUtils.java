package me.redstoner2019.uno.plugin;

import java.lang.reflect.InvocationTargetException;

public class PluginUtils {
    public static Object loadClass(ClassLoader loader, String classname){
        try {
            return loader.loadClass(classname).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            return null;
        }
    }
}
