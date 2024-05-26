package me.redstoner2019.uno.client.networking;

import me.redstoner2019.client.ODClient;
import me.redstoner2019.events.ConnectionFailedEvent;
import me.redstoner2019.events.ConnectionLostEvent;
import me.redstoner2019.events.ConnectionSuccessEvent;
import me.redstoner2019.events.ObjectRecieveEvent;
import me.redstoner2019.uno.client.ClientMain;
import me.redstoner2019.uno.client.gui.Application;
import me.redstoner2019.uno.client.gui.subguis.LobbyGUI;
import me.redstoner2019.uno.client.gui.subguis.LobbySelector;
import me.redstoner2019.uno.client.gui.subguis.ServerMenu;
import org.json.JSONObject;

import java.awt.*;

public class ClientConnector extends ODClient {
    public ClientConnector(){

    }
    public void start(String ip, int port){
        connect(ip,port);
        addConnectionFailedEvent(new ConnectionFailedEvent() {
            @Override
            public void onEvent() {
                Application.switchGui("server-menu");
                ServerMenu.serverInfo.setText("Could not connect to Server");
                ServerMenu.serverInfo.setForeground(Color.RED);
            }
        });
        addConnectionLostEvent(new ConnectionLostEvent() {
            @Override
            public void onEvent() {
                Application.switchGui("server-menu");
                ServerMenu.serverInfo.setText("Connection lost");
                ServerMenu.serverInfo.setForeground(Color.RED);
            }
        });
        addConnectionSuccessEvent(new ConnectionSuccessEvent() {
            @Override
            public void onEvent() {

            }
        });
        addObjectReiecedEvent(new ObjectRecieveEvent() {
            @Override
            public void onEvent(Object o) {
                if(o instanceof String){
                    JSONObject packet = new JSONObject((String) o);
                    System.out.println(packet);
                    if(packet.has("header")) switch (packet.getString("header")){
                        case "join-result": {
                            if(packet.getString("data").equals("invalid-token")){
                                ServerMenu.serverInfo.setText("Invalid Token. Check auth server and log in again.");
                                disconnect();
                            } else if(packet.getString("data").equals("auth-failed")){
                                ServerMenu.serverInfo.setText("Server failed to connect to auth server.");
                                disconnect();
                            }else {
                                Application.switchGui("lobby-selector");
                            }
                            break;
                        }
                        case "join-lobby-result":{
                            if(packet.getString("data").equals("success")){
                                String code = packet.getString("code");
                                LobbyGUI.lobbyCode.setText(code);
                                Application.switchGui("lobby-gui");
                            } else {
                                LobbySelector.lobbyInfo.setText("Couldn't find lobby");
                                LobbySelector.lobbyInfo.setForeground(Color.RED);
                            }
                            break;
                        }
                        case "create-lobby-result":{
                            if(packet.getString("data").equals("success")){
                                String code = packet.getString("code");
                                LobbyGUI.lobbyCode.setText(code);
                                Application.switchGui("lobby-gui");
                            } else {
                                LobbySelector.lobbyInfo.setText("Couldn't create lobby");
                                LobbySelector.lobbyInfo.setForeground(Color.RED);
                            }
                            break;
                        }
                    }
                }
            }
        });
        JSONObject o = new JSONObject();
        o.put("header","login-packet");
        o.put("token", ClientMain.TOKEN);
        o.put("version",ClientMain.version.toString());
        o.put("client-version",ClientMain.uiVersion.toString());
        o.put("client-type","jar-file");
        sendObject(o.toString());
        ServerMenu.serverInfo.setText("Logging in...");
        startListener();
    }
}
