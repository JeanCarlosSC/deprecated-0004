package view;

import lib.Resource;

import javax.swing.*;
import java.awt.*;

public class AppView extends JFrame {
    public AppView() {
        addJLabels();
        addJButtons();

        add(new PCola());

        loadProperties();
    }
    private void addJLabels() {
        JLabel lCola = new JLabel("Cola");
        lCola.setBounds(32, 32, 200, 54);
        lCola.setFont(Resource.fontTitle);
        add(lCola);

        JLabel lCajero = new JLabel("Cajero");
        lCajero.setBounds(532, 32, 200, 54);
        lCajero.setFont(Resource.fontTitle);
        add(lCajero);
    }
    private void addJButtons() {
        JButton btAtender = new JButton("Atender");
        btAtender.setBounds(532,96, 100, 32);
        add(btAtender);
    }
    private void loadProperties() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class PCola extends JScrollPane {
    private JPanel content;
    private JLabel lID;
    private JLabel lTransacciones;
    public PCola() {
        initContent();
        updateContent();
        setBounds(32, 96, 400, 500);
        setBorder(Resource.blackBorderTransparent);
    }
    private void initContent() {
        content = new JPanel();
        content.setLayout(null);
        content.setBounds(2, 2, 396, 496);
        content.setPreferredSize(new Dimension(396, 496));

        lID = new JLabel("ID");
        lID.setBounds(74, 20, 100, 32);
        lID.setFont(Resource.fontTitle2);

        lTransacciones = new JLabel("N° Transacciones");
        lTransacciones.setBounds(184, 20, 200, 32);
        lTransacciones.setFont(Resource.fontTitle2);

        setViewportView(content);
    }
    public void updateContent() {
        content.add(lID);
        content.add(lTransacciones);
    }

}