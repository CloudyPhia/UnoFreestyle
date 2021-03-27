package ui.visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

//Represents the panel that displays when the player selects "end game".

public class EndPanel extends JPanel implements ActionListener {
    protected JButton saveButton;
    protected JButton endButton;
    protected JLabel titleText;
    protected JLabel endText;

    private UnoFrame unoFrame;

    private static final String END_MENU_TITLE = "So, you're looking to end the game.";
    private static final String SAVE_BUTTON_TOOL_TIP_TXT = "Click this button to save your game and end.";
    private static final String END_BUTTON_TOOL_TIP_TXT = "Click this button to end the game WITHOUT saving.";
    private static final String SAVE_BUTTON_NAME = "Save Game and End";
    private static final String END_BUTTON_NAME = "End Game without saving.";
    private static final String END_TITLE_TEXT = "You can close the window now!";

    public EndPanel(UnoFrame unoFrame) {
        super(new GridLayout(3, 1));

        this.unoFrame = unoFrame;

        titleText = new JLabel(END_MENU_TITLE);
        titleText.setHorizontalAlignment(JLabel.CENTER);

        add(titleText);

        if (unoFrame.getEndFromDiscard()) {
            finishText();
            return;
        }

        setUpSaveButton();

        setUpEndButton();

        saveButton.setToolTipText(SAVE_BUTTON_TOOL_TIP_TXT);
        endButton.setToolTipText(END_BUTTON_TOOL_TIP_TXT);

        saveButton.addActionListener(this);
        endButton.addActionListener(this);


        add(saveButton);
        add(endButton);



    }

    //EFFECTS: sets up the save button.
    public void setUpSaveButton() {
        saveButton = new JButton(SAVE_BUTTON_NAME);
        saveButton.setVerticalTextPosition(AbstractButton.CENTER);
        saveButton.setHorizontalTextPosition(AbstractButton.CENTER);
        saveButton.setMnemonic(KeyEvent.VK_D);
        saveButton.setActionCommand("save");
        saveButton.setEnabled(true);
    }

    //EFFECTS: sets up the end button.
    public void setUpEndButton() {
        endButton = new JButton(END_BUTTON_NAME);
        endButton.setVerticalTextPosition(AbstractButton.CENTER);
        endButton.setHorizontalTextPosition(AbstractButton.CENTER);
        endButton.setMnemonic(KeyEvent.VK_E);
        endButton.setActionCommand("end");
        endButton.setEnabled(true);
    }

    //EFFECTS: displays the text at the very end of the game, when all buttons are disabled and nothing more can be done
    public void finishText() {
        titleText.setText(END_TITLE_TEXT);
    }

    /**
     * Invoked when an action occurs.
     *
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("save".equals(e.getActionCommand())) {
            saveButton.setEnabled(false);
            endButton.setEnabled(false);
            JOptionPane.showMessageDialog(unoFrame,
                    "Saving the game...");
            unoFrame.saveGame();

            if (unoFrame.getSaveError()) {
                JOptionPane.showMessageDialog(unoFrame,
                        "Game could not be saved (FileNotFound). Thanks for playing!");
            } else {
                JOptionPane.showMessageDialog(unoFrame,
                        "Game saved! Thanks for playing!");
            }

            finishText();

        } else {
            saveButton.setEnabled(false);
            endButton.setEnabled(false);

            JOptionPane.showMessageDialog(unoFrame,
                    "Thanks for playing! Have a great rest of your day!");

            finishText();


        }
    }
}
