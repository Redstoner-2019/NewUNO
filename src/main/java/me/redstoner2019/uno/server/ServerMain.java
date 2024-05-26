package me.redstoner2019.uno.server;

import me.redstoner2019.Version;
import me.redstoner2019.client.AuthenticatorClient;
import me.redstoner2019.uno.Main;
import me.redstoner2019.uno.plugin.Plugin;
import me.redstoner2019.uno.server.networking.ServerConnector;
import me.redstoner2019.uno.util.Logger;
import me.redstoner2019.uno.util.game.Lobby;

import java.util.HashMap;

public class ServerMain extends Plugin {
    public static String authIP = "localhost";
    public static int authPort = 8009;
    public static AuthenticatorClient authenticatorClient = new AuthenticatorClient();
    public static HashMap<String, Lobby> lobbies = new HashMap<>();
    public static Version version = Version.fromString("v1.0.0-alpha.1");
    @Override
    public void onEnable() {
        Main.logger.log("Initializing Server");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                ServerConnector serverConnector = new ServerConnector(8008);
            }
        });
        t.start();
        authenticatorClient.setAddress(authIP);
        authenticatorClient.setPort(authPort);
        authenticatorClient.setup();
    }
}
