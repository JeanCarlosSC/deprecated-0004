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
    private TBlock tBlock;
    private STable tEjecucion;
    private SLabel lTime;
       
    public AppView(AppController controller) {
        super(1280, 720, "First Come First Served");

        this.appController = controller;

        tData = new TData();
        tBlock = new TBlock();
        
        gantt = new Gantt(this);
        add(gantt);

        ejecucionHeader = new ArrayList<>();
        ejecucionHeader.add("Proceso");
        ejecucionHeader.add("T. de llegada");
        ejecucionHeader.add("Ráfaga");
        ejecucionHeader.add("T. de espera");

        updateGUI();
    }

    public void updateGUI() {
        loadLabels();
        updateTComponent(tData, appController.getDatos());
        repaint();
    }
    
    private void updateTComponent(TComponent tComponent, ArrayList<ArrayList<String>> newData) {
        if(tComponent.getComponent() != null) {
            remove(tComponent.getComponent());
        }

        tComponent.updateComponent(newData);
        add(tComponent.getComponent());
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
        
        lTime = new SLabel(40, 620, 200, 32, "Tiempo");
        add(lTime);
    }

    public void step() {
        lTime.setText("Tiempo: "+appController.getTiempo()+" s.");

        updateTComponent(tBlock, appController.getBloqueados());
        //updateTComponent(tExecution, appController.getEnEjecucion());

        if(tEjecucion!=null) {
            remove(tEjecucion);
        }

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
