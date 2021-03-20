package ui.visuals;

import model.Card;

import javax.swing.*;
import java.awt.*;

public class CardActionPanel extends JPanel {
    //PICTURE top center of the screen
    // CARDNAME has been discarded.
    // You drew a CARDNAME!
    // possibly a sound??
    // should probably take in a card

    private String card;

    public CardActionPanel() {
        super(new GridLayout(1,1));

    }

    public void cardDrawScreen(Card card) {
        JLabel cardDrawn;
        ImageIcon icon = createImageIcon("FIGURE THIS OUT",
                "FIGURE THIS OUT");
        // WHAT IT SHOULD BE: ImageIcon icon = createImageIcon("images/middle.gif",
        //                                         "a pretty but meaningless splat");

        cardDrawn = new JLabel(card.getColour() + " " + card.getNumber(), icon, JLabel.CENTER);
        cardDrawn.setToolTipText("This is the card you drew.");

        add(cardDrawn);

    }

    public void cardDiscardScreen(Card card) {
        JLabel cardDiscarded;
        ImageIcon icon = createImageIcon("FIGURE THIS OUT",
                "FIGURE THIS OUT");
        // WHAT IT SHOULD BE: ImageIcon icon = createImageIcon("images/middle.gif",
        //                                         "a pretty but meaningless splat");

        cardDiscarded = new JLabel(card.getColour() + " " + card.getNumber(), icon, JLabel.CENTER);
        cardDiscarded.setToolTipText("This is the card you discarded.");

        add(cardDiscarded);
    }



    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = CardActionPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void removeCardActionElements() {
        removeAll();
    }


}
