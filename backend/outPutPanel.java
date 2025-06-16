package backend;

import javax.swing.*;
import java.awt.*;

public class outPutPanel extends JPanel {
    private JTextArea outputArea; //update through instance methods

    public outPutPanel() {
        setLayout(new BorderLayout()); //set layout
        setPreferredSize(new Dimension(300, 150));
        setMaximumSize(new Dimension(300, 150));
        setBorder(BorderFactory.createTitledBorder("Game Log"));

        outputArea = new JTextArea(); //initalize box
        outputArea.setEditable(false); //cannot type directly into box
        outputArea.setLineWrap(true); //words wrap instead of side scrolling
        outputArea.setWrapStyleWord(true); //entire words, not just middle of words wrap

        JScrollPane scrollPane = new JScrollPane(outputArea);//wrap in scroll pane
        add(scrollPane, BorderLayout.CENTER); //add to center of panel
    }

    public void appendMessage(String message) { 
        outputArea.append(message + "\n"); //add message to the box
    }

    public void clearMessages() {
        outputArea.setText(""); //clear message from the box
    }
}
