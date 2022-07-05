package view;

import control.OficinaBancaria;
import lib.sRAD_java.gui.component.Resource;
import lib.sRAD_java.gui.sComponent.SButton;
import lib.sRAD_java.gui.sComponent.SLabel;
import lib.sRAD_java.gui.sComponent.STextArea;
import model.ColaBanco;

import javax.swing.*;
import java.awt.*;

import static lib.sRAD_java.gui.component.Resource.fontTitle;

public class AppView extends JFrame {
    private OficinaBancaria banco;
    private PCola pCola;
    public AppView(OficinaBancaria oficinaBancaria) {
        banco = oficinaBancaria;
        pCola = new PCola();

        addJLabels();
        addJButtons();

        add(pCola);

        loadBackground();
        loadProperties();
    }
    private void loadBackground() {
        JLabel background = new SLabel(0, 0, new ImageIcon("public/background.jpg"));
        getContentPane().add(background);
    }
    private void addJLabels() {
        JLabel lCola = new JLabel("Cola");
        lCola.setBounds(62, 32, 200, 54);
        lCola.setFont(fontTitle);
        add(lCola);

        JLabel lCajero = new JLabel("Cajero");
        lCajero.setBounds(532, 32, 200, 54);
        lCajero.setFont(fontTitle);
        add(lCajero);
    }
    private void addJButtons() {
        JButton btAtender = new SButton(532,96, 100, 32, "Atender");
        btAtender.addActionListener(e -> banco.atender());
        add(btAtender);

        JButton btInsertar = new SButton(310, 610, 150, 32, "Insertar cliente");
        btInsertar.addActionListener(e -> banco.insertarCliente());
        add(btInsertar);
    }
    private void loadProperties() {
        setTitle("Simulador de cola bancaria");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(720, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void updateContent(ColaBanco cola) {
        pCola.updateContent(cola);
    }
}

class PCola extends JScrollPane {
    private JPanel content;
    private JLabel lID;
    private JLabel lTransacciones;
    public PCola() {
        initContent();
        updateContent();
        setBounds(62, 96, 400, 500);
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
        content.removeAll();
        content.add(lID);
        content.add(lTransacciones);
    }
    public void updateContent(ColaBanco cola) {
        updateContent();
        JTextArea text = new STextArea(32, 50, 400, 440, cola.getContent());
        content.add(text);
        repaint();
    }

}