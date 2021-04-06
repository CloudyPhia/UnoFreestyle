package ui.visuals;


import exceptions.IllegalNumberException;
import exceptions.IncorrectColourException;
import model.Card;
import model.GameState;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * Represents the main window in which the Uno
 * game is played
 */

public class UnoFrame extends JFrame {
    private JPanel startContentPanel;
    private JPanel discardSelectPanel;
    private JPanel endPanel;
    private JPanel namePanel;
    private JPanel optionsPanel;

    private final Boolean liveGame = false;
    private Boolean namePanelBoolean = false;
    private Boolean loadError = false;
    private final Boolean setUpRun = true;
    private final Boolean endScreen = false;
    private final Boolean failure = false;
    private Boolean discarding = false;
    private Boolean drawing = false;
    private final Boolean newGame = false;
    private final Boolean thanksForPlaying = false;
    private Boolean saveError = false;
    private Boolean endFromDiscard = false;


    private final ArrayList<Player> playerList;
    private Player player1;
    private Player player2;
    //private Player player3; for future use
    //private Player player4; for future use
    private Player currentPlayer;

    private static final String JSON_STORE = "./data/gamestate.json";
    private final Scanner input;
    private GameState gameState;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private int playerTurn;
    protected static int STARTING_CARD_AMOUNT = 7;

    public UnoFrame() {
        super("Uno Application");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 600));
        setLayout(new CardLayout());
        input = new Scanner(System.in);
        gameState = new GameState("Saved Game");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setUpPanels();

        add(startContentPanel);
        startContentPanel.setVisible(true);
        setVisible(true);
        pack();

        playerList = new ArrayList<>();

    }

    //MODIFIES: this
    //EFFECTS: sets up the panels that can be initialized at the beginning of the game (and adds them to the frame)
    public void setUpPanels() {
        startContentPanel = new StartPanel(this);
        startContentPanel.setOpaque(true);
        startContentPanel.setVisible(false);
        startContentPanel.setBackground(new java.awt.Color(171, 196, 255));
        namePanel = new EnterNamePanel(this);
        namePanel.setOpaque(true);
        namePanel.setVisible(false);
        namePanel.setBackground(new java.awt.Color(182, 204, 254));
        add(namePanel);
    }

    //MODIFIES: this
    //EFFECTS: initializes the discardPanel and adds it to the frame.
    public void initializeDiscardPanel() {
        discardSelectPanel = new DiscardSelectPanel(this);
        discardSelectPanel.setOpaque(true);
        discardSelectPanel.setVisible(false);
        discardSelectPanel.setBackground(new java.awt.Color(182, 204, 254));
        add(discardSelectPanel);
        pack();
    }

    //MODIFIES: this
    //EFFECTS: initializes the endPanel and adds it to the frame.
    public void initializeEndPanel() {
        endPanel = new EndPanel(this);
        endPanel.setOpaque(true);
        endPanel.setVisible(false);
        endPanel.setBackground(new java.awt.Color(193, 211, 254));
        add(endPanel);
    }


    //MODIFIES: this
    //EFFECTS: initializes the optionsMenuPanel and adds it to the frame.
    public void initializeOptionsMenuPanel() {
        optionsPanel = new OptionsMenuPanel(this);
        optionsPanel.setOpaque(true);
        optionsPanel.setVisible(false);
        optionsPanel.setBackground(new java.awt.Color(171, 196, 255));
        add(optionsPanel);
    }

    //EFFECTS: initializes the players, sets the namePanel to visible, and hides the starting panel.
    public void newGameSelected() {
        init();
        resetPlayerTurns();
        //initializeNamePane();
        namePanelBoolean = true;
        namePanel.setVisible(true);
        startContentPanel.setVisible(false);
        //remove(startContentPane);
        pack();

    }

    //EFFECTS: returns namePanelBoolean, which is just if the name panel is displaying or not.
    public Boolean isNamePanelDisplaying() {
        return namePanelBoolean;
    }


    //MODIFIES: this
    //EFFECTS: If a previous game can be loaded, the game begins and the starting panel is removed. Otherwise, the
    //         method returns and does not load a previous game.
    public void loadGameSelected() {
        loadGameState();

        if (loadError) {
            return;
        }

        startContentPanel.setVisible(false);
        remove(startContentPanel);
        resetPlayerTurns();
        beginOptions();

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
    public Card drawFromDeck(Player p) {
        try {
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

            return card;

        } catch (IllegalNumberException e) {
            System.out.println("This card was generated with an illegal number and was not added to the hand.");

            return null;
        } catch (IncorrectColourException e) {
            System.out.println("This card was generated with an illegal colour and was not added to the hand.");

            return null;
        }
    }

    //EFFECTS: changes the turn to the next player
    public void nextPlayer() {

        if (playerTurn >= playerList.size() - 1) {
            resetPlayerTurns();
        } else {
            this.playerTurn++;
        }

    }

    //MODIFIES: this
    //EFFECTS: removes the namePanel, and initializes the options menu.
    public void beginOptions() {
        remove(namePanel);

        initializeOptionsMenuPanel();

        if (namePanelBoolean) {
            resetPlayerTurns();
            namePanelBoolean = false;
        }

        optionsPanel.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: if the previous player discarded or drew a card, that menu will be hidden and the new options menu
    //         will be displayed for the current player.
    public void continueOptions() {
        if (discarding) {
            discardSelectPanel.setVisible(false);
            remove(discardSelectPanel);
            discarding = false;
        }

        if (drawing) {
            optionsPanel.setVisible(false);
            remove(optionsPanel);
            drawing = false;
        }

        nextPlayer();
        initializeOptionsMenuPanel();
        optionsPanel.setVisible(true);

    }


    //EFFECTS: gets the player turn
    public Integer getPlayerTurn() {
        return playerTurn;
    }

    //EFFECTS: gets the size of the current player's hand.
    public Integer getPlayerHandSize() {
        getCurrentPlayer();
        return currentPlayer.getPlayerHandSize();
    }


    //EFFECTS: turns a card's colour and number into the corresponding string needed to find it as an image.
    public String getCorrespondingCardImageName(Card card) {
        switch (card.getColour()) {
            case "Blue":
                return "blue_" + card.getNumber();
            case "Yellow":
                return "yellow_" + card.getNumber();
            case "Green":
                return "green_" + card.getNumber();
            default:
                return "red_" + card.getNumber();

        }
    }

    //EFFECTS: returns the card that was just drawn by the player.
    public Card drawCard() {
        drawing = true;
        getCurrentPlayer();
        return drawFromDeck(currentPlayer);
    }

    //EFFECTS: initializes the discard panel and removes the options panel. Basically sets up the discarding for the
    //         current player.
    public void discardCard() {
        initializeDiscardPanel();
        remove(optionsPanel);
        discardSelectPanel.setVisible(true);

        discarding = true;
    }

    //MODIFIES: Player, this
    //EFFECTS: removes the card in the player's hand at the given index.
    public void removeCardAtIndex(Integer index) {
        getCurrentPlayer();
        currentPlayer.removeCardFromHand(currentPlayer.getPlayerHand().get(index));

    }


    //MODIFIES: this
    //EFFECTS: This endGame is used when the "end game" button is clicked in the options menu. It initializes the
    //         endPanel and removes the options panel.
    public void endGame() {
        initializeEndPanel();
        remove(optionsPanel);
        endPanel.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: This ending is used when a player runs out of cards when theyre discarding (their hand is empty). It
    //         removes the discard panel and initializes the end panel.
    public void ending() {
        remove(discardSelectPanel);
        endFromDiscard = true;
        initializeEndPanel();
        endPanel.setVisible(true);
    }

    //EFFECTS: returns the "endFromDiscard" boolean. This is used to clarify if we've just ended from the discardPanel
    //         or if we've ended by selecting "end game".
    public Boolean getEndFromDiscard() {
        return endFromDiscard;
    }


    //EFFECTS: returns the player's name.
    public String getPlayerName() {
        getCurrentPlayer();
        return currentPlayer.getName();
    }


    //EFFECTS: gets the player's cards as a StringArray. This is important for our discardPanel where we need the scroll
    //         menu.
    public String[] getPlayerCards() {
        ArrayList<String> cardInHand;
        cardInHand = convertPlayerHandToStringArrayList();
        String[] strArray = cardInHand.toArray(new String[cardInHand.size()]);
        return strArray;

    }

    //EFFECTS: turns all of the cards into Strings that fit their respective image formats compared to just cards and
    //         integers.
    public ArrayList<String> convertPlayerHandToStringArrayList() {
        ArrayList<String> cardInHand = new ArrayList<>();
        getCurrentPlayer();
        for (Card card : currentPlayer.getPlayerHand()) {
            switch (card.getColour()) {
                case "Blue":
                    cardInHand.add("blue_" + card.getNumber());
                    break;
                case "Green":
                    cardInHand.add("green_" + card.getNumber());
                    break;
                case "Yellow":
                    cardInHand.add("yellow_" + card.getNumber());
                    break;
                default:
                    cardInHand.add("red_" + card.getNumber());
                    break;
            }
        }
        return cardInHand;

    }

    //EFFECTS: returns the card at the current player's index. (Used in the discard panel)
    public Card getCardAtIndex(Integer index) {
        return currentPlayer.getPlayerHand().get(index);
    }


    //MODIFIES: Player
    //EFFECTS: sets the player's name.
    public void setPlayerName(String name) {
        getCurrentPlayer();
        currentPlayer.setName(name);
    }

    //EFFECTS: the public call to saveGameState. Saves the gamestate.
    public void saveGame() {
        saveGameState();
    }

    //EFFECTS: returns "saveError" - used to clarify if there was an error when saving.
    public Boolean getSaveError() {
        return saveError;
    }

    //EFFECTS: returns "getLoadError" - used to clarify if there was an error when loading.
    public Boolean getLoadError() {
        return loadError;
    }



    //MODIFIES: gameState
    //EFFECTS: saves the gamestate to file
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
            saveError = true;
        }
    }

    //MODIFIES: this
    //EFFECTS: loads gamestate from file. If there were no players saved to the file, the game quits and asks the player
    //         to load the game again.
    private void loadGameState() {
        try {
            gameState = jsonReader.read();

            if (gameState.numCurrentPlayers() <= 1) {
             //   System.out.println("No previous game saved to the file. Please reload and start a new game.");
             //   exit(2);
                JOptionPane.showMessageDialog(this,
                        "Unable to load previous game. Click the button to begin a new game.",
                        "Loading error",
                        JOptionPane.ERROR_MESSAGE);

                loadError = true;

            }

            System.out.println("Loaded " + gameState.getName() + " from " + JSON_STORE);

            for (Player p: gameState.getCurrentPlayers()) {
                playerList.add(p);
            }


        } catch (IOException e) {
            //System.out.println("Unable to read from file: " + JSON_STORE);
            JOptionPane.showMessageDialog(this,
                    "Unable to read from file: " + JSON_STORE + ". Starting a new game.",
                    "Loading error",
                    JOptionPane.ERROR_MESSAGE);

            //System.out.println("Please reload and start a new game.");

            loadError = true;


            //failure = true;
        }

    }

}
