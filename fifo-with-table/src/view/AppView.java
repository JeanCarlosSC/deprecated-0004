package view;

import sRAD_java.gui.sComponent.STable;

import javax.swing.*;
import java.util.ArrayList;

public class AppView extends JFrame {
    public AppView() {
        ArrayList<ArrayList<String>> datos = new ArrayList<>();

        ArrayList<String> header = new ArrayList<>();
        header.add("Proceso");
        header.add("T. de llegada");
        header.add("Ráfaga");
        header.add("T. de comienzo");
        header.add("T. de final");
        header.add("T. de retorno");
        header.add("T. de espera");

       ArrayList<String> prueba2 = new ArrayList<>();
        prueba2.add("Proceso");
        prueba2.add("T. de llegada");
        prueba2.add("Ráfaga");
        prueba2.add("T. de comienzo");
        prueba2.add("T. de final");
        prueba2.add("T. de retorno");
        prueba2.add("T. de espera");

        datos.add(header);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);
        datos.add(prueba2);

        STable table = new STable(32, 32, 722, 300, datos);
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
