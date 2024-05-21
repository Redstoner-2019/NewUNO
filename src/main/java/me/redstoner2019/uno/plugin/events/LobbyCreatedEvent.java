package me.redstoner2019.uno.plugin.events;

import me.redstoner2019.uno.util.game.Lobby;

public class LobbyCreatedEvent extends Event{
    private Lobby lobby;

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public LobbyCreatedEvent(Lobby lobby) {
        this.lobby = lobby;
    }
}
