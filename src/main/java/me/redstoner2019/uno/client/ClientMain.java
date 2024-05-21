package me.redstoner2019.uno.client;

import me.redstoner2019.uno.Main;
import me.redstoner2019.uno.plugin.Plugin;
import me.redstoner2019.uno.util.Logger;
import me.redstoner2019.uno.util.Player;

public class ClientMain extends Plugin {
    @Override
    public void onEnable() {
        Main.logger.log("Initializing Client");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Main.onLobbyCreateEvent(new Player("",""), "test");
    }
}
