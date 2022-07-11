package view;

import lib.sRAD_java.gui.sComponent.STable;

import javax.swing.*;
import java.util.ArrayList;

public class AppView extends JFrame {
    public AppView() {
        ArrayList<ArrayList<String>> datos = new ArrayList<>();

        ArrayList<String> header = new ArrayList<>();
        header.add("Proceso");
        header.add("T. de llegada");
        header.add("T. de ráfaga");
        header.add("T. de comienzo");
        header.add("T. de final");
        header.add("T. de retorno");
        header.add("T. de espera");

       ArrayList<String> prueba2 = new ArrayList<>();
        prueba2.add("prueba2 1");
        prueba2.add("prueba2 2");

        datos.add(header);

        STable table = new STable(32, 32, 800, 600, datos);
        add(table);
        loadProperties();
    }

    private void loadProperties() {
        setTitle("FCFS");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        setLayout(null);
        setVisible(true);
    }

}
