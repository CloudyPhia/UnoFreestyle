package ui.visuals;

import model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static ui.visuals.DiscardSelectPanel.createImageIcon;

//Represents the panel where the user can select their options on their turn.

/*
    CITATION: OptionsMenuPanel based partially on the ButtonDemo project from the java oracle library.
              URL: https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
 */

public class OptionsMenuPanel extends JPanel implements ActionListener {
    //PLAYER'S name displayed at the top
    //buttons for CHOOSE,DRAW, and EXIT

    private static final String DRAW_BUTTON_TOOL_TIP_TXT = "Click this button to start a new game.";
    private static final String DISCARD_BUTTON_TOOL_TIP_TXT = "Click this button to load a previous game.";
    private static final String DRAW_BUTTON_NAME = "Draw";
    private static final String DISCARD_BUTTON_NAME = "Discard";
    private static final String END_BUTTON_NAME = "End Game";


    protected JButton drawButton;
    protected JButton discardButton;
    protected JButton endButton;
    protected JLabel titleText;
    private UnoFrame unoFrame;


    public OptionsMenuPanel(UnoFrame unoFrame) {
        super(new GridLayout(4, 1));
        this.unoFrame = unoFrame;

        titleText = new JLabel(optionsMenuTitle());
        titleText.setHorizontalAlignment(JLabel.CENTER);

        setUpButtons();

        drawButton.addActionListener(this);
        discardButton.addActionListener(this);
        endButton.addActionListener(this);

        drawButton.setEnabled(true);
        discardButton.setEnabled(true);
        endButton.setEnabled(true);

        add(titleText);
        add(drawButton);
        add(discardButton);
        add(endButton);

    }

    //EFFECTS: sets up the draw, discard, and end buttons.
    public void setUpButtons() {
        drawButton = new JButton(DRAW_BUTTON_NAME);
        setUpButton(drawButton);
        drawButton.setActionCommand("draw");
        drawButton.setOpaque(true);
        drawButton.setBorderPainted(false);
        drawButton.setBackground(new java.awt.Color(237, 242, 251));

        discardButton = new JButton(DISCARD_BUTTON_NAME);
        setUpButton(discardButton);
        discardButton.setActionCommand("discard");
        discardButton.setOpaque(true);
        discardButton.setBorderPainted(false);
        discardButton.setBackground(new java.awt.Color(226, 234, 252));

        endButton = new JButton(END_BUTTON_NAME);
        setUpButton(endButton);
        endButton.setActionCommand("end");
        endButton.setOpaque(true);
        endButton.setBorderPainted(false);
        endButton.setBackground(new java.awt.Color(215, 227, 252));
    }

    /**
     * Invoked when an action occurs.
     *
     *
     */
    public void actionPerformed(ActionEvent e) {
        if ("draw".equals(e.getActionCommand())) {
            drawButton.setEnabled(false);
            discardButton.setEnabled(false);
            endButton.setEnabled(false);
            draw();
        } else if ("discard".equals(e.getActionCommand())) {
            drawButton.setEnabled(false);
            discardButton.setEnabled(false);
            endButton.setEnabled(false);
            unoFrame.discardCard();
        } else {
            drawButton.setEnabled(false);
            discardButton.setEnabled(false);
            endButton.setEnabled(false);
            unoFrame.endGame();
        }
    }

    //EFFECTS: displays the card image with text describing what card was drawn. It also calls a method that adds the
    //         card to the hand.
    public void draw() {
        Card card = unoFrame.drawCard();
        ImageIcon icon = createImageIcon("images/" + unoFrame.getCorrespondingCardImageName(card) + ".png");
        JOptionPane.showMessageDialog(unoFrame,
                "Wow, " + unoFrame.getPlayerName() + "! You drew a "
                        + card.getColour() + " " + String.valueOf(card.getNumber()) + "!",
                "Card drawn",
                JOptionPane.INFORMATION_MESSAGE,
                icon);

        unoFrame.continueOptions();
    }

    //EFFECTS: sets up a generic button with center text alignment.
    public void setUpButton(JButton button) {
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.setMnemonic(KeyEvent.VK_D);
    }

    //EFFECTS: constructs the title of the screen, including the player-specific name.
    public String optionsMenuTitle() {
        String options = "Here are your options, " + unoFrame.getPlayerName() + "!";
        return options;
    }

}
