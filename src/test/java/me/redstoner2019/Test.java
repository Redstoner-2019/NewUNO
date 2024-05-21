package me.redstoner2019;

import me.redstoner2019.uno.plugin.events.EventHandler;
import me.redstoner2019.uno.plugin.events.Listener;
import me.redstoner2019.uno.plugin.events.LobbyCreateEvent;

public class Test implements Listener {
    @EventHandler
    public static void onJoinEvent(LobbyCreateEvent s){
        System.out.println(s.getCustomCode());
    }
    public static void test(){

    }
    @EventHandler
    public static void test2(){

    }
}
