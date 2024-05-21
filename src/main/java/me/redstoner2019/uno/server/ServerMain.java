package me.redstoner2019.uno.server;

import me.redstoner2019.uno.Main;
import me.redstoner2019.uno.plugin.Plugin;
import me.redstoner2019.uno.server.networking.ServerConnector;
import me.redstoner2019.uno.util.Logger;

public class ServerMain extends Plugin {
    @Override
    public void onEnable() {
        Main.logger.log("Initializing Server");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                ServerConnector.start(8008);
            }
        });
        t.start();
    }
}
