package me.redstoner2019.uno.plugin.events;

import me.redstoner2019.uno.util.Player;
import me.redstoner2019.uno.util.game.Card;

public class PlayerPutCardEvent extends Event {
    private Player player;
    private Card card;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public PlayerPutCardEvent(Player player, Card card) {
        this.player = player;
        this.card = card;
    }
}
