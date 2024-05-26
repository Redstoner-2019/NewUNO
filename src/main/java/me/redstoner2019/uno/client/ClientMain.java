package me.redstoner2019.uno.client;

import me.redstoner2019.Version;
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
    public static String TOKEN = "n4vXMSe53ho9yIYL4F2wd4AxjGRF3B1Aq89+gJlm6d4tGRK0/bn8Tef1watF0nHv/l1vWOfIe2gFv33lmC53pjmqHQXFF4j8A1WVH2RiQkOck6vP5UKiEndnxdYsehJSmyVjeUJfi99rsgpnqOkE7rRc8m+CBdEUFLUGAPoQCSo=";
    public static Version version = Version.fromString("v1.0.0-alpha.1");
    public static Version uiVersion = Version.fromString("v1.0.0-alpha.1");

    @Override
    public void onEnable() {
        Main.logger.log("Initializing Client");
        Application.startGUI();
    }
}
