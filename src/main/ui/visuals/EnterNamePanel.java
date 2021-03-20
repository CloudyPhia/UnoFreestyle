package ui.visuals;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterNamePanel extends JPanel {
    //ENTER P1/P2/P3/P4's name: _____
    //this should call to messages panel which will then say "nice to meet you, __!"
    private UnoFrame unoFrame;

    private static final String addNameString = "Set Name";
    private JButton nameButton;
    private JTextField playerName;
    private boolean alreadyEnabled = false;

    public EnterNamePanel() {
        super(new BorderLayout());
        JLabel titleLabel;
        nameButton = new JButton(addNameString);
        AddName addName = new AddName(nameButton);
        nameButton.setActionCommand("nameAdded");

        titleLabel = new JLabel(setTitle(), JLabel.CENTER);

        playerName = new JTextField(10);
        playerName.addActionListener(addName);
        playerName.getDocument().addDocumentListener(addName);
//        String name = listModel.getElementAt(
//                list.getSelectedIndex()).toString();

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(playerName);
        buttonPane.add(nameButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(titleLabel, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    public String setTitle() {
        String titleString = "Please enter this player's name.";
        return titleString;
    }


    //This listener is shared by the text field and the hire button.
    class AddName implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddName(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = playerName.getText();

            //User didn't type in a unique name...
            if (name.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                playerName.requestFocusInWindow();
                playerName.selectAll();
                return;
            }

//            int index = list.getSelectedIndex(); //get selected index
//            if (index == -1) { //no selection, so insert at beginning
//                index = 0;
//            } else {           //add after the selected item
//                index++;
//            }

//            listModel.insertElementAt(playerName.getText(), index);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());


            unoFrame.setPlayerName(name);


            //Reset the text field.
            playerName.requestFocusInWindow();
            playerName.setText("");

//            //Select the new item and make it visible.
//            list.setSelectedIndex(index);
//            list.ensureIndexIsVisible(index);
        }

//        //This method tests for string equality. You could certainly
//        //get more sophisticated about the algorithm.  For example,
//        //you might want to ignore white space and capitalization.
//        protected boolean alreadyInList(String name) {
//            return listModel.contains(name);
//        }

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

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

//    public void actionPerformed(ActionEvent e) {
//        nameButton.setEnabled(false);
//
//        if ("nameAdded".equals(e.getActionCommand())) {
//            nameButton.setEnabled(false);
//
//        } else {
//            nameButton.setEnabled(true);
//        }
//
//        String name = playerName.getText();
//
//        //User didn't type in a unique name...
//        if (name.equals("")) {
//            Toolkit.getDefaultToolkit().beep();
//            playerName.requestFocusInWindow();
//            playerName.selectAll();
//            return;
//        }
//
//        unoFrame.setPlayerName(name);
//
//        playerName.requestFocusInWindow();
//        playerName.setText("");

//        int index = list.getSelectedIndex(); //get selected index
//        if (index == -1) { //no selection, so insert at beginning
//            index = 0;
//        } else {           //add after the selected item
//            index++;
//        }



        //listModel.insertElementAt(employeeName.getText(), index);
        //If we just wanted to add to the end, we'd do this:
        //listModel.addElement(employeeName.getText());

        //Reset the text field.

//        //Select the new item and make it visible.
//        list.setSelectedIndex(index);
//        list.ensureIndexIsVisible(index);
  //      }

//    //Required by DocumentListener.
//    public void insertUpdate(DocumentEvent e) {
//        enableButton();
//    }
//
//    //Required by DocumentListener.
//    public void removeUpdate(DocumentEvent e) {
//        handleEmptyTextField(e);
//    }
//
//    //Required by DocumentListener.
//    public void changedUpdate(DocumentEvent e) {
//        if (!handleEmptyTextField(e)) {
//            enableButton();
//        }
//    }
//
//    private void enableButton() {
//        if (!alreadyEnabled) {
//            nameButton.setEnabled(true);
//        }
//    }
//
//    private boolean handleEmptyTextField(DocumentEvent e) {
//        if (e.getDocument().getLength() <= 0) {
//            nameButton.setEnabled(false);
//            alreadyEnabled = false;
//            return true;
//        }
//        return false;
//    }


}

