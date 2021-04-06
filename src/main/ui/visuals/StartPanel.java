package ui.visuals;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

//Represents a class that displays the panel that opens at the very beginning of the game.

/*
    CITATION: StartPanel based partially on the ButtonDemo project from the java oracle library.
              URL: https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
 */

public class StartPanel extends JPanel implements ActionListener {
    // start menu - has "UNO" at the top with two buttons for loading and saving

    private static final String START_MENU_TITLE = "Welcome To Janky Uno!";
    private static final String NEW_BUTTON_TOOL_TIP_TXT = "Click this button to start a new game.";
    private static final String LOAD_BUTTON_TOOL_TIP_TXT = "Click this button to load a previous game.";
    private static final String NEW_BUTTON_NAME = "New Game";
    private static final String LOAD_BUTTON_NAME = "Load Game";

    protected JButton newButton;
    protected JButton loadButton;
    protected JLabel titleText;

    private final UnoFrame unoFrame;


    public StartPanel(UnoFrame unoFrame) {
        super(new GridLayout(3, 1));

        this.unoFrame = unoFrame;

        titleText = new JLabel(START_MENU_TITLE);
        titleText.setHorizontalAlignment(JLabel.CENTER);

        setUpButtons();

        newButton.addActionListener(this);
        loadButton.addActionListener(this);

        add(titleText);
        add(loadButton);
        add(newButton);

    }

    //EFFECTS: sets up the new and load buttons.
    public void setUpButtons() {
        newButton = new JButton(NEW_BUTTON_NAME);
        newButton.setVerticalTextPosition(AbstractButton.CENTER);
        newButton.setHorizontalTextPosition(AbstractButton.CENTER);
        newButton.setMnemonic(KeyEvent.VK_D);
        newButton.setActionCommand("new");
        newButton.setOpaque(true);
        newButton.setBorderPainted(false);
        newButton.setBackground(new java.awt.Color(215, 227, 252));

        loadButton = new JButton(LOAD_BUTTON_NAME);
        loadButton.setVerticalTextPosition(AbstractButton.CENTER);
        loadButton.setHorizontalTextPosition(AbstractButton.CENTER);
        loadButton.setMnemonic(KeyEvent.VK_E);
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(false);
        loadButton.setBackground(new java.awt.Color(226, 234, 252));
        loadButton.setActionCommand("load");

        newButton.setToolTipText(NEW_BUTTON_TOOL_TIP_TXT);
        loadButton.setToolTipText(LOAD_BUTTON_TOOL_TIP_TXT);
    }

    /**
     * Invoked when an action occurs.
     *
     *
     */
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) {
            newButton.setEnabled(false);
            loadButton.setEnabled(false);
            unoFrame.newGameSelected();
        } else {
            newButton.setEnabled(false);
            loadButton.setEnabled(false);
            loadOldGame();
        }
    }

    //EFFECTS: displays dialogues that mention loading a previous game. Will also start a new game if the old game
    //         fails to load.
    public void loadOldGame() {
        JOptionPane.showMessageDialog(unoFrame,
                "Attempting to load a previous game...");

        unoFrame.loadGameSelected();

        if (unoFrame.getLoadError()) {
            JOptionPane.showMessageDialog(unoFrame,
                    "Game could not be loaded. Starting a new game now!");
            unoFrame.newGameSelected();
            return;
        }

        JOptionPane.showMessageDialog(unoFrame,
                "Game loaded!");


    }

}
