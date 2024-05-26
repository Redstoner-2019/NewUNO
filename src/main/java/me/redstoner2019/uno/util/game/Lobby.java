package me.redstoner2019.uno.util.game;

import me.redstoner2019.uno.util.Player;
import org.json.JSONObject;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Lobby {
    private String code;
    private List<Player> players = new ArrayList<>();
    private int cardsPerPlayer = 7;
    private int decks = 2;
    private Queue<Card> DECK = new ArrayDeque<>();
    private Player owner;
    public Lobby(String code, Player owner){
        this.code = code;
        this.owner = owner;
    }

    public boolean isOwner(Player p){
        return p.equals(owner);
    }

    public void removePlayer(Player p){
        players.remove(p);
    }

    public int getAmountOfPlayers(){
        return players.size();
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

    public List<Player> getPlayers() {
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
    public void start(){
        for(Player p : players){
            p.getClientHandler().sendObject(getLobbyStateFor(p).toString());
        }
    }
    public JSONObject getLobbyStateFor(Player p){
        JSONObject data = new JSONObject();
        data.put("header","lobby-state");
        data.put("current-player",players.getFirst());

        JSONObject displayData = new JSONObject();
        for(Player pl : players){
            displayData.put(pl.getUsername(),pl.getDisplayname());
        }
        data.put("display-names",displayData);
        return data;
    }
    public void nextPlayer(){
        Player p = players.getFirst();
        players.remove(p);
        players.addLast(p);
    }
    public void recievePacket(JSONObject packet){

    }
}
