package me.redstoner2019.uno.util;

import me.redstoner2019.server.ODClientHandler;

public class Player {
    private String username;
    private String displayname;
    private String currentLobby;
    private ODClientHandler clientHandler;

    public ODClientHandler getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(ODClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public Player(String username, String displayname) {
        this.username = username;
        this.displayname = displayname;
    }

    public String getCurrentLobby() {
        return currentLobby;
    }

    public void setCurrentLobby(String currentLobby) {
        this.currentLobby = currentLobby;
    }
    public void sendObject(Object o){
        clientHandler.sendObject(o);
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", displayname='" + displayname + '\'' +
                '}';
    }
}
