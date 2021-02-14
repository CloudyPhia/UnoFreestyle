package ui;

import model.Card;
import model.Player;

import java.util.List;
import java.util.Random;



public class Game {

    private Player player1;
    private Player player2;
    private Integer cardPlaceInHand;

    //public static int STARTING_CARD_AMOUNT = 7;

    //EFFECTS: runs the game application
    public Game() {
        runGame();
    }


    //MODIFIES: this
    //EFFECTS:
    private void runGame() {

        // for each player

        //drawFromDeck(); //make a new player each time

    }

    //REQUIRES: amount of cards in the player's hand > 0
    //MODIFIES: Player and this
    //EFFECTS: discard the first instance of the specified card from the player's hand and
    //         return true. If that card doesn't exist, return false.
    public boolean discardCard(Player p, Card c) {

        if (p.getCardIndex(c) == -1) {
            return false;
        } else {
            p.removeCardFromHand(c);
            return true;
        }

    }

    //MODIFIES: Player and this
    //EFFECTS: adds a randomized card to the player's hand.
    public void drawFromDeck(Player p) {
        Random random = new Random();

        Integer colour = random.nextInt(4);
        String colourName = "";

        if (colour == 0) {
            colourName = "Red";
        } else if (colour == 1) {
            colourName = "Blue";
        } else if (colour == 2) {
            colourName = "Yellow";
        } else {
            colourName = "Green";
        }

        Card card = new Card(colourName, random.nextInt(10));

        p.addCardToHand(card);

    }

    //EFFECTS: ends the player's turn and goes to the next person.
    public void endTurn() {
        //do something
    }

    //later create game class in model package, made ui package called UnoApp
    // scanner stuff from tellerapp




    //PUT ALL THE MAIN SHIT TO RUN IT HERE
    // have multiple players as specific fields or list of players to keep track
    //create new player objects (player 1 = new player), maybe only have 2
    //focus on setting up the game and then havng very basic functionality
    //card field in game class that's last played card for symbol/colour




}
