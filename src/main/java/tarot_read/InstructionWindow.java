package tarot_read;

import javax.swing.*;

public class InstructionWindow {
    
    public static void show(JFrame parentFrame) {

        // Dialog window (Diaglogue lol)
        JDialog dialog = new JDialog(parentFrame, "Instructions", true);
        dialog.setSize(600, 400); // Adjusted height to fit text better
        dialog.setLocationRelativeTo(parentFrame);

        // I can do html here???
        JLabel instructionsLabel = new JLabel("<html><div style='text-align:left;'>"
            + "<h2 style='text-align:center;'>Tarot Reading Instructions</h2>"
            + "<h3>3-Card Tarot Reading</h3>"
            + "<ol>"
            + "<li><b>Shuffle the Deck:</b> Focus on your question or intention while shuffling.</li>"
            + "<li><b>Draw Three Cards:</b> Lay them out in a row from left to right.</li>"
            + "<li><b>Interpret:</b> Use these meanings: "
            + "Past (Card 1), Present (Card 2), Future (Card 3).</li>"
            + "</ol>"
            + "<h3>5-Card Celtic Cross Reading</h3>"
            + "<ol>"
            + "<li><b>Shuffle the Deck:</b> Focus on your question or intention.</li>"
            + "<li><b>Draw Five Cards:</b> Lay them in a cross pattern: "
            + "Center (Card 1), Cross (Card 2), Above (Card 3), Below (Card 4), Right (Card 5).</li>"
            + "<li><b>Interpret:</b> Assign meanings: "
            + "Present (Card 1), Challenge (Card 2), Goal (Card 3), Foundation (Card 4), Future (Card 5).</li>"
            + "</ol>"
            + "<p style='text-align:center;'>Good luck with your readings!</p>"
            + "</div></html>");

        // Align content and add to dialog
        instructionsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dialog.add(instructionsLabel);

        // Make the dialog visible
        dialog.setVisible(true);
    }
}
