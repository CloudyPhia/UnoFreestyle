package ui;

import model.Card;
import model.GameState;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

//Uno card game application

/*
 * CITATION: saveGameState(); and loadGameState(); code obtained (and modified) from JsonSerializationDemo
 *           - WorkRoomApp class.
 *           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

public class UnoApplication {

    private ArrayList<Player> playerList;
    private Player player1;
    private Player player2;
    private Player player3; //for future use
    private Player player4; //for future use

    private Player currentPlayer;

    private static final String JSON_STORE = "./data/gamestate.json";
    private Scanner input;
    private GameState gameState;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private int playerTurn;
    private int amountOfPlayers = 2;
    private Boolean liveGame = false;
    private Boolean setUpRun = true;
    private Boolean endScreen = false;
    private Boolean failure = false;
    private Boolean newGame = false;

    protected static int STARTING_CARD_AMOUNT = 7;

    //EFFECTS: runs the game application
    public UnoApplication() throws FileNotFoundException {
        input = new Scanner(System.in);
        gameState = new GameState("Saved Game");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGame();
    }


    //MODIFIES: this
    //EFFECTS: processes user input
    private void runGame() {
        boolean keepGoing = true;
        String command = null;

        playerSetUp();

        // askPlayerAboutLoadingOldGame();

        resetPlayerTurns();

        if (failure) {
            keepGoing = false;
            exit(69);
        }

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

        askToSave();

        endGameMessage();

    }

    //EFFECTS: loads the initial menu that asks about loading an old game
    private void askPlayerAboutLoadingOldGame() {
        String command = null;

        while (setUpRun) {
            initialLoadMenu();
            command = input.next();
            command = command.toLowerCase();
            processStartupCommand(command);

        }

    }

    //EFFECTS: displays the initial load menu
    private void initialLoadMenu() {
        System.out.println("Hello! Welcome to Online Uno!");
        System.out.println("\tload -> load your previous saved game!");
        System.out.println("\tnew -> start a new game!");

    }

    //EFFECTS: processes user commands in the startup menu.
    private void processStartupCommand(String command) {
        if (command.equals("load")) {
            setUpRun = false;
            newGame = false;
            loadGameState();
        } else if (command.equals("new")) {
            setUpRun = false;
            newGame = true;
            init();
            askForEachPlayerName();
        } else {
            System.out.println("That selection is not valid. Please try again.");
        }
    }

    //EFFECTS: sets up the players and initializes the new list
    private void playerSetUp() {
        playerList = new ArrayList<>();

        askPlayerAboutLoadingOldGame();

    }


    // MODIFIES: this, Player
    // EFFECTS: initializes the player and gives them their starting cards
    private void init() {

        player1 = new Player("NameNotInitialized");
        player2 = new Player("NameNotInitialized2");

        playerList.add(player1);
        playerList.add(player2);
        //playerList.add(player3); for future use
        //playerList.add(player4); for future use

        resetPlayerTurns();

        while (playerTurn < amountOfPlayers) {
            getCurrentPlayer();
            dealCards();
            this.playerTurn++;
        }

        resetPlayerTurns();
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

        while (playerTurn < amountOfPlayers) {
            getCurrentPlayer();
            askForName();
            this.playerTurn++;
        }
    }

    // MODIFIES: this, Player
    // EFFECTS: asks the player for a name and then sets the player's name to their input.
    private void askForName() {

        System.out.println("Please enter this player's name.");

        String name = input.next();

        currentPlayer.setName(name);

        System.out.println("Nice to meet you, " + currentPlayer.getName() + "!");
    }




    // MODIFIES: this
    // EFFECTS: processes user command in game
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


    // REQUIRES: number be typed into console
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

            nextPlayer();
        }

    }

    //MODIFIES: this
    //EFFECTS: resets the player turns to 0, effectively starting again with player1.
    private void resetPlayerTurns() {
        this.playerTurn = 0;
    }

    //EFFECTS: gets the current player that the turn is on.
    private void getCurrentPlayer() {
        currentPlayer = playerList.get(playerTurn);
    }

    //EFFECTS: sends out "Thanks for playing" and sets liveGame to false.
    private void endGameMessage() {
        System.out.println("\nThanks For Playing!");
        liveGame = false;
    }


    //EFFECTS: asks the player if they'd like to save their game.
    private void askToSave() {
        endScreen = true;
        String endCommand = null;
        while (endScreen) {
            endMenu();
            endCommand = input.next();
            endCommand = endCommand.toLowerCase();

            processEndCommand(endCommand);
        }
    }

    //EFFECTS: displays the end menu.
    private void endMenu() {
        System.out.println("Currently ending the game. Would you like to save?");
        System.out.println("\tsave -> save game and exit");
        System.out.println("\tfinish -> end the game without saving");

    }

    //EFFECTS: processes user command in the end menu.
    private void processEndCommand(String endCommand) {
        while (endScreen) {
            if (endCommand.equals("save")) {
                System.out.println("Saving game...");
                saveGameState();
                endScreen = false;
            } else if (endCommand.equals("finish")) {
                System.out.println("Ending game...");
                endScreen = false;
            } else {
                System.out.println("That selection is not valid. Please try again.");
            }
        }
    }


    //MODIFIES: gameState
    //EFFECTS: saves the workroom to file
    private void saveGameState() {
        try {
            for (Player p: playerList) {
                gameState.addPlayer(p);
            }

            jsonWriter.open();
            jsonWriter.write(gameState);
            jsonWriter.close();
            System.out.println("Saved " + gameState.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads workroom from file
    private void loadGameState() {
        try {
            gameState = jsonReader.read();
            System.out.println("Loaded " + gameState.getName() + " from " + JSON_STORE);



            player1 = gameState.getCurrentPlayers().get(0);
            player2 = gameState.getCurrentPlayers().get(1);

            playerList.add(player1);
            playerList.add(player2);


        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            System.out.println("Please reload and start a new game.");
            failure = true;
        }

    }




}
