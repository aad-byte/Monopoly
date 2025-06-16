package backend;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class App {
    public static Monopoly monopolyGame;
    public static playerInfoPanel playerDisplay;
    public static  outPutPanel gameLog;
    public static Dice dice;

    public static void main(String[] args) {
        int propertyWidth = 104;
        int propertyHeight = (int)(propertyWidth * 1.5);
        int boardSide = propertyWidth*5 + propertyHeight*2;
        
        
        JFrame frame = new JFrame("Monopoly");
        frame.setSize((boardSide*3)/2, boardSide+26
        );
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        monopolyGame = new Monopoly();
        frame.setLayout(new BorderLayout());

        
        frame.add(monopolyGame, BorderLayout.WEST);//add board on the frame, to the left
        monopolyGame.setBounds(0,0, boardSide, boardSide); //adjust postion of monopoly Game


        JPanel sidePanel = new JPanel();
        
        //make side panel transparent
        sidePanel.setOpaque(true);
        sidePanel.setBackground(new Color(182, 211, 189)); 

        //set remaing window deimensions for side panel
        sidePanel.setPreferredSize(new Dimension(416, boardSide));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        frame.add(sidePanel, BorderLayout.EAST); //aligin on the right
    
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20))); // 20px vertical space

        playerDisplay = new playerInfoPanel(); //create the playerdisplay card

        //wrap the player info display in another panel to prvent the height form expanding
        JPanel playerInfoContainer = new JPanel();
        playerInfoContainer.setLayout(new BorderLayout());
        playerInfoContainer.setBorder(BorderFactory.createTitledBorder("Player Info")); //border title
        playerInfoContainer.setMaximumSize(new Dimension(300, 400));
        JScrollPane scrollPane = new JScrollPane(playerDisplay, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playerInfoContainer.add(scrollPane, BorderLayout.CENTER); //center inside the wrap
        
        sidePanel.add(playerInfoContainer);//add player info container to the side panel
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20))); // 20px vertical space

        gameLog = new outPutPanel(); //create output panel
        sidePanel.add(gameLog);//append it the side panel
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20))); // 20px vertical space

        dice = new Dice(); //create instance of dice
        sidePanel.add(dice);
        
        gameLog.setAlignmentX(0.5f);
        playerInfoContainer.setAlignmentX(0.5f);

        frame.setVisible(true);
        
    }
}