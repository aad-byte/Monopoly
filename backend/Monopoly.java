package backend;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class Monopoly extends JPanel{
    int tileWidth = 120;
    int tileHeight = 80;
    int squareSize = 120;
    int boardSide = tileWidth * 5 + squareSize * 2;

    int carX = (52*3)/2, carY = (52*29)/2;
    int hatX = (52*3)/2, hatY = (52*29)/2;
    int bootX = (52*3)/2, bootY = (52*29)/2;

    BufferedImage img1, img2, img3, boardImage;

    Monopoly(){
        setPreferredSize(new Dimension(boardSide, boardSide));
        setBackground(new Color(182, 211, 189));

        //images for avatars
        try {
            img1 = ImageIO.read(getClass().getResource("car.png"));
            img2 = ImageIO.read(getClass().getResource("hat.png"));
            img3 = ImageIO.read(getClass().getResource("boot.png"));
        } catch (IOException | NullPointerException e) {
            System.out.println("Image loading failed");
        }

        //try to load board image
        try {
            boardImage = ImageIO.read(getClass().getResource("monopolyBoard.png"));
        } catch (IOException | NullPointerException e) {
            System.err.println("failed to load board image:");
            e.printStackTrace();
        }
    }

    int cellSize = boardSide/16;

    @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Draw board
            if (boardImage != null) {
                g.drawImage(boardImage, 0, 0, 832, 832, null);
            }
          
            if (img1 != null){
                g.drawImage(img1, carX, carY, 80, 80, null); // car
            }else{
                System.out.println("img1");
            }
            if (img2 != null) {
                g.drawImage(img2, hatX, hatY, 55, 55, null); // hat
            }
                if (img3 != null) {
                    g.drawImage(img3, bootX, bootY, 45, 45, null); // boot
                }
                
            
        }


        public void moveCar(int x, int y){
            carX = x;
            carY = y;
            repaint();
        }

        public void moveHat(int x, int y){
            hatX = x;
            hatY = y;
            repaint();
        }

        public void moveBoot(int x, int y){
            bootX = x;
            bootY = y;
            repaint();
        }

}