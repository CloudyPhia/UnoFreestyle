package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private ArrayList<Card> playerHand;
    private String name;

    // CONSTANT:
    // each player's list of cards

    //have hand here - list of cards for each
    // every time create new player object ,each object should have unique
    // hand and keep information

    // create a field that's list of card




    //EFFECTS: instantiates the player's hand
    public Player(String playerName) {
        this.name = playerName;
        this.playerHand = new ArrayList<>();

        //for (int i = 0; i == 7; i++) {
          //  playerHand.add(i, null);
            //this is my attempt at just like putting shit here.
            // Might NOT need this

      //  }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    //public void printCardList()

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

    public String getName(String n) {
        return this.name;
    }







    // CHANGING:
    // adding multiple players to the game
    //
}

// possibly putting drawCard, discardCard in here. Might centralize it
// and streamline it more. If I do that, for the game stuff for now i'll
// just have like p.drawCard(); sout("drew card") and then print the card
// or some shit? Refer to transcript.java
