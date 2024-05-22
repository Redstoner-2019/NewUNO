package me.redstoner2019.uno.client;

import me.redstoner2019.client.AuthenticatorClient;
import me.redstoner2019.uno.Main;
import me.redstoner2019.uno.client.gui.Application;
import me.redstoner2019.uno.client.networking.ClientConnector;
import me.redstoner2019.uno.plugin.Plugin;
import me.redstoner2019.uno.util.Logger;

public class ClientMain extends Plugin {
    public static Logger logger = new Logger("CLIENT");
    public static ClientConnector connector = new ClientConnector();
    public static AuthenticatorClient authenticatorClient = new AuthenticatorClient();
    public static String TOKEN = "";

    @Override
    public void onEnable() {
        Main.logger.log("Initializing Client");
        Application.startGUI();
    }
}
