package me.redstoner2019;

import me.redstoner2019.uno.plugin.EventHandler;
import me.redstoner2019.uno.util.Player;

public class Test extends EventHandler {
    @Override
    public boolean onLobbyCreateEvent(Player p, String customCode){
        return true;
    }
}
