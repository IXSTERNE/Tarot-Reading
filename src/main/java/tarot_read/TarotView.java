package tarot_read;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


public class TarotView extends JFrame{

    private JTextArea[] cardDescriptions, cardDescriptions2;
    private JLabel[] cardImages, cardImages2;

    private JButton reveal_button, reveal_celtic_button;
    private JTabbedPane tabPanel;

    public TarotView(TarotController controller, int width, int height, boolean fullscreen) {

        setTitle("Tarot Reader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);

        if (fullscreen) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
        }

        setJMenuBar(MenuBar.creatMenuBar(this));
        tabPanel = new JTabbedPane();

        JPanel page1 = new JPanel(new BorderLayout());
        JPanel page2 = new JPanel(new BorderLayout());

        JPanel spreadPanel = new JPanel(new GridBagLayout());
        JPanel spreadPanel2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc2.insets = new Insets(5, 5, 5, 5);

        
        cardImages = new JLabel[3];
        cardDescriptions = new JTextArea[3];

        cardImages2 = new JLabel[5];
        cardDescriptions2 = new JTextArea[5];

        // Initialize card images and descriptions
        for (int i = 0; i < 3; i++) {
            cardImages[i] = new JLabel();
            cardImages[i].setHorizontalAlignment(JLabel.CENTER);
            cardDescriptions[i] = new JTextArea(3, 20);
            cardDescriptions[i].setEditable(false);
        }

        for (int i = 0; i < 5; i++) {
            cardImages2[i] = new JLabel();
            cardImages2[i].setHorizontalAlignment(JLabel.CENTER);
            cardDescriptions2[i] = new JTextArea(5, 20);
            cardDescriptions2[i].setEditable(false);
        }

        reveal_button = new JButton("Reveal"); 
        reveal_button.setActionCommand("REVEAL_THREE");

        reveal_celtic_button = new JButton("Reveal");
        reveal_celtic_button.setActionCommand("REVEAL_FIVE");


        // Past, present, future reading layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        spreadPanel.add(cardImages[0], gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        spreadPanel.add(cardImages[1], gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        spreadPanel.add(cardImages[2], gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        spreadPanel.add(reveal_button, gbc);



        // Celtic reading layout
        gbc2.gridx = 1;
        gbc2.gridy = 1;
        spreadPanel2.add(cardImages2[0], gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        spreadPanel2.add(cardImages2[1], gbc2);

        gbc2.gridx = 2;
        gbc2.gridy = 1;
        spreadPanel2.add(cardImages2[2], gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 0;
        spreadPanel2.add(cardImages2[3], gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 2;
        spreadPanel2.add(cardImages2[4], gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 3;
        spreadPanel2.add(reveal_celtic_button, gbc2);

        
        JPanel descriptionPanel = new JPanel(new GridLayout(3, 1));
        for (int i = 0; i < 3; i++) {
            descriptionPanel.add(new JScrollPane(cardDescriptions[i]));
        }

        JPanel descriptionPanel2 = new JPanel(new GridLayout(5, 1));
        for (int i = 0; i < 5; i++) {
            descriptionPanel2.add(new JScrollPane(cardDescriptions2[i]));
        }

        page1.add(spreadPanel, BorderLayout.CENTER);
        page1.add(descriptionPanel, BorderLayout.EAST);

        page2.add(spreadPanel2, BorderLayout.CENTER);
        page2.add(descriptionPanel2, BorderLayout.EAST);

        tabPanel.addTab("Past, Present and Future", page1);
        tabPanel.addTab("Celtic Cross", page2);

        add(tabPanel);
        setVisible(true);

        displayCards();
    }

    public void setController(TarotController controller){
        //this.controller = controller;
        reveal_button.addActionListener(controller);
        reveal_celtic_button.addActionListener(controller);
    }

    public static Image getImage(final String pathAndFileName){
        final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    private void updateCardDisplay(JLabel cardImageLabel, JTextArea cardDescription, CardWithInversion cardWithInversion, int scaleFactor) {
        Tarot card = cardWithInversion.getCard();
        boolean isInverted = cardWithInversion.isInverted();
    
        cardDescription.setText("Card Name: " + card.getName() + "\n" +
                "Arcana: " + card.getArcana() + "\n" +
                "Suit: " + (card.getSuit() == null ? "N/A" : card.getSuit()) + "\n" +
                "Upright Meaning: " + card.getUprightMeaning() + "\n" +
                "Reversed Meaning: " + card.getReversedMeaning());
    
        String imagePath = "src/main/resources/images/" + card.getName().toLowerCase().replace(" ", "_") + ".png";
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            if (image == null) {
                throw new IOException("Image file not found: " + imagePath);
            }
    
            if (isInverted) {
                AffineTransform tx = AffineTransform.getRotateInstance(Math.PI, image.getWidth() / 2, image.getHeight() / 2);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                image = op.filter(image, null);
            }
    
            ImageIcon cardIcon = new ImageIcon(image.getScaledInstance(620 / scaleFactor, 1270 / scaleFactor, Image.SCALE_SMOOTH));
            cardImageLabel.setIcon(cardIcon);
        } catch (IOException e) {
            cardImageLabel.setText("Image not found");
            e.printStackTrace();
        }
    }

    private void displayCards() {
        initializeCardBacks(3, cardImages, cardDescriptions);
        initializeCardBacks(5, cardImages2, cardDescriptions2);
    }
    
    public void revealCards(Object[] cards) {
        for (int i = 0; i < cards.length; i++) {
            updateCardDisplay(cardImages[i], cardDescriptions[i], (CardWithInversion) cards[i], 3);
        }
    }
    
    public void revealFiveCards(Object[] cards) {
        for (int i = 0; i < cards.length; i++) {
            updateCardDisplay(cardImages2[i], cardDescriptions2[i], (CardWithInversion) cards[i], 6);
        }
    }
    

    // There has to be like a default function where it initializes the placeholders / cardbacks
    private void initializeCardBacks(int cardCount, JLabel[] cardImages, JTextArea[] cardDescriptions) {
        String backImagePath = "src/main/resources/images/tarot_back.png";
        ImageIcon backImageIcon = new ImageIcon(backImagePath);
        Image backImage = backImageIcon.getImage();
        int scaleFactor = cardCount == 3 ? 3 : 6; // Adjust scaling based on card count
        Image resizedBackImage = backImage.getScaledInstance(620 / scaleFactor, 1270 / scaleFactor, Image.SCALE_SMOOTH);
        backImageIcon = new ImageIcon(resizedBackImage);
    
        for (int i = 0; i < cardCount; i++) {
            cardImages[i].setIcon(backImageIcon);
            cardDescriptions[i].setText("Card " + (i + 1) + ": Unknown");
        }
    }
}
