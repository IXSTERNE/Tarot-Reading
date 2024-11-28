import javax.swing.*;

public class InstructionWindow {
    
    public static void show(JFrame parentFrame){

        JDialog dialog = new JDialog(parentFrame, "Instructions", true);
        dialog.setSize(600, 300);
        dialog.setLocationRelativeTo(parentFrame);

        JLabel instructionsLabel = new JLabel("<html><p style='text-align:center;'>"
            + "Welcome to the application!<br>"
            + "Use the menu options to navigate.<br>"
            + "For help, contact support."
            + "</p></html>");

        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        dialog.add(instructionsLabel);
        dialog.setVisible(true);
    }
}
