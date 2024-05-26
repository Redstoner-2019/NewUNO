package me.redstoner2019.uno.server.networking;

import me.redstoner2019.events.ClientConnectEvent;
import me.redstoner2019.events.ConnectionLostEvent;
import me.redstoner2019.events.ObjectRecieveEvent;
import me.redstoner2019.server.ODClientHandler;
import me.redstoner2019.server.ODServer;
import me.redstoner2019.uno.Main;
import me.redstoner2019.uno.server.ServerMain;
import me.redstoner2019.uno.util.Logger;
import me.redstoner2019.uno.util.Player;
import me.redstoner2019.uno.util.game.Lobby;
import org.json.JSONObject;

public class ServerConnector extends ODServer {
    public Logger logger = new Logger("SERVER");
    public ServerConnector(int port){
        setPort(port);

        addClientConnectEvent(new ClientConnectEvent() {
            @Override
            public void onEvent(ODClientHandler handler) {
                Player player = new Player(null,null);
                handler.addConnectionLostEvent(new ConnectionLostEvent() {
                    @Override
                    public void onEvent() {

                    }
                });
                handler.addObjectRecieveEvent(new ObjectRecieveEvent() {
                    @Override
                    public void onEvent(Object o) {
                        System.out.println(o);
                        if(o instanceof String){
                            JSONObject packet = new JSONObject((String) o);
                            if(packet.has("header")){
                                switch (packet.getString("header")){
                                    case "login-packet":{
                                        String token = packet.getString("token");
                                        if(!ServerMain.authenticatorClient.isConnected()){
                                            logger.err("Not connected to auth Server");
                                            JSONObject result = new JSONObject();
                                            result.put("header","join-result");
                                            result.put("data","auth-failed");
                                            handler.sendObject(result.toString());
                                            ServerMain.authenticatorClient.setup();
                                            break;
                                        }
                                        JSONObject tokeninfo = ServerMain.authenticatorClient.tokeninfo(token);
                                        if(tokeninfo.getString("data").equals("success")){
                                            JSONObject result = new JSONObject();
                                            result.put("header","join-result");
                                            result.put("data","success");
                                            player.setDisplayname(tokeninfo.getString("displayname"));
                                            player.setUsername(tokeninfo.getString("username"));
                                            player.setClientHandler(handler);
                                            System.out.println(result.toString());
                                            handler.sendObject(result.toString());
                                        } else {
                                            JSONObject result = new JSONObject();
                                            result.put("header","join-result");
                                            result.put("data","invalid-token");
                                            handler.sendObject(result.toString());
                                        }
                                        break;
                                    }
                                    case "join-lobby":{
                                        String code = packet.getString("code");
                                        Lobby lobby = ServerMain.lobbies.getOrDefault(code,null);
                                        JSONObject result = new JSONObject();
                                        result.put("header","join-lobby-result");
                                        if(lobby == null){
                                            result.put("data","lobby-not-found");
                                        } else {
                                            lobby.addPlayer(player);
                                            result.put("data","success");
                                            result.put("code",code);
                                            player.setCurrentLobby(code);
                                        }
                                        handler.sendObject(result.toString());
                                        break;
                                    }
                                    case "create-lobby":{
                                        JSONObject result = new JSONObject();
                                        result.put("header","create-lobby-result");

                                        String code = packet.getString("code");

                                        if(ServerMain.lobbies.containsKey(code)){
                                            result.put("data","lobby-create-failed");
                                        } else {
                                            Lobby lobby = new Lobby(code,player);
                                            lobby.addPlayer(player);
                                            player.setCurrentLobby(code);
                                            ServerMain.lobbies.put(code,lobby);
                                            result.put("data","success");
                                            result.put("code",code);
                                        }
                                        handler.sendObject(result.toString());
                                        break;
                                    }
                                    case "start-lobby":{
                                        Lobby lobby = ServerMain.lobbies.getOrDefault(player.getCurrentLobby(),null);
                                        if(lobby == null){
                                            player.setCurrentLobby(null);
                                            JSONObject result = new JSONObject();
                                            result.put("header","join-lobby-result");
                                            result.put("data","lobby-not-found");
                                            player.sendObject(result.toString());
                                            break;
                                        }
                                        if(!lobby.isOwner(player)){
                                            JSONObject result = new JSONObject();
                                            result.put("header","start-lobby-result");
                                            result.put("data","not-lobby-owner");
                                            player.sendObject(result.toString());
                                            break;
                                        }
                                        lobby.start();
                                        break;
                                    }
                                    case "lobby-packet":{
                                        Lobby lobby = ServerMain.lobbies.getOrDefault(player.getCurrentLobby(),null);
                                        if(lobby == null){
                                            player.setCurrentLobby(null);
                                            JSONObject result = new JSONObject();
                                            result.put("header","join-lobby-result");
                                            result.put("data","lobby-not-found");
                                            player.sendObject(result.toString());
                                            break;
                                        }
                                        lobby.recievePacket(packet);
                                        break;
                                    }
                                    case "leave-lobby-packet":{
                                        Lobby lobby = ServerMain.lobbies.getOrDefault(player.getCurrentLobby(),null);
                                        if(lobby == null){
                                            player.setCurrentLobby(null);
                                            JSONObject result = new JSONObject();
                                            result.put("header","join-lobby-result");
                                            result.put("data","lobby-not-found");
                                            player.sendObject(result.toString());
                                            break;
                                        }
                                        lobby.removePlayer(player);
                                        if(lobby.getAmountOfPlayers() == 0){
                                            ServerMain.lobbies.put(player.getCurrentLobby(),null);
                                        }
                                        player.setCurrentLobby(null);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                });
                handler.startListener();
            }
        });
        start();
    }
}
