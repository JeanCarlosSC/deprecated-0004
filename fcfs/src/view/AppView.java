package view;

import control.AppController;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AppView extends JFrame{
    private AppController controller;
    private Gantt gantt;
    
    public AppView(AppController controller) {
        this.controller = controller;
        
        gantt = new Gantt(this);
        add(gantt);
        
        loadLabels();
        loadTables();
        loadProperties();
    }
    
    private void loadTables() {
        String[][] datosValues = {
            {"0", "0", "0"},
            {"0", "0", "0"}
        };
        String[] datosNames = { "Proceso", "Tiempo de llegada", "Ráfaga"};
        JTable tDatos = new JTable(datosValues, datosNames);
        
        JScrollPane spDatos = new JScrollPane(tDatos);
        spDatos.setBounds(32, 62, 1200, 110);
        add(spDatos);
        
        JTable tListaDeEspera = new JTable();
        tListaDeEspera.setBounds(32, 232, 585, 110);
        add(tListaDeEspera);
        
        JTable tListaDeEjecucion = new JTable();
        tListaDeEjecucion.setBounds(647, 232, 585, 110);
        add(tListaDeEjecucion);
    }
    
    private void loadLabels() {
        JLabel lDatos = new JLabel("Datos");
        lDatos.setBounds(40, 30, 200, 32);
        add(lDatos);
        
        JLabel lListaDeEspera = new JLabel("Lista de espera");
        lListaDeEspera.setBounds(40, 200, 200, 32);
        add(lListaDeEspera);
        
        JLabel lEjecucion = new JLabel("Ejecución");
        lEjecucion.setBounds(655, 200, 200, 32);
        add(lEjecucion);
        
        JLabel lGantt = new JLabel("Gantt");
        lGantt.setBounds(40, 360, 200, 32);
        add(lGantt);
        
        JLabel lAdd = new JLabel("Añadir");
        lAdd.setBounds(40, 620, 200, 32);
        add(lAdd);
    }
    
    private void loadProperties() {
        setTitle("First Come First Served");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(this);
        setVisible(true);
    }
}
