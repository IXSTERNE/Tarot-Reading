import javax.swing.*;
import java.awt.*;

public class TarotView extends JFrame{
    private JFrame frame;
    private JTextArea cardDisplay1, cardDisplay2, cardDisplay3;
    private JLabel cardImage1, cardImage2, cardImage3;
    private JButton button1, button2, button3;
    private TarotController controller;

    public TarotView() {
        
        frame = new JFrame("Tarot Reader");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        button1 = new JButton("Reveal Past");
        button2 = new JButton("Reveal Present");
        button3 = new JButton("Reveal Future");

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(cardImage1, BorderLayout.NORTH);
        panel1.add(new JScrollPane(cardDisplay1), BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(cardImage2, BorderLayout.NORTH);
        panel2.add(new JScrollPane(cardDisplay2), BorderLayout.CENTER);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.add(cardImage3, BorderLayout.NORTH);
        panel3.add(new JScrollPane(cardDisplay3), BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        frame.add(panel1, gbc);

        gbc.gridx = 1;
        frame.add(panel2, gbc);

        gbc.gridx = 2;
        frame.add(panel3, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weighty = 0.1; // Make buttons take less vertical space
        frame.add(button1, gbc);

        gbc.gridx = 1;
        frame.add(button2, gbc);

        gbc.gridx = 2;
        frame.add(button3, gbc);

        displayCards();

        button1.addActionListener(controller);
        button2.addActionListener(controller);
        button3.addActionListener(controller);

        frame.setVisible(true);
    }

    public void revealCards(Tarot card, int column) {

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

        // Here I have to on default put the back of the card image
        
        String imagePath = "images/" + card.getName().toLowerCase().replace(" ", "_") + ".png";
        ImageIcon cardIcon = new ImageIcon(imagePath);
        Image image = cardIcon.getImage();
        Image resizedImage = image.getScaledInstance(620 / 3, 1270 / 3, Image.SCALE_SMOOTH);
        cardIcon = new ImageIcon(resizedImage);
        cardImage.setIcon(cardIcon);
    }

    // There has to be like a default function where it initializes a the placeholders
    private void displayCards(){

        String backImagePath = "images/tarot_back.png";
        ImageIcon backImageIcon = new ImageIcon(backImagePath);
        Image backImage = backImageIcon.getImage();
        Image resizedBackImage = backImage.getScaledInstance(620 / 3, 1270 / 3, Image.SCALE_SMOOTH);
        backImageIcon = new ImageIcon(resizedBackImage);

        // This here is repetitive
        cardImage1.setIcon(backImageIcon);
        cardImage2.setIcon(backImageIcon);
        cardImage3.setIcon(backImageIcon);
    }
}
