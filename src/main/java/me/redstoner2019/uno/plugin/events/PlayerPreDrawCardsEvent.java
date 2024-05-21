package me.redstoner2019.uno.plugin.events;

import me.redstoner2019.uno.util.Player;

public class PlayerPreDrawCardsEvent extends Event{
    private Player player;
    private int amount;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PlayerPreDrawCardsEvent(Player player, int amount) {
        this.player = player;
        this.amount = amount;
    }
}
