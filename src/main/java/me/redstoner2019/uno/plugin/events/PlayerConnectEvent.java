package me.redstoner2019.uno.plugin.events;

public class PlayerConnectEvent extends Event{
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PlayerConnectEvent(String username) {
        this.username = username;
    }
}
