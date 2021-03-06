package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;

// Represents a specific named player that is holding a hand of cards

/*
 * CITATION: toJson(); and cardsToJson(); method code obtained (and modified) from JsonSerializationDemo
 *           - Thingy class and WorkRoom class respectively
 *           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

public class Player implements Writeable {

    private final ArrayList<Card> playerHand;
    protected String playerName;

    //REQUIRES: playerName has a non-zero length
    //EFFECTS: instantiates the player's hand and sets the name to playerName.
    public Player(String playerName) {
        this.playerName = playerName;
        this.playerHand = new ArrayList<>();
    }

    //EFFECTS: returns true if the size of the hand is greater than 0.
    public boolean possibleToDiscard() {
        return playerHand.size() != 0;
    }


    //REQUIRES: a randomized card
    //MODIFIES: this
    //EFFECTS: adds the random card to the end of the player's hand (the list of cards)
    public void addCardToHand(Card card) {
        playerHand.add(card);
    }

    //REQUIRES: card is actually in the list
    //MODIFIES: this
    //EFFECTS: removes the first instance of the selected card from the index
    public void removeCardFromHand(Card card) {
        int cardIndex = getCardIndex(card);
        playerHand.remove(cardIndex);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("name", playerName);
        json.put("cards", cardsToJson());
        return json;
    }

    //EFFECTS: returns cards in this player as a JSON array
    public JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card c : playerHand) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }


    //setters
    //EFFECTS: sets the name of the player
    public void setName(String playerName) {
        this.playerName = playerName;
    }


    //getters

    //EFFECTS: returns the list of cards (the player's Hand)
    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    //EFFECTS: return the size of the playerHand
    public int getPlayerHandSize() {
        return playerHand.size();
    }

    //EFFECTS: gets the index of the first instance of a specific card
    public int getCardIndex(Card c) {
        return playerHand.indexOf(c);
    }

    //EFFECTS: gets the name of the player
    public String getName() {
        return this.playerName;
    }
}
