package me.redstoner2019.uno.util.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Card {
    private String numerator;
    private String color;
    private static HashMap<Card, Integer> registeredDeck = new HashMap<>();

    public String getNumerator() {
        return numerator;
    }

    public void setNumerator(String numerator) {
        this.numerator = numerator;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Card(String color, String numerator) {
        this.numerator = numerator;
        this.color = color;
    }
    public Card(CardColor color, CardNumerator numerator) {
        this.numerator = numerator.name();
        this.color = color.name();
    }
    public static void registerCard(String color, String numerator, int amount){
        registeredDeck.put(new Card(color,numerator),amount);
    }
    public static void registerCard(Card c, int amount){
        registeredDeck.put(c,amount);
    }
    public static List<Card> getDeck(){
        List<Card> cards = new ArrayList<>();
        for(Card c : registeredDeck.keySet()){
            for(int i = 0; i < registeredDeck.get(c); i++){
                cards.add(c);
            }
        }
        return cards;
    }
}
