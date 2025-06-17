package backend;

import javax.swing.*;
import java.awt.*;

public class playerInfoPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel cashLabel;
    private JLabel properites;
    private JLabel avatarLabel;

    public playerInfoPanel() {
        setLayout(new GridLayout(4, 1));
        setPreferredSize(new Dimension(200, 370));

        nameLabel = new JLabel("Name: ");
        cashLabel = new JLabel("Cash: ");
        properites = new JLabel("Properties Owned: ");
        avatarLabel = new JLabel("Avatar: "); // default avatar

        add(nameLabel);
        add(cashLabel);
        add(properites);
        add(avatarLabel);
    }

    public void updateInfo(String name, double cash, String propList, String avatar) {
        nameLabel.setText("Name: " + name);
        cashLabel.setText("Cash: $" + cash);
        properites.setText("Properties owned: " + propList);
        avatarLabel.setText("Avatar: " + avatar);
    }
}