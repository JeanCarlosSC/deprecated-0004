package view;

import control.AppController;
import sRAD_java.gui.sComponent.STable;

import javax.swing.*;
import java.util.ArrayList;

public abstract class AppView extends JFrame {
    private AppController appController;
    private STable table;
    public AppView(AppController appController) {
        this.appController = appController;

        updateGUI();
        loadProperties();
    }

    private void handleAdd() {
        insertarDato("A", "1", "1");
        updateGUI();
    }

    public abstract void insertarDato(String proceso, String tDeLlegada, String rafaga);
    private void updateGUI() {
        ArrayList<String> header = new ArrayList<>();
        header.add("Proceso");
        header.add("T. de llegada");
        header.add("Ráfaga");
        header.add("T. de comienzo");
        header.add("T. de final");
        header.add("T. de retorno");
        header.add("T. de espera");

        ArrayList<ArrayList<String>> datos = new ArrayList<>();
        datos.add(header);

        for (ArrayList<String> dato: appController.getDatos()) {
            datos.add(dato);
        }

        table = new STable(32, 32, 722, 300, datos);
        add(table);
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
