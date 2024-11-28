import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class CelticReading extends JPanel {
    private JLabel[] cardImages;
    private JTextArea[] cardDescriptions;
    private JButton revealButton;
    private TarotController controller;

    public CelticReading(TarotController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Create a panel for the card layout mimicking Celtic Cross spread
        JPanel spreadPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Initialize arrays for 5 cards
        cardImages = new JLabel[5];
        cardDescriptions = new JTextArea[5];

        // Create card labels and descriptions
        for (int i = 0; i < 5; i++) {
            cardImages[i] = new JLabel();
            cardImages[i].setHorizontalAlignment(JLabel.CENTER);
            cardDescriptions[i] = new JTextArea(5, 20);
            cardDescriptions[i].setEditable(false);
        }

        // Reveal button
        revealButton = new JButton("Reveal Celtic Cross");
        revealButton.setActionCommand("REVEAL_CELTIC_CROSS");
        revealButton.addActionListener(controller);

        // Position cards in a cross-like layout
        gbc.gridx = 1;
        gbc.gridy = 1;
        spreadPanel.add(cardImages[0], gbc); // Center card

        gbc.gridx = 0;
        gbc.gridy = 1;
        spreadPanel.add(cardImages[1], gbc); // Left card

        gbc.gridx = 2;
        gbc.gridy = 1;
        spreadPanel.add(cardImages[2], gbc); // Right card

        gbc.gridx = 1;
        gbc.gridy = 0;
        spreadPanel.add(cardImages[3], gbc); // Top card

        gbc.gridx = 1;
        gbc.gridy = 2;
        spreadPanel.add(cardImages[4], gbc); // Bottom card

        gbc.gridx = 1;
        gbc.gridy = 3;
        spreadPanel.add(revealButton, gbc);
        
        

        // Add description panels below the cards
        JPanel descriptionPanel = new JPanel(new GridLayout(5, 1));
        for (int i = 0; i < 5; i++) {
            descriptionPanel.add(new JScrollPane(cardDescriptions[i]));
        }

        // Layout the main panel
        add(spreadPanel, BorderLayout.CENTER);
        add(descriptionPanel, BorderLayout.EAST);

        // Initial setup with card backs
        initializeCardBacks();
    }

    private void initializeCardBacks() {
        String backImagePath = "images/tarot_back.png";
        ImageIcon backImageIcon = new ImageIcon(backImagePath);
        Image backImage = backImageIcon.getImage();
        Image resizedBackImage = backImage.getScaledInstance(620 / 5, 1270 / 5, Image.SCALE_SMOOTH);
        backImageIcon = new ImageIcon(resizedBackImage);

        for (int i = 0; i < 5; i++) {
            cardImages[i].setIcon(backImageIcon);
            cardDescriptions[i].setText("Card " + (i + 1) + ": Unknown");
        }
    }

    public void revealCard(Tarot card, int position, boolean upsideDown) {
        if (position < 0 || position >= 5) {
            throw new IllegalArgumentException("Invalid card position");
        }

        // Update text description
        cardDescriptions[position].setText(
            "Card Name: " + card.getName() + "\n" +
            "Arcana: " + card.getArcana() + "\n" +
            "Suit: " + (card.getSuit() == null ? "N/A" : card.getSuit()) + "\n" +
            "Upright Meaning: " + card.getUprightMeaning() + "\n" +
            "Reversed Meaning: " + card.getReversedMeaning()
        );

        // Load and potentially rotate image
        String imagePath = "images/" + card.getName().toLowerCase().replace(" ", "_") + ".png";
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            if (upsideDown) {
                AffineTransform tx = AffineTransform.getRotateInstance(Math.PI, image.getWidth() / 2, image.getHeight() / 2);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                image = op.filter(image, null);
            }
            ImageIcon cardIcon = new ImageIcon(image.getScaledInstance(620 / 5, 1270 / 5, Image.SCALE_SMOOTH));
            cardImages[position].setIcon(cardIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetReading() {
        initializeCardBacks();
    }
}