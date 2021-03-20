package ui.visuals;

import javax.swing.*;
import java.awt.*;

public class MessagesPanel extends JPanel {
    //basic messages for the game.
    //"Nice to meet you, ____!"
    //"Loading old game..."
    //"Game could not be loaded."
    //"Thanks for playing!"
    private UnoFrame unoFrame;

    private JLabel niceToMeetYou;
    private JLabel loadingOldGame;
    private JLabel gameNotLoad;
    private JLabel thanksForPlaying;

    private static final String LOADING_MESSAGE = "Loading old game...";
    private static final String COULD_NOT_LOAD_MESSAGE = "Old game could not be loaded. Starting new game...";
    private static final String THANKS_MESSAGE = "Thanks for playing!";


    public MessagesPanel() {
        super(new GridLayout(1, 1));

        niceToMeetYou = new JLabel(setGreetingMessage());
        loadingOldGame = new JLabel(LOADING_MESSAGE);
        gameNotLoad = new JLabel(COULD_NOT_LOAD_MESSAGE);
        thanksForPlaying = new JLabel(THANKS_MESSAGE);

    }

    //TODO: maybe change this to passing in a player?
    private String setGreetingMessage() {
        String greetingMessage = "Nice to meet you, " + unoFrame.getPlayerName() + "!";
        return greetingMessage;
    }

    public void addNiceToMeetYouMessage() {
        removeAll();
        add(niceToMeetYou);
    }

    public void removeNiceToMeetYouMessage() {
        remove(niceToMeetYou);
    }

    public void addLoadingMessage() {
        removeAll();
        add(loadingOldGame);
    }

    public void removeLoadingMessage() {
        remove(loadingOldGame);
    }

    public void addGameNotLoadMessage() {
        removeAll();
        add(gameNotLoad);
    }

    public void removeGameNotLoadMessage() {
        remove(gameNotLoad);
    }

    public void addThanksForPlayingMessage() {
        removeAll();
        add(thanksForPlaying);
    }

    public void removeThanksForPlayingMessage() {
        remove(thanksForPlaying);
    }


}
