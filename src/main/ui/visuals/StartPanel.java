package ui.visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class StartPanel extends JPanel implements ActionListener {
    // start menu - has "UNO" at the top with two buttons for loading and saving
    private UnoFrame unoFrame;

    private static final String START_MENU_TITLE = "Welcome To Janky Uno!";
    private static final String NEW_BUTTON_TOOL_TIP_TXT = "Click this button to start a new game.";
    private static final String LOAD_BUTTON_TOOL_TIP_TXT = "Click this button to load a previous game.";
    private static final String NEW_BUTTON_NAME = "New Game";
    private static final String LOAD_BUTTON_NAME = "Load Game";

    protected JButton newButton;
    protected JButton loadButton;
    protected JLabel titleText;

    public StartPanel() {
        super(new GridLayout(3, 1));
        titleText = new JLabel(START_MENU_TITLE);
        titleText.setHorizontalAlignment(JLabel.CENTER);

        newButton = new JButton(NEW_BUTTON_NAME);
        newButton.setVerticalTextPosition(AbstractButton.CENTER);
        newButton.setHorizontalTextPosition(AbstractButton.CENTER);
        newButton.setMnemonic(KeyEvent.VK_D);
        newButton.setActionCommand("new");

        loadButton = new JButton(LOAD_BUTTON_NAME);
        loadButton.setVerticalTextPosition(AbstractButton.CENTER);
        loadButton.setHorizontalTextPosition(AbstractButton.CENTER);
        loadButton.setMnemonic(KeyEvent.VK_D);
        loadButton.setActionCommand("load");

        newButton.setToolTipText(NEW_BUTTON_TOOL_TIP_TXT);
        newButton.setToolTipText(LOAD_BUTTON_TOOL_TIP_TXT);

        add(titleText);
        add(loadButton);
        add(newButton);

    }


    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) {
            newButton.setEnabled(false);
            loadButton.setEnabled(false);
            unoFrame.newGameSelected();
        } else if ("load".equals(e.getActionCommand())) {
            newButton.setEnabled(false);
            loadButton.setEnabled(false);
            unoFrame.loadGameSelected();
        } else {
            newButton.setEnabled(true);
            loadButton.setEnabled(true);
        }
    }



}
