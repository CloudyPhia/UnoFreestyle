package ui.visuals;

import model.Card;
import model.Player;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/*
 * CITATION: DiscardSelectPanel code based on the SplitPaneDemo2Project from the java oracle library.
 *           URL: https://docs.oracle.com/javase/tutorial/uiswing/components/splitpane.html
 *
 *           Also based partially on the ButtonDemo project from the java oracle library:
             URL: https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
 */

/*
 * UnoCard images were utilized from https://opengameart.org/content/uno-playing-cards-2d by author mehrasaur.
 */

//Represents the discard select panel

public class DiscardSelectPanel extends JPanel implements ListSelectionListener, ActionListener {
    //split screen panel where image of the card shows up on right, name of card on left
    // button to discard at the bottom

    private final JLabel picture;
    private final JList cards;
    private final JSplitPane splitPane;
    private final JScrollPane cardsScrollPane;
    private final JScrollPane pictureScrollPane;
    protected JButton discardButton;

    private final UnoFrame unoFrame;
    private final String[] playerCardNames;

    public DiscardSelectPanel(UnoFrame unoFrame) {

        this.unoFrame = unoFrame;

        playerCardNames = unoFrame.getPlayerCards();

        cards = new JList(playerCardNames);
        cards.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cards.setSelectedIndex(0);
        cards.addListSelectionListener(this);

        cardsScrollPane = new JScrollPane(cards);
        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);

        pictureScrollPane = new JScrollPane(picture);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, cardsScrollPane, pictureScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        discardButton = new JButton("Discard Card");
        setUpDiscardButton();

        Dimension minimumSize = new Dimension(600, 200);
        cardsScrollPane.setMinimumSize(minimumSize);
        pictureScrollPane.setMinimumSize(minimumSize);


        splitPane.setMinimumSize(new Dimension(1200, 400));
        updateLabel(playerCardNames[cards.getSelectedIndex()]);

        addElements();

    }

    //MODIFIES: this
    //EFFECTS: adds the elements to the panel and sets their background colours.
    public void addElements() {
        cardsScrollPane.setOpaque(true);
        discardButton.setOpaque(true);
        discardButton.setBorderPainted(false);
        discardButton.setBackground(new java.awt.Color(237, 242, 251));
        add(cardsScrollPane);
        add(pictureScrollPane);
        add(discardButton);
    }

    //EFFECTS: sets up the discard button.
    private void setUpDiscardButton() {
        discardButton.setVerticalTextPosition(AbstractButton.CENTER);
        discardButton.setHorizontalTextPosition(AbstractButton.CENTER);
        discardButton.setMnemonic(KeyEvent.VK_D);
        discardButton.addActionListener(this);
        discardButton.setActionCommand("discard");
    }

    //change to be a jlist - then you never have to convert it into a differnet object
    // create a helper that takes in an arraylist that copies each of the elements over to a JList
    // and returns the jlist - in mainpanel have playercards return a jlist instead of a string array

    /**
     * Called whenever the value of the selection changes.
     *
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        updateLabel(playerCardNames[list.getSelectedIndex()]);
    }

    //EFFECTS: Renders the selected image
    protected void updateLabel(String name) {
        ImageIcon icon = createImageIcon("images/" + name + ".png");
        picture.setIcon(icon);
        if  (icon != null) {
            picture.setText(null);
        } else {
            picture.setText("Image not found");
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DiscardSelectPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Invoked when an action occurs.
     *
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        discardButton.setEnabled(false);
        Card card = unoFrame.getCardAtIndex(cards.getSelectedIndex());
        ImageIcon icon = createImageIcon("images/" + unoFrame.getCorrespondingCardImageName(card) + ".png");
        JOptionPane.showMessageDialog(unoFrame,
                unoFrame.getPlayerName() + ", You discarded a "
                        + card.getColour() + " " + card.getNumber() + "!",
                "Card drawn",
                JOptionPane.INFORMATION_MESSAGE,
                icon);

        unoFrame.removeCardAtIndex(cards.getSelectedIndex());

        if (unoFrame.getPlayerHandSize() > 1) {
            unoFrame.continueOptions();
        } else if (unoFrame.getPlayerHandSize() == 1) {
            JOptionPane.showMessageDialog(unoFrame,
                    unoFrame.getPlayerName() + " is at Uno!!",
                    "Uno!",
                    JOptionPane.WARNING_MESSAGE);
            unoFrame.continueOptions();
        } else {
            JOptionPane.showMessageDialog(unoFrame, unoFrame.getPlayerName() + " has won the game!");
            unoFrame.ending();
            JOptionPane.showMessageDialog(unoFrame, "Thanks for playing!");
           // unoFrame.exit();
        }

    }
}
