import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame{
    private JComboBox<String> resolutionComboBox;
    private JCheckBox fullscreenCheckBox;
    private JButton startButton;

    public Launcher(){
        setTitle("Settings");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Resolution Setting
        JLabel resolutionLabel = new JLabel("Resolution:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(resolutionLabel, gbc);

        String[] resolutions = {"800x600", "1024x768", "1280x720", "1920x1080"};
        resolutionComboBox = new JComboBox<>(resolutions);
        gbc.gridx = 1;
        add(resolutionComboBox, gbc);

        // Fullscreen Setting
        JLabel fullscreenLabel = new JLabel("Fullscreen:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fullscreenLabel, gbc);

        fullscreenCheckBox = new JCheckBox();
        gbc.gridx = 1;
        add(fullscreenCheckBox, gbc);

        // Start Button
        startButton = new JButton("Start");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(startButton, gbc);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchMainApplication();
            }
        });
    }

    private void launchMainApplication() {

        String resolution = (String) resolutionComboBox.getSelectedItem();
        boolean fullscreen = fullscreenCheckBox.isSelected();

        String[] dims = resolution.split("x");
        int width = Integer.parseInt(dims[0]);
        int height = Integer.parseInt(dims[1]);

        dispose();

        TarotApp.launchTarotApp(width, height, fullscreen);
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> {
            
        //});
        Launcher launcher = new Launcher();
        launcher.setVisible(true);
    }
}
