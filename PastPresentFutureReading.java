import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class PastPresentFutureReading extends JPanel {
    private JTextArea cardDisplay1, cardDisplay2, cardDisplay3;
    private JLabel cardImage1, cardImage2, cardImage3;
    private JButton reveal_past_button, reveal_present_button, reveal_future_button, reset_button;
    private TarotController controller;

    public PastPresentFutureReading(TarotController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        JPanel panel5 = new JPanel(new GridBagLayout());

        // Setup buttons and images similar to your original implementation
        cardDisplay1 = new JTextArea();
        cardDisplay1.setEditable(false);
        cardImage1 = new JLabel();
        cardImage1.setHorizontalAlignment(JLabel.CENTER);

        cardDisplay2 = new JTextArea();
        cardDisplay2.setEditable(false);
        cardImage2 = new JLabel();
        cardImage2.setHorizontalAlignment(JLabel.CENTER);

        cardDisplay3 = new JTextArea();
        cardDisplay3.setEditable(false);
        cardImage3 = new JLabel();
        cardImage3.setHorizontalAlignment(JLabel.CENTER);

        reveal_past_button = new JButton("Reveal Past");
        reveal_present_button = new JButton("Reveal Present");
        reveal_future_button = new JButton("Reveal Future");
        reset_button = new JButton("Reset");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Create card panels similar to your original implementation
        JPanel card_panel1 = createCardPanel(cardImage1, reveal_past_button, cardDisplay1);
        JPanel card_panel2 = createCardPanel(cardImage2, reveal_present_button, cardDisplay2);
        JPanel card_panel3 = createCardPanel(cardImage3, reveal_future_button, cardDisplay3);

        // Add to GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel5.add(card_panel1, gbc);

        gbc.gridx = 1;
        panel5.add(card_panel2, gbc);

        gbc.gridx = 2;
        panel5.add(card_panel3, gbc);

        // Reset button
        panel1.add(reset_button);

        // Add panels to this main panel
        add(panel1, BorderLayout.NORTH);
        add(panel5, BorderLayout.CENTER);

        // Set action commands
        reveal_past_button.setActionCommand("REVEAL_PAST");
        reveal_present_button.setActionCommand("REVEAL_PRESENT");
        reveal_future_button.setActionCommand("REVEAL_FUTURE");
        reset_button.setActionCommand("RESET");

        // Initial display
        displayCards();

        // Set up action listeners
        setActionListeners(controller);
    }

    private JPanel createCardPanel(JLabel cardImage, JButton revealButton, JTextArea cardDisplay) {
        JPanel card_panel = new JPanel();
        card_panel.setLayout(new BoxLayout(card_panel, BoxLayout.Y_AXIS));
        cardImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        revealButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        card_panel.add(cardImage);
        card_panel.add(revealButton);
        card_panel.add(new JScrollPane(cardDisplay));
        return card_panel;
    }

    public void setActionListeners(TarotController controller) {
        reveal_past_button.addActionListener(controller);
        reveal_present_button.addActionListener(controller);
        reveal_future_button.addActionListener(controller);
        reset_button.addActionListener(controller);
    }

    public void revealCards(Tarot card, int column, boolean upsideDown) {
        JTextArea cardDisplay;
        JLabel cardImage;

        switch (column) {
            case 1:
                cardDisplay = cardDisplay1;
                cardImage = cardImage1;
                break;
            case 2:
                cardDisplay = cardDisplay2;
                cardImage = cardImage2;
                break;
            case 3:
                cardDisplay = cardDisplay3;
                cardImage = cardImage3;
                break;
            default:
                throw new IllegalArgumentException("Invalid column: " + column);
        }

        cardDisplay.setText("Card Name: " + card.getName() + "\n" +
                            "Arcana: " + card.getArcana() + "\n" +
                            "Suit: " + (card.getSuit() == null ? "N/A" : card.getSuit()) + "\n" +
                            "Upright Meaning: " + card.getUprightMeaning() + "\n" +
                            "Reversed Meaning: " + card.getReversedMeaning());

        String imagePath = "images/" + card.getName().toLowerCase().replace(" ", "_") + ".png";
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            if (upsideDown) {
                AffineTransform tx = AffineTransform.getRotateInstance(Math.PI, image.getWidth() / 2, image.getHeight() / 2);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                image = op.filter(image, null);
            }
            ImageIcon cardIcon = new ImageIcon(image.getScaledInstance(620 / 3, 1270 / 3, Image.SCALE_SMOOTH));
            cardImage.setIcon(cardIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayCards(){
        String backImagePath = "images/tarot_back.png";
        ImageIcon backImageIcon = new ImageIcon(backImagePath);
        Image backImage = backImageIcon.getImage();
        Image resizedBackImage = backImage.getScaledInstance(620 / 3, 1270 / 3, Image.SCALE_SMOOTH);
        backImageIcon = new ImageIcon(resizedBackImage);

        cardImage1.setIcon(backImageIcon);
        cardImage2.setIcon(backImageIcon);
        cardImage3.setIcon(backImageIcon);

        // Repeated text for unknown cards
        String unknownText = "Card Name: " + "Unknown" + "\n" +
                             "Arcana: " + "Unknown" + "\n" +
                             "Suit: " + "Unknown" + "\n" +
                             "Upright Meaning: " + "Unknown" + "\n" +
                             "Reversed Meaning: " + "Unknown";

        cardDisplay1.setText(unknownText);
        cardDisplay2.setText(unknownText);
        cardDisplay3.setText(unknownText);
    }

    public void resetCards(){
        displayCards();
    }
}
