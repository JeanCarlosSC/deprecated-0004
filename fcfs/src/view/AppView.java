package view;

import control.AppController;
import java.util.ArrayList;
import javax.swing.JTable;
import sRAD_java.gui.sComponent.SFrame;
import sRAD_java.gui.sComponent.SLabel;
import sRAD_java.gui.sComponent.STable;

public class AppView extends SFrame{
    private AppController appController;
    private Gantt gantt;

    // table headers
    private ArrayList<String> datosHeader;
    private ArrayList<String> bloqueadosHeader;
    private ArrayList<String> ejecucionHeader;
    private STable tDatos;
    private STable tBloqueados;
    private STable tEjecucion;
       
    public AppView(AppController controller) {
        super(1280, 720, "First Come First Served");
        this.appController = controller;
        
        gantt = new Gantt(this);
        add(gantt);
        
        datosHeader = new ArrayList<>();
        datosHeader.add("Proceso");
        datosHeader.add("T. de llegada");
        datosHeader.add("Ráfaga");
        datosHeader.add("T. de comienzo");
        datosHeader.add("T. de final");
        datosHeader.add("T. de retorno");
        datosHeader.add("T. de espera");

        bloqueadosHeader = new ArrayList<>();
        bloqueadosHeader.add("Proceso");
        bloqueadosHeader.add("T. de llegada");
        bloqueadosHeader.add("Ráfaga");
        bloqueadosHeader.add("T. de espera");

        ejecucionHeader = new ArrayList<>();
        ejecucionHeader.add("Proceso");
        ejecucionHeader.add("T. de llegada");
        ejecucionHeader.add("Ráfaga");
        ejecucionHeader.add("T. de espera");


        updateGUI();
    }

    public void updateGUI() {
        loadLabels();
        updateTables();
        repaint();
    }
    
    private void updateTables() {
        if(tDatos!=null) {
            remove(tDatos);
            remove(tEjecucion);
            remove(tBloqueados);
        }

        // datos
        ArrayList<ArrayList<String>> datos = new ArrayList<>();
        datos.add(datosHeader);

        for (ArrayList<String> dato: appController.getDatos()) {
            datos.add(dato);
        }

        tDatos = new STable(32, 62, 1194, 130, datos, 170, 28);
        add(tDatos);

        // lista de bloqueos
        ArrayList<ArrayList<String>> bloqueados = new ArrayList<>();
        bloqueados.add(bloqueadosHeader);

        tBloqueados = new STable(32, 232, 585, 110, bloqueados, 140, 32);
        add(tBloqueados);

        // lista de ejecucion
        ArrayList<ArrayList<String>> enEjecucion = new ArrayList<>();
        enEjecucion.add(ejecucionHeader);

        tEjecucion = new STable(647, 232, 585, 110, enEjecucion, 140, 32);
        add(tEjecucion);
    }
    
    private void loadLabels() {
        SLabel lDatos = new SLabel(40, 30, 200, 32, "Datos");
        add(lDatos);
        
        SLabel lListaDeEspera = new SLabel(40, 200, 200, 32, "Lista de espera");
        add(lListaDeEspera);
        
        SLabel lEjecucion = new SLabel(655, 200, 200, 32, "Ejecución");
        add(lEjecucion);
        
        SLabel lGantt = new SLabel(40, 360, 200, 32, "Gantt");
        add(lGantt);
        
        SLabel lAdd = new SLabel(40, 620, 200, 32, "Añadir");
        add(lAdd);
    }
    
}
