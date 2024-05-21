package me.redstoner2019.uno.plugin;

import me.redstoner2019.uno.util.Player;
import me.redstoner2019.uno.util.game.Card;
import me.redstoner2019.uno.util.game.Lobby;

import java.util.List;

public class EventHandler {
    /**
     * @param p Player that created a Lobby.
     * @param customCode Custom Lobby code, null if it should be random
     */
    public boolean onLobbyCreateEvent(Player p, String customCode) {
        return true;
    }
    public boolean lobbyCreatedEvent(Lobby lobby){
        return true;
    }
    public boolean playerJoinLobbyEvent(Player player, Lobby lobby){
        return true;
    }

    public boolean playerJoinServerEvent(Player player){
        return true;
    }
    public boolean playerConnectEvent(String username){
        return true;
    }
    public boolean playerLeaveServerEvent(Player player){
        return true;
    }

    public boolean playerPreDrawCardsEvent(Player player, int amount){
        return true;
    }
    public boolean playerDrawnCardsEvent(Player player, List<Card> cards){
        return true;
    }
    public boolean playerPutCardEvent(Player player, Card card){
        return true;
    }
    public boolean playerSkipTurnEvent(Player player){
        return true;
    }
}