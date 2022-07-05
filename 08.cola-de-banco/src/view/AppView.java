package view;

import javax.swing.*;

public class AppView extends JFrame {
    public AppView() {
        loadProperties();
    }
    private void loadProperties() {
        setLayout(null);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
