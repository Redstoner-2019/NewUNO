package me.redstoner2019.uno.plugin.events;

import me.redstoner2019.uno.util.Player;
import me.redstoner2019.uno.util.game.Card;

import java.util.List;

public class PlayerDrawnCardsEvent extends Event {
    private Player player;
    private List<Card> cards;

    public PlayerDrawnCardsEvent(Player player, List<Card> cards) {
        this.player = player;
        this.cards = cards;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
