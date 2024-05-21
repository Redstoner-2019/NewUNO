package me.redstoner2019.uno.plugin.events;

import me.redstoner2019.uno.util.Player;

public class PlayerLeaveServerEvent extends Event{
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerLeaveServerEvent(Player player) {
        this.player = player;
    }
}
