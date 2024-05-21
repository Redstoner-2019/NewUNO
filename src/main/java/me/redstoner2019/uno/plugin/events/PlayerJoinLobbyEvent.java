package me.redstoner2019.uno.plugin.events;

import me.redstoner2019.uno.util.Player;
import me.redstoner2019.uno.util.game.Lobby;

public class PlayerJoinLobbyEvent extends Event{
    private Player player;
    private Lobby lobby;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public PlayerJoinLobbyEvent(Player player, Lobby lobby) {
        this.player = player;
        this.lobby = lobby;
    }
}
