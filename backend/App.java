package backend;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        int propertyWidth = 104;
        int propertyHeight = (int)(propertyWidth * 1.5);
        int boardSide = propertyWidth*5 + propertyHeight*2;

        JFrame frame = new JFrame("Monopoly");
        frame.setSize((boardSide*3)/2, boardSide+26
        );
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        Monopoly monopolyGame = new Monopoly();
        frame.setLayout(null); //no centering or resizing (get rid of default BorderLayout)
        monopolyGame.setBounds(0,0, boardSide, boardSide); //adjust postion of monopoly Game
        frame.add(monopolyGame); //add board
        frame.setVisible(true);
    }
}