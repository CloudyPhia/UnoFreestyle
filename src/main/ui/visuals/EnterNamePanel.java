package ui.visuals;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//represents the panel where the user inputs their name.

public class EnterNamePanel extends JPanel {
    //ENTER P1/P2/P3/P4's name: _____
    //this should call to messages panel which will then say "nice to meet you, __!"

    private static final String addNameString = "Set Name";
    private JButton nameButton;
    private JPanel buttonPane;
    private JTextField playerNameField;
    private boolean alreadyEnabled = false;

    private UnoFrame unoFrame;

    public EnterNamePanel(UnoFrame unoFrame) {
        super(new BorderLayout());

        this.unoFrame = unoFrame;

        JLabel titleLabel;
        nameButton = new JButton(addNameString);
        AddName addNameListener = new AddName(nameButton);
        nameButton.setActionCommand("nameAdded");
        nameButton.addActionListener(addNameListener);
        nameButton.setEnabled(false);

        titleLabel = new JLabel("Please enter this player's name.", JLabel.CENTER);

        playerNameField = new JTextField(10);
        playerNameField.addActionListener(addNameListener);
        playerNameField.getDocument().addDocumentListener(addNameListener);
//        String name = listModel.getElementAt(
//                list.getSelectedIndex()).toString();

        setUpExtraPanelForButtonSpace();

        add(titleLabel, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

        buttonPane.setVisible(true);
        setVisible(true);
    }

    //EFFECTS: Creates a panel that uses BoxLayout.
    public void setUpExtraPanelForButtonSpace() {
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(playerNameField);
        buttonPane.add(nameButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


    }

    //This listener is shared by the text field and the hire button.
    class AddName implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddName(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        //EFFECTS: takes in the action performed and performs as necessary.
        public void actionPerformed(ActionEvent e) {
            String name = playerNameField.getText();

            //User didn't type in a unique name...
            if (name.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                playerNameField.requestFocusInWindow();
                playerNameField.selectAll();
                return;
            }

            unoFrame.setPlayerName(name);
            JOptionPane.showMessageDialog(unoFrame,
                    "Nice to meet you, " + unoFrame.getPlayerName() + "!");

            unoFrame.nextPlayer();

            if (unoFrame.getPlayerTurn() < 1) {
                unoFrame.beginOptions();
                return;
            }


            //Reset the text field.
            playerNameField.requestFocusInWindow();
            playerNameField.setText("");

        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        //EFFECTS: enables the button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        //EFFECTS: disables the button if the text field is empty, otherwise enables it.
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

}

