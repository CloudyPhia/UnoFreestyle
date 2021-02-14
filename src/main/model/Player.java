package model;

import java.util.ArrayList;

// Represents a specific named player that is holding a hand of cards

public class Player {

    private final ArrayList<Card> playerHand;
    private final String name;

    //REQUIRES: playerName has a non-zero length
    //EFFECTS: instantiates the player's hand and sets the name to playerName.

    public Player(String playerName) {
        this.name = playerName;
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


}
