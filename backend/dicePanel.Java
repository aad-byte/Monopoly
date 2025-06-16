package backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DicePanel extends JPanel {
    private JButton rollButton;
    private JLabel resultLabel;

    public DicePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(300, 100));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        rollButton = new JButton("Roll Dice");
        rollButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultLabel = new JLabel("Result: ");
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(rollButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(resultLabel);

        rollButton.addActionListener(e -> {
            int roll = (int)(Math.random() * 6) + 1;
            resultLabel.setText("Result: " + roll);

            // TODO: You could notify main game logic here
        });
    }

    public void setOnRoll(ActionListener listener) {
        for (ActionListener l : rollButton.getActionListeners()) {
            rollButton.removeActionListener(l);
        }
        rollButton.addActionListener(listener);
    }

    public int getLastRoll() {
        String text = resultLabel.getText();
        if (text.startsWith("Result: ")) {
            return Integer.parseInt(text.substring(8));
        }
        return -1;
    }
}