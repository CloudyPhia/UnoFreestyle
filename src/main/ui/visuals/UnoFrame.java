package ui.visuals;


import com.sun.tools.javadoc.Start;
import model.Card;
import model.GameState;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.UnoApplication;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

/*
 * Represents the main window in which the Uno
 * game is played
 */
public class UnoFrame extends JFrame {
    private Boolean startScreen = true;
    private Boolean optionsMenuScreen = false;
    private Boolean messagesScreen = false;
    private Boolean enterNameScreen = false;
    private Boolean discardSelectScreen = false;
    private Boolean cardActionScreen = false;
    private Boolean liveGame = false;
    private Boolean setUpRun = true;
    private Boolean endScreen = false;
    private Boolean failure = false;
    private Boolean newGame = false;

    private CardActionPanel cardActionPanel;
    private DiscardSelectPanel discardSelectPanel;
    private EndPanel endPanel;
    private EnterNamePanel enterNamePanel;
    private MessagesPanel messagesPanel;
    private OptionsMenuPanel optionsMenuPanel;
    private StartPanel startPanel;

    private ArrayList<Player> playerList;
    private Player player1;
    private Player player2;
    //private Player player3; for future use
    //private Player player4; for future use
    private Player currentPlayer;

    private static final String JSON_STORE = "./data/gamestate.json";
    private Scanner input;
    private GameState gameState;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private int playerTurn;
    protected static int STARTING_CARD_AMOUNT = 7;


    public UnoFrame() {
        super("Uno Application");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 600));
        input = new Scanner(System.in);
        gameState = new GameState("Saved Game");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        JComponent startContentPane = new StartPanel();
        startContentPane.setOpaque(true);
        setContentPane(startContentPane);
        pack();
        setVisible(true);

       // runGame();

    }

//    public void runGame() {
////        boolean keepGoing = true;
//        setUpStartScreen();
////
////        if (failure) {
////            keepGoing = false;
////            exit(1);
////        }
//
//    }

//    public void setUpStartScreen() {
//        StartPanel startPane = new StartPanel();
//
//        while (startScreen) {
//            startPane.setOpaque(true);
//            setContentPane(startPane);
//            pack();
//            setVisible(true);
//        }
//
//        startPane.setOpaque(false);
//        remove(startPane);
//    }

    //TODO: ADD INIT AND ASKFOREACHPLAYERNAME
    public void newGameSelected() {
        init();
//        //get the EnterName panel and ask for their names
//        //askForEachPlayerName();
//        startScreen = false;
//
//        resetPlayerTurns();
//
//
//        add(enterNamePanel);
//
//        while (playerTurn < playerList.size()) {
//            getCurrentPlayer();
//            this.playerTurn++;
//        }
//        remove(enterNamePanel);
    }


    //TODO: ADD LOADGAMESTATE
    public void loadGameSelected() {
        //loadGameState();
        startScreen = false;
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

        while (playerTurn < playerList.size()) {
            getCurrentPlayer();
            dealCards();
            this.playerTurn++;
        }

        resetPlayerTurns();
        enterPlayerNames();
    }

    private void enterPlayerNames() {
        remove(startPanel);
        add(enterNamePanel);

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

    //EFFECTS: deals the player STARTING_CARD_AMOUNT of cards
    private void dealCards() {
        for (int in = 0; in < STARTING_CARD_AMOUNT; in++) {
            drawFromDeck(currentPlayer);
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
            cardActionPanel.cardDrawScreen(card);

            nextPlayer();
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

    //TODO:
    public void drawCard() {

    }

    //TODO:
    public void discardCard() {

    }

    //TODO:
    public void endGame() {

    }

    //TODO: this is like specificlaly for the enter name panel
    public String getPlayerNumber() {
        return null;
    }

    //TODO: GET THEIR NAME
    public String getPlayerName() {
        return null;
    }

    //TODO: GET THE CARD NAMES AND THEIR COLOURS AND SHIT FOR EACH SPECIFIC PLAYER WHEN ITS THEIR TURN
    //what this is going to do. is we are going to say:
    //   for each of the cards:
    //   if the number is 1: see what colour it is
    //   if its 1 and blue, add Blue1 to the list.
    //   blue1 will then correspond to an image of a Blue 1.
    //   obvioulsy do this for each of the cards, and reiterate through this list each time this is called.
    public String[] getPlayerCards() {
        return null;
    }

    //TODO: SET THE PLAYER'S NAME
    public void setPlayerName(String name) {
        //set their name
        //make the call to messages panel, display message for like 5 seconds or smth
    }

}
