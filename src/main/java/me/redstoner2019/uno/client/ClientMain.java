package me.redstoner2019.uno.client;

import me.redstoner2019.uno.Main;
import me.redstoner2019.uno.client.gui.Application;
import me.redstoner2019.uno.plugin.Plugin;
import me.redstoner2019.uno.plugin.events.LobbyCreateEvent;
import me.redstoner2019.uno.util.Logger;
import me.redstoner2019.uno.util.Player;

public class ClientMain extends Plugin {
    public static Logger logger = new Logger("CLIENT");
    @Override
    public void onEnable() {
        Main.logger.log("Initializing Client");
        Application.startGUI();
    }
}
