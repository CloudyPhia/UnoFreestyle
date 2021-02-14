package ui;

import model.Card;
import model.Player;

import java.util.Random;
import java.util.Scanner;

//Uno card game application

public class UnoApplication {

    private Player player1;
    private Scanner input;
    private String playerName;

    protected static int STARTING_CARD_AMOUNT = 7;

    //EFFECTS: runs the game application
    public UnoApplication() {
        runGame();
    }


    //MODIFIES: this
    //EFFECTS: processes user input
    private void runGame() {
        boolean keepGoing = true;
        String command = null;

        init();

        askForName();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("end")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }

        }

        System.out.println("\nThanks For Playing!");

    }

    // MODIFIES: this
    // EFFECTS: asks the player for a name and then sets the player's name to their input.
    private void askForName() {
        System.out.println("Please enter Player 1's name.");
        playerName = input.next();

        System.out.println("Nice to meet you, " + playerName + "!");

    }

    // MODIFIES: this, Player
    // EFFECTS: initializes the player and gives them their starting cards
    private void init() {
        player1 = new Player(playerName);

        for (int i = 0; i < STARTING_CARD_AMOUNT; i++) {
            drawFromDeck(player1);
        }

        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("draw")) {
            drawFromDeck(player1);
        } else if (command.equals("choose") && player1.possibleToDiscard()) {
            selectCard();
        } else {
            System.out.println("That selection is not valid. Please try again.");
        }
    }



    // MODIFIES: this
    // EFFECTS: removes a card from the player's list of cards based on which card index they select
    private void selectCard() {
        System.out.println(playerName + ", Type ONLY the index number of the card you to discard.");

        int selectedIndex = input.nextInt();

        if (selectedIndex >= player1.getPlayerHandSize()) {

            System.out.println("Invalid index selection. Please try again.");

        } else {
            Card c = player1.getPlayerHand().get(selectedIndex);
            System.out.println("Card " + c.getColour() + " " + c.getNumber() + " at index " + selectedIndex
                    + " has been discarded.");
            discardCard(player1, player1.getPlayerHand().get(selectedIndex));
        }

    }




    //EFFECTS: displays the options menu to the user.
    private void displayMenu() {

        System.out.println(playerName + ", here are your cards.");
        for (Card c : player1.getPlayerHand()) {
            System.out.println("Index:" + player1.getCardIndex(c) + " Card Type: "
                    + c.getColour() + " " + c.getNumber());
        }

        System.out.println(playerName + ", here are your turn options");
        if (player1.possibleToDiscard()) {
            System.out.println("\tchoose -> choose a card to discard");
        }

        System.out.println("\tdraw -> draw a card");
        System.out.println("\tend -> end the game");

    }




    //REQUIRES: amount of cards in the player's hand > 0
    //MODIFIES: Player
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

    //MODIFIES: Player, this
    //EFFECTS: adds a randomized card to the player's hand.
    public void drawFromDeck(Player p) {
        Random random = new Random();

        int colour = random.nextInt(4);
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



}
