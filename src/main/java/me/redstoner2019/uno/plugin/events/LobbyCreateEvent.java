package me.redstoner2019.uno.plugin.events;

import me.redstoner2019.uno.util.Player;

public class LobbyCreateEvent extends Event{
    private Player player;
    private String customCode;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    public LobbyCreateEvent(Player player, String customCode) {
        this.player = player;
        this.customCode = customCode;
    }
}
