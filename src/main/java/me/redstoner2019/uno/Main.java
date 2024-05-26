package me.redstoner2019.uno;

import me.redstoner2019.uno.client.ClientMain;
import me.redstoner2019.uno.plugin.Plugin;
import me.redstoner2019.uno.plugin.PluginUtils;
import me.redstoner2019.uno.plugin.events.Event;
import me.redstoner2019.uno.plugin.events.EventHandler;
import me.redstoner2019.uno.plugin.events.Listener;
import me.redstoner2019.uno.server.ServerMain;
import me.redstoner2019.uno.util.Logger;
import me.redstoner2019.uno.util.Util;
import me.redstoner2019.uno.util.game.Card;
import me.redstoner2019.uno.util.game.CardColor;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.redstoner2019.uno.util.game.CardColor.*;
import static me.redstoner2019.uno.util.game.CardNumerator.*;

public class Main {
    public static List<Plugin> activePlugins = new ArrayList<>();
    public static Logger logger = new Logger("UNO");
    public static String getVersion(){
        return "v2.0.0-alpha.1";
    }
    public static void main(String[] args) {
        File plugins = new File("plugins");
        if(!plugins.exists()) if(!plugins.mkdirs()) {
            logger.log("Failed to create plugin folder");
            return;
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String type = args[0];
                switch (type){
                    case "server": {
                        ServerMain main = new ServerMain();
                        main.onEnable();
                        break;
                    }
                    case "client": {
                        ClientMain main = new ClientMain();
                        main.onEnable();
                        break;
                    }
                }
            }
        });
        t.start();

        if(plugins.listFiles() == null){
            logger.err("Error reading plugins: Plugins list is null");
            return;
        }
        for(CardColor c : List.of(RED,GREEN,BLUE,YELLOW)){
            Card.registerCard(new Card(c,ZERO),1);
            Card.registerCard(new Card(c,ONE),2);
            Card.registerCard(new Card(c,TWO),2);
            Card.registerCard(new Card(c,THREE),2);
            Card.registerCard(new Card(c,FOUR),2);
            Card.registerCard(new Card(c,FIVE),2);
            Card.registerCard(new Card(c,SIX),2);
            Card.registerCard(new Card(c,SEVEN),2);
            Card.registerCard(new Card(c,EIGHT),2);
            Card.registerCard(new Card(c,NINE),2);
            Card.registerCard(new Card(c,DRAW_TWO),2);
            Card.registerCard(new Card(c,DRAW_FOUR),2);
            Card.registerCard(new Card(c,SKIP_TURN),2);
            Card.registerCard(new Card(c,SELECT_COLOR),2);
        }

        for(File f : Objects.requireNonNull(plugins.listFiles())){
            if(f.getName().endsWith(".jar")){
                logger.log("Plugin " + f.getAbsoluteFile() + " found.");
                try {
                    URLClassLoader classLoader = new URLClassLoader(new URL[] { new URL("jar:file:" + f.getAbsoluteFile() + "!/") });
                    InputStream inputStream = classLoader.getResourceAsStream("plugin.json");
                    if(inputStream == null){
                        logger.err("Couldn't load plugin, error reading plugin.json");
                        continue;
                    }
                    JSONObject o = new JSONObject(new String(inputStream.readAllBytes()));
                    Plugin plugin = (Plugin) PluginUtils.loadClass(classLoader,o.getString("main-class"));
                    if(plugin == null){
                        logger.err("How the fuck did you manage that the plugin is null while loading plugin main... literally how, you just broke the matrix");
                        continue;
                    }
                    activePlugins.add(plugin);
                    plugin.onEnable();
                } catch (Exception e) {
                    logger.err(e.getLocalizedMessage());
                    logger.err(Util.stacktraceToString(e.getStackTrace()));
                    logger.err("Plugin " + f.getAbsoluteFile() + " failed to load.");
                    continue;
                }
                logger.log("Plugin " + f.getName() + " successfully loaded.");
            }
        }
        logger.log("All plugins loaded!");
        logger.log("Cards registered in deck: " + Card.getDeck().size());
    }

    public static void callEvent(Event e){
        for(Plugin plugin : activePlugins){
            for(Listener listener : plugin.getEvents()){
                Method[] methods = listener.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(EventHandler.class)) {
                        method.setAccessible(true);
                        if(method.getParameterCount() == 1){
                            if(method.getParameterTypes()[0] == e.getClass()){
                                try {
                                    method.invoke(listener,e);
                                } catch (Exception ex) {
                                    new Logger("TEST").warn("Couldnt call method");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}