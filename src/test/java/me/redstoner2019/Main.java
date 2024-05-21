package me.redstoner2019;

import me.redstoner2019.uno.plugin.Plugin;
import me.redstoner2019.uno.plugin.events.*;
import me.redstoner2019.uno.util.Logger;
import me.redstoner2019.uno.util.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main extends Plugin {
    public static List<Listener> registered = new ArrayList<>();
    @Override
    public void onEnable() {
        System.out.println("Initializing");
        registered.add(new Test());
        callEvents(new LobbyCreateEvent(new Player("",""),"BOOBS"));
    }

    public static void callEvents(Event e){
        for(Listener listener : registered){
            Method[] methods = listener.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(EventHandler.class)) {
                    method.setAccessible(true);
                    if(method.getParameterCount() == 1){
                        if(method.getParameterTypes()[0] == e.getClass()){
                            try {
                                method.invoke(listener,e);
                            } catch (IllegalAccessException | InvocationTargetException ex) {
                                new Logger("TEST").warn("Couldnt call method");
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main().onEnable();
    }
}
