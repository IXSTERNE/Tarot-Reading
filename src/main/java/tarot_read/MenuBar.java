package tarot_read;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar {

    public static JMenuBar creatMenuBar(JFrame parentFrame){

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem helpMenuItem = new JMenuItem("Emporium");
        JMenuItem howMenuItem = new JMenuItem("How to read");

        fileMenu.add(exitMenuItem);
        helpMenu.add(helpMenuItem);
        helpMenu.add(howMenuItem);

        // Add action listener for exit menu item
        exitMenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        howMenuItem.addActionListener(e -> InstructionWindow.show(parentFrame));

        helpMenuItem.addActionListener(e -> EmporiumWindow.show(parentFrame));

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }
}
