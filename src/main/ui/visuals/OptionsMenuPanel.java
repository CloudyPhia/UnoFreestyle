package ui.visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class OptionsMenuPanel extends JPanel implements ActionListener {
    //PLAYER'S name displayed at the top
    //buttons for CHOOSE,DRAW, and EXIT
    private UnoFrame unoFrame;

    private static final String DRAW_BUTTON_TOOL_TIP_TXT = "Click this button to start a new game.";
    private static final String DISCARD_BUTTON_TOOL_TIP_TXT = "Click this button to load a previous game.";
    private static final String DRAW_BUTTON_NAME = "Draw";
    private static final String DISCARD_BUTTON_NAME = "Discard";
    private static final String END_BUTTON_NAME = "End Game";

    protected JButton drawButton;
    protected JButton discardButton;
    protected JButton endButton;
    protected JLabel titleText;


    public OptionsMenuPanel() {
        super(new GridLayout(3, 1));
        titleText = new JLabel(optionsMenuTitle());
        titleText.setHorizontalAlignment(JLabel.CENTER);

        drawButton = new JButton(DRAW_BUTTON_NAME);
        setUpButton(drawButton);
        drawButton.setActionCommand("draw");

        discardButton = new JButton(DISCARD_BUTTON_NAME);
        setUpButton(discardButton);
        discardButton.setActionCommand("discard");

        endButton = new JButton(END_BUTTON_NAME);
        setUpButton(endButton);
        endButton.setActionCommand("end");

        add(drawButton);
        add(discardButton);
        add(endButton);

        drawButton.setEnabled(true);
        discardButton.setEnabled(true);
        endButton.setEnabled(true);

    }

    public void actionPerformed(ActionEvent e) {
        if ("draw".equals(e.getActionCommand())) {
            drawButton.setEnabled(false);
            discardButton.setEnabled(false);
            endButton.setEnabled(false);
            unoFrame.drawCard();
        } else if ("discard".equals(e.getActionCommand())) {
            drawButton.setEnabled(false);
            discardButton.setEnabled(false);
            endButton.setEnabled(false);
            unoFrame.discardCard();
        } else if ("end".equals(e.getActionCommand())) {
            drawButton.setEnabled(false);
            discardButton.setEnabled(false);
            endButton.setEnabled(false);
            unoFrame.endGame();
        } else {
            drawButton.setEnabled(true);
            discardButton.setEnabled(true);
            endButton.setEnabled(true);
        }
    }


    public void setUpButton(JButton button) {
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.setMnemonic(KeyEvent.VK_D);
    }

    public String optionsMenuTitle() {
        String options = "Here are your options, " + unoFrame.getPlayerName() + "!";
        return options;
    }


//    public StartPanel() {
//        super(new GridLayout(3, 1));
//        titleText = new JLabel(START_MENU_TITLE);
//        titleText.setHorizontalAlignment(JLabel.CENTER);
//
//        newButton = new JButton(NEW_BUTTON_NAME);
//        newButton.setVerticalTextPosition(AbstractButton.CENTER);
//        newButton.setHorizontalTextPosition(AbstractButton.CENTER);
//        newButton.setMnemonic(KeyEvent.VK_D);
//        newButton.setActionCommand("new");
//
//        loadButton = new JButton(LOAD_BUTTON_NAME);
//        loadButton.setVerticalTextPosition(AbstractButton.CENTER);
//        loadButton.setHorizontalTextPosition(AbstractButton.CENTER);
//        loadButton.setMnemonic(KeyEvent.VK_D);
//        loadButton.setActionCommand("load");
//
//        newButton.setToolTipText(NEW_BUTTON_TOOL_TIP_TXT);
//        newButton.setToolTipText(LOAD_BUTTON_TOOL_TIP_TXT);
//
//        add(titleText);
//        add(loadButton);
//        add(newButton);
//
//    }
//
//
//    public void actionPerformed(ActionEvent e) {
//        if ("new".equals(e.getActionCommand())) {
//            newButton.setEnabled(false);
//            loadButton.setEnabled(false);
//            unoFrame.newGameSelected();
//        } else if ("load".equals(e.getActionCommand())) {
//            newButton.setEnabled(false);
//            loadButton.setEnabled(false);
//            unoFrame.loadGameSelected();
//        } else {
//            newButton.setEnabled(true);
//            loadButton.setEnabled(true);
//        }
//    }
}
