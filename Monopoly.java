import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;


public class Monopoly extends JPanel{
    int propertyWidth = 80;
    int propertyHeight = (int)(propertyWidth * 1.5);
    int boardSide = propertyWidth*5 + propertyHeight*2;

    Monopoly(){
        setPreferredSize(new Dimension(boardSide, boardSide));
        setBackground(new Color(211, 240, 213));
    }
}
