package me.redstoner2019.uno.plugin;

import me.redstoner2019.uno.plugin.events.Listener;
import me.redstoner2019.uno.util.Logger;
import me.redstoner2019.uno.util.game.Card;

import java.util.ArrayList;
import java.util.List;

public class Plugin {
    private List<Listener> events = new ArrayList<>();
    private Logger logger = new Logger();
    public void onEnable(){

    }
    public void registerEvent(Listener e){
        events.add(e);
    }
    public void unregisterEvent(Listener e){
        events.remove(e);
    }

    public List<Listener> getEvents() {
        return events;
    }
    public void registerCard(Card c, int amount){
        Card.registerCard(c,amount);
    }
}
