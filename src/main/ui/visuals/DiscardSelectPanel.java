package ui.visuals;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/*
 * CITATION: DiscardSelectPanel code obtained (and modified) from SplitPaneDemo2Project
 *           URL: _____
 */

public class DiscardSelectPanel extends JPanel implements ListSelectionListener {
    //split screen panel where image of the card shows up on right, name of card on left
    // button to discard at the bottom
    private UnoFrame unoFrame;

    private JLabel picture;
    private JList cards;
    private JSplitPane splitPane;

    private String[] playerCardNames = unoFrame.getPlayerCards();

    public DiscardSelectPanel() {
        cards = new JList(playerCardNames);
        cards.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cards.setSelectedIndex(0);
        cards.addListSelectionListener(this);

        JScrollPane cardsScrollPane = new JScrollPane(cards);
        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane pictureScrollPane = new JScrollPane(picture);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, cardsScrollPane, pictureScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        Dimension minimumSize = new Dimension(100, 50);
        cardsScrollPane.setMinimumSize(minimumSize);
        pictureScrollPane.setMinimumSize(minimumSize);

        splitPane.setPreferredSize(new Dimension(400, 200));
        updateLabel(playerCardNames[cards.getSelectedIndex()]);
    }

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

    //Renders the selected image
    protected void updateLabel(String name) {
        ImageIcon icon = createImageIcon("images/" + name + ".gif");
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
}
