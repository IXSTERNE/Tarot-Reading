import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


public class TarotView extends JFrame{
    private JFrame frame;
    private JTextArea cardDisplay1, cardDisplay2, cardDisplay3;
    private JLabel cardImage1, cardImage2, cardImage3;
    private JButton reveal_past_button, reveal_present_button, reveal_future_button, reset_button;
    private TarotController controller;
    private JTabbedPane tabPanel;

    public TarotView(TarotController controller, int width, int height, boolean fullscreen) {
        this.controller = controller;

        frame = new JFrame("Tarot Reader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(width, height);
        setExtendedState(fullscreen ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL);

        if (fullscreen) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }

        frame.setJMenuBar(MenuBar.creatMenuBar(frame));

        tabPanel = new JTabbedPane();

        JPanel page1 = new JPanel();
        CelticReading page2 = new CelticReading(controller);
        SingleReading page3 = new SingleReading();

        page1.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel(new GridBagLayout());

        //panel1.setBackground(Color.RED);
        panel2.setBackground(Color.BLUE);
        panel3.setBackground(Color.YELLOW);
        panel4.setBackground(Color.GREEN);

        page1.add(panel1, BorderLayout.NORTH);
        //page1.add(panel2, BorderLayout.WEST);
        //page1.add(panel3, BorderLayout.EAST);
        //page1.add(panel4, BorderLayout.SOUTH);
        page1.add(panel5, BorderLayout. CENTER);

        tabPanel.addTab("Past, Present and Future", page1);
        tabPanel.addTab("Celtic Reading", page2);
        tabPanel.addTab("Single Card Reading", page3);

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

        JPanel card_panel1 = new JPanel();
        card_panel1.setLayout(new BoxLayout(card_panel1, BoxLayout.Y_AXIS));
        cardImage1.setAlignmentX(Component.CENTER_ALIGNMENT);
        reveal_past_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        card_panel1.add(cardImage1);
        card_panel1.add(reveal_past_button);
        card_panel1.add(new JScrollPane(cardDisplay1));

        JPanel card_panel2 = new JPanel();
        card_panel2.setLayout(new BoxLayout(card_panel2, BoxLayout.Y_AXIS));
        cardImage2.setAlignmentX(Component.CENTER_ALIGNMENT);
        reveal_present_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        card_panel2.add(cardImage2);
        card_panel2.add(reveal_present_button);
        card_panel2.add(new JScrollPane(cardDisplay2));

        JPanel card_panel3 = new JPanel();
        card_panel3.setLayout(new BoxLayout(card_panel3, BoxLayout.Y_AXIS));
        cardImage3.setAlignmentX(Component.CENTER_ALIGNMENT);
        reveal_future_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        card_panel3.add(cardImage3);
        card_panel3.add(reveal_future_button);
        card_panel3.add(new JScrollPane(cardDisplay3));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel5.add(card_panel1, gbc);

        gbc.gridx = 1;
        panel5.add(card_panel2, gbc);

        gbc.gridx = 2;
        panel5.add(card_panel3, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;

        // Reset button
        panel1.add(reset_button);

        displayCards();

        reveal_past_button.setActionCommand("REVEAL_PAST");
        reveal_present_button.setActionCommand("REVEAL_PRESENT");
        reveal_future_button.setActionCommand("REVEAL_FUTURE");
        reset_button.setActionCommand("RESET");

        frame.add(tabPanel);
        frame.setVisible(true);
    }

    public void setController(TarotController controller){
        this.controller = controller;
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

        // Here pretty much turns the cards upside down
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

        cardDisplay1.setText("Card Name: " + "Unknown" + "\n" +
                            "Arcana: " + "Unknown" + "\n" +
                            "Suit: " + "Unknown" + "\n" +
                            "Upright Meaning: " + "Unknown" + "\n" +
                            "Reversed Meaning: " + "Unknown");

        cardDisplay2.setText("Card Name: " + "Unknown" + "\n" +
                            "Arcana: " + "Unknown" + "\n" +
                            "Suit: " + "Unknown" + "\n" +
                            "Upright Meaning: " + "Unknown" + "\n" +
                            "Reversed Meaning: " + "Unknown");

        cardDisplay3.setText("Card Name: " + "Unknown" + "\n" +
                            "Arcana: " + "Unknown" + "\n" +
                            "Suit: " + "Unknown" + "\n" +
                            "Upright Meaning: " + "Unknown" + "\n" +
                            "Reversed Meaning: " + "Unknown");
    }

    public void resetCards(){
        displayCards();
    }
}
