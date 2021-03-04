package ui;

import model.Card;
import model.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Uno card game application

//have an integer that's keeping track of whos turn it is - what point in time you'll switch turn
//switch statement (look it up!) :D , use break at the end of cases
//list of players - for now set it to be 2 but later prompt user to ask for how many people are playing

public class UnoApplication {

    private ArrayList<Player> playerList;
    private Player player1;
    private Player player2;
    private Player player3; //for future use
    private Player player4; //for future use

    private Player currentPlayer;

    private Scanner input;

    private int playerTurn;
    private int amountOfPlayers = 2;
    private Boolean liveGame = false;

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

        playerList = new ArrayList<>();
        player1 = new Player("NameNotInitialized");
        player2 = new Player("NameNotInitialized2");
       // player3 = new Player("NameNotInitialized3");
       // player4 = new Player("NameNotInitialized4;");

        init();

        askForEachPlayerName();

        resetPlayerTurns();

        liveGame = true;

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
        liveGame = false;

    }

    // MODIFIES: this, Player
    // EFFECTS: initializes the player and gives them their starting cards
    private void init() {

        playerList.add(player1);
        playerList.add(player2);
        //playerList.add(player3); for future use
        //playerList.add(player4); for future use

        resetPlayerTurns();

        getCurrentPlayer();

        for (int i = 0; i < amountOfPlayers; i++) {
            dealCards();

            if (playerTurn < amountOfPlayers - 1) {
                this.playerTurn++;
            }
            getCurrentPlayer();
        }

        resetPlayerTurns();

        input = new Scanner(System.in);
    }

    //EFFECTS: deals the player STARTING_CARD_AMOUNT of cards
    private void dealCards() {
        for (int in = 0; in < STARTING_CARD_AMOUNT; in++) {
            drawFromDeck(currentPlayer);
        }

    }


    //EFFECTS: individually asks each player for their name
    private void askForEachPlayerName() {
        resetPlayerTurns();
        for (int i = 0; i < amountOfPlayers; i++) {

            askForName();

            if (playerTurn < amountOfPlayers - 1) {
                this.playerTurn++;
            }
            getCurrentPlayer();
        }
    }

    // MODIFIES: this
    // EFFECTS: asks the player for a name and then sets the player's name to their input.
    private void askForName() {

        System.out.println("Please enter this player's name.");

        String name = input.next();

        currentPlayer.setName(name);

        System.out.println("Nice to meet you, " + currentPlayer.getName() + "!");
    }




    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {

        getCurrentPlayer();

        if (command.equals("draw")) {
            drawFromDeck(currentPlayer);
        } else if (command.equals("choose") && currentPlayer.possibleToDiscard()) {
            selectCard();
        } else {
            System.out.println("That selection is not valid. Please try again.");
        }
    }

    //EFFECTS: changes the turn to the next player
    private void nextPlayer() {

        if (playerTurn >= playerList.size() - 1) {
            resetPlayerTurns();
        } else {
            this.playerTurn++;
        }

    }



    // MODIFIES: this
    // EFFECTS: removes a card from the player's list of cards based on which card index they select
    private void selectCard() {

        System.out.println(currentPlayer.getName() + ", Type ONLY the index number of the card you to discard.");
        int selectedIndex = input.nextInt();

        if (selectedIndex >= currentPlayer.getPlayerHandSize()) {

            System.out.println("Invalid index selection. Please try again.");

        } else {
            Card c = currentPlayer.getPlayerHand().get(selectedIndex);
            System.out.println("Card " + c.getColour() + " " + c.getNumber() + " at index " + selectedIndex
                    + " has been discarded.");
            discardCard(currentPlayer, currentPlayer.getPlayerHand().get(selectedIndex));

            nextPlayer();
        }

    }


    //EFFECTS: displays the options menu to the user.
    private void displayMenu() {

        getCurrentPlayer();

        System.out.println(currentPlayer.getName() + ", here are your cards.");
        for (Card c : currentPlayer.getPlayerHand()) {
            System.out.println("Index:" + currentPlayer.getCardIndex(c) + " Card Type: "
                    + c.getColour() + " " + c.getNumber());
        }

        System.out.println(currentPlayer.getName() + ", here are your turn options");
        if (currentPlayer.possibleToDiscard()) {
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
        if (liveGame) {
            System.out.println(currentPlayer.getName() + ", you drew a " + card.getColour() + " " + card.getNumber()
                    + "!");
        }

        nextPlayer();

    }

    //EFFECTS: resets the player turns to 0, effectively starting again with player1.
    private void resetPlayerTurns() {
        this.playerTurn = 0;
    }

    //EFFECTS: gets the current player that the turn is on.
    private void getCurrentPlayer() {
        currentPlayer = playerList.get(playerTurn);
    }


}
