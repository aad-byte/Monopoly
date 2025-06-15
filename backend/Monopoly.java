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

    BufferedImage img1, img2, img3;

    Monopoly(){
        setPreferredSize(new Dimension(boardSide, boardSide));
        setBackground(new Color(182, 211, 189));

        //images for avatars
        try {
            img1 = ImageIO.read(new File("car.png"));
            img2 = ImageIO.read(new File("hat.png"));
            img3 = ImageIO.read(new File("boot.png"));
            } catch (IOException e) {
            e.printStackTrace();
        }

        //draw all avatars starting at one point
    }

    int cellSize = boardSide/16;

    @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i <= 16; i++) {
                int position = i * cellSize;
            
                // Horizontal lines
                g.drawLine(0, position, boardSide, position);
                // Vertical lines
                g.drawLine(position, 0, position, boardSide);
            }
            g.setColor(Color.RED);
            g.drawLine(cellSize*3, 0, cellSize*3, boardSide);
            g.setColor(Color.BLUE);
            g.drawLine(0, boardSide-(cellSize*3), boardSide, boardSide-(cellSize*3));
            g.setColor(Color.GREEN);
            g.drawLine(0, cellSize*3, boardSide, cellSize*3);
            g.setColor(Color.yellow);
            g.drawLine(boardSide - cellSize*3, 0,  boardSide - cellSize*3, boardSide);
            
        }


        protected void moveCar(int x, int y){
            protected void paintComponent(Graphics g){
                g.drawImage(img1, x, y, 40, 40, null);
            }
        }

        protected void moveHat(int x, int y){
            protected void paintComponent(Graphics g){
                g.drawImage(img2, x, y, 40, 40, null);
            }
        }

        protected void moveBoot(int x, int y){
            protected void paintComponent(Graphics g){
                g.drawImage(img2, x, y, 40, 40, null);
            }
        }

}