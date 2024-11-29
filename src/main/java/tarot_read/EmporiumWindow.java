package tarot_read;

import javax.swing.*;
import java.awt.*;

public class EmporiumWindow {

    // Sample card data
    private static String[] cardNames = {"The Fool", "The Magician", "The High Priestess",
                                        "The Empress", "The Emperor", "The Hierophant",
                                        "The Lovers", "The Chariot", "The Strength", 
                                        "The Hermit"};
    private static String[] cardImages = {"src/main/resources/images/the_fool.png", "src/main/resources/images/the_magician.png", "src/main/resources/images/the_high_priestess.png",
                                        "src/main/resources/images/the_empress.png", "src/main/resources/images/the_emperor.png", "src/main/resources/images/the_hierophant.png",
                                        "src/main/resources/images/the_lovers.png", "src/main/resources/images/the_chariot.png", "src/main/resources/images/the_strength.png",
                                        "src/main/resources/images/the_hermit.png"};
    private static int currentCardIndex = 0; 

    public static void show(JFrame parentFrame) {
        // Create the window for the dialog
        JDialog dialog = new JDialog(parentFrame, "Emporium", true);
        dialog.setSize(400, 600);
        dialog.setLocationRelativeTo(parentFrame);

        // Main layout for the dialog
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Card Name Section
        JLabel cardNameLabel = new JLabel(cardNames[currentCardIndex], SwingConstants.CENTER);
        cardNameLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 24));
        mainPanel.add(cardNameLabel, BorderLayout.NORTH);

        // Card Image and Navigation Buttons Section
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));

        // Left Arrow Button
        JPanel leftButtonPanel = new JPanel(new GridBagLayout());
        JButton leftButton = new JButton("←");
        leftButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        leftButton.addActionListener(e -> navigateCards(-1, cardNameLabel, centerPanel));
        leftButtonPanel.add(leftButton);
        centerPanel.add(leftButtonPanel, BorderLayout.WEST);

        // Right Arrow Button
        JPanel rightButtonPanel = new JPanel(new GridBagLayout());
        JButton rightButton = new JButton("→");
        rightButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        rightButton.addActionListener(e -> navigateCards(1, cardNameLabel, centerPanel));
        rightButtonPanel.add(rightButton);
        centerPanel.add(rightButtonPanel, BorderLayout.EAST);

        // Card Image
        JLabel cardImageLabel = new JLabel();
        cardImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateCardImage(cardImageLabel, cardImages[currentCardIndex]);
        centerPanel.add(cardImageLabel, BorderLayout.CENTER);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Close Button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to dialog
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    // Navigate cards
    private static void navigateCards(int direction, JLabel cardNameLabel, JPanel centerPanel) {
        currentCardIndex = (currentCardIndex + direction + cardNames.length) % cardNames.length;

        cardNameLabel.setText(cardNames[currentCardIndex]);
        JLabel cardImageLabel = (JLabel) ((BorderLayout) centerPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        updateCardImage(cardImageLabel, cardImages[currentCardIndex]);
    }

    // Update the card images
    private static void updateCardImage(JLabel cardImageLabel, String imagePath) {
        ImageIcon cardImage = new ImageIcon(imagePath); // Replace with the actual image path
        cardImageLabel.setIcon(new ImageIcon(cardImage.getImage().getScaledInstance(620 / 3, 1270 / 3, Image.SCALE_SMOOTH))); // Resize the image
    }
}
