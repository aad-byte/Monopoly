package backend;

import javax.swing.*;
import java.awt.*;

public class propertyInfoPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel rentLabel;
    private JLabel ownershipStatusLabel;
    private JLabel ownerLabel;
    private JLabel housesLabel;
    private JLabel assetVLabel;

    public propertyInfoPanel() {
        setLayout(new GridLayout(4, 1));
        setPreferredSize(new Dimension(200, 370));

        nameLabel = new JLabel("Name: ");
        priceLabel = new JLabel("Price: ");
        rentLabel = new JLabel("Rent: ");
        ownerLabel = new JLabel("Owner: "); 
        housesLabel = new JLabel("# of houses: "); 
        assetVLabel = new JLabel("Value: "); 

        add(nameLabel);
        add(priceLabel);
        add(rentLabel);
        add(ownerLabel);
        add(housesLabel);
        add(assetVLabel);

    }

    public void updateInfo(String name, String price, String rent, String owner, String houses, String AssetValue) {
        nameLabel.setText("Name: " + name);
        priceLabel.setText("Price: $" + price);
        rentLabel.setText("Rent: $" + rent);
        ownerLabel.setText("Owner: " + owner);
        housesLabel.setText("# of houses: " + houses);
        assetVLabel.setText("Value: " + AssetValue);
    }
}