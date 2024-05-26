package me.redstoner2019.uno.util.game;

import me.redstoner2019.uno.util.Player;

import java.util.ArrayDeque;
import java.util.Queue;

public class Lobby {
    private String code;
    private Queue<Player> players = new ArrayDeque<>();
    private int cardsPerPlayer = 7;
    private int decks = 2;
    private Queue<Card> DECK = new ArrayDeque<>();
    public Lobby(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void addPlayer(Player p){
        players.add(p);
    }
    public void addNewCard(Card c){
        DECK.add(c);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public int getCardsPerPlayer() {
        return cardsPerPlayer;
    }

    public void setCardsPerPlayer(int cardsPerPlayer) {
        this.cardsPerPlayer = cardsPerPlayer;
    }

    public int getDecks() {
        return decks;
    }

    public void setDecks(int decks) {
        this.decks = decks;
    }

    public Queue<Card> getDECK() {
        return DECK;
    }
}
