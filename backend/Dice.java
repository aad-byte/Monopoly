package backend;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Dice extends JPanel {
    private BufferedImage diceImage; // this holds the current dice face

    public Dice() {
        setMaximumSize(new Dimension(120, 120)); //set preffered dimensions of dice
        setDiceFace(1); // show dice1.png by default
    }

    // call when dice is rolled 
    public void setDiceFace(int value) {
        try {
            diceImage = ImageIO.read(getClass().getResource("dice" + value + ".png")); //read image respective to value passef in
            repaint(); // redraws the panel
        } catch (IOException | NullPointerException e) {
            System.out.println("could not load dice" + value + ".png"); //error message
        }
    }

    // draw dice on the screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (diceImage != null) {
            g.drawImage(diceImage, 30, 30, 60, 60, null); // x, y, width, height
        }
    }
}
