import javax.swing.*;
import java.awt.*;

public class SingleReading extends JPanel{

    public SingleReading(){
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Celtic Cross Tarot Reading", SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);
        
        // Add more components as needed for your Celtic Reading layout
        JPanel readingPanel = new JPanel();
        readingPanel.setLayout(new GridLayout(3, 3));
        // Add more specific components for the Celtic Cross spread
        
        add(readingPanel, BorderLayout.CENTER);
    }
    
}
