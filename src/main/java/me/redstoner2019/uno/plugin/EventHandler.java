package me.redstoner2019.uno.plugin;

import me.redstoner2019.uno.plugin.events.LobbyCreateEvent;
import me.redstoner2019.uno.plugin.events.LobbyCreatedEvent;
import me.redstoner2019.uno.plugin.events.PlayerJoinLobbyEvent;
import me.redstoner2019.uno.plugin.events.PlayerUNOEvent;
import me.redstoner2019.uno.util.Player;
import me.redstoner2019.uno.util.game.Card;
import me.redstoner2019.uno.util.game.Lobby;

import java.util.List;

public class EventHandler {
    public void onLobbyCreateEvent(LobbyCreateEvent event) {
        return;
    }
    public void lobbyCreatedEvent(LobbyCreatedEvent e){
        return;
    }
    public void playerJoinLobbyEvent(PlayerJoinLobbyEvent e){
        return;
    }

    public void playerJoinServerEvent(Player player){
        return;
    }
    public void playerConnectEvent(String username){
        return;
    }
    public void playerLeaveServerEvent(Player player){
        return;
    }

    public void playerPreDrawCardsEvent(Player player, int amount){
        return;
    }
    public void playerDrawnCardsEvent(Player player, List<Card> cards){
        return;
    }
    public void playerPutCardEvent(Player player, Card card){
        return;
    }
    public void playerSkipTurnEvent(Player player){
        return;
    }
    public void playerUNOEvent(PlayerUNOEvent e){
        return;
    }
}