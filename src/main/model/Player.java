package model;

import java.util.ArrayList;
import java.util.List;

// Represents a specific named player that is holding a hand of cards

public class Player {

    private ArrayList<Card> playerHand;
    private String name;


    //EFFECTS: instantiates the player's hand
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
    //EFFECTS: adds the random card to the player's hand (the list of cards)
    public void addCardToHand(Card card) {
        playerHand.add(card);
    }

    //REQUIRES: card is actually in the list
    //MODIFIES: this
    //EFFECTS: removes the selected card from the index
    public void removeCardFromHand(Card card) {
        int cardIndex = getCardIndex(card);
        playerHand.remove(cardIndex);
    }


    //getter for the list so that you can still access list from game class
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

  //  public void setName(String playerName) {
  //      this.name = playerName;
  //  }




}
