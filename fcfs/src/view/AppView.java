package view;

import control.AppController;

import java.util.ArrayList;

import model.Proceso;
import sRAD_java.gui.sComponent.SFrame;
import sRAD_java.gui.sComponent.SLabel;
import sRAD_java.gui.sComponent.STable;

public class AppView extends SFrame{
    private AppController appController;
    private Gantt gantt;

    // table headers
    private ArrayList<String> bloqueadosHeader;
    private ArrayList<String> ejecucionHeader;
    private TData tData;
    private STable tBloqueados;
    private STable tEjecucion;
    private SLabel lAdd;
       
    public AppView(AppController controller) {
        super(1280, 720, "First Come First Served");

        this.appController = controller;

        tData = new TData();
        
        gantt = new Gantt(this);
        add(gantt);

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
        if(tData.getComponent() != null) {
            remove(tData.getComponent());
        }

        tData.updateComponent(appController.getDatos());
        add(tData.getComponent());
    }
    
    private void loadLabels() {
        SLabel lDatos = new SLabel(40, 30, 200, 32, "Datos");
        add(lDatos);
        
        SLabel lListaDeEspera = new SLabel(40, 200, 200, 32, "Lista de bloqueados");
        add(lListaDeEspera);
        
        SLabel lEjecucion = new SLabel(655, 200, 200, 32, "Ejecución");
        add(lEjecucion);
        
        SLabel lGantt = new SLabel(40, 360, 200, 32, "Gantt");
        add(lGantt);
        
        lAdd = new SLabel(40, 620, 200, 32, "Tiempo");
        add(lAdd);
    }

    public void step() {
        lAdd.setText("Tiempo: "+appController.getTiempo()+" s.");
        if(tBloqueados!=null) {
            remove(tBloqueados);
            remove(tEjecucion);
        }

        // lista de bloqueos
        ArrayList<ArrayList<String>> bloqueados = new ArrayList<>();
        bloqueados.add(bloqueadosHeader);

        for (ArrayList<String> dato: appController.getBloqueados()) {
            bloqueados.add(dato);
        }

        tBloqueados = new STable(32, 232, 570, 110, bloqueados, 140, 18);
        add(tBloqueados);

        // lista de ejecucion
        ArrayList<ArrayList<String>> datosEnEjecucion = new ArrayList<>();
        datosEnEjecucion.add(ejecucionHeader);

        Proceso enEjecucion = appController.getEnEjecucion();
        if(enEjecucion != null) {
            ArrayList<String> dato = new ArrayList<>();
            dato.add(enEjecucion.getNombre());
            dato.add(enEjecucion.getTiempoDeLLegada()+"");
            dato.add(enEjecucion.getRafaga()+"");
            dato.add(enEjecucion.getTiempoDeComienzo()+"");
            dato.add(enEjecucion.getTiempoFinal()+"");
            dato.add(enEjecucion.getTiempoDeRetorno()+"");
            dato.add(enEjecucion.getTiempoDeEspera()+"");
            datosEnEjecucion.add(dato);
        }

        tEjecucion = new STable(647, 232, 570, 110, datosEnEjecucion, 140, 18);
        add(tEjecucion);
    }
}
