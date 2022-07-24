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
    private TData tData;
    private TBlock tBlock;
    private TExecution tExecution;
    private SLabel lTime;
       
    public AppView(AppController controller) {
        super(1280, 720, "First Come First Served");

        this.appController = controller;

        tData = new TData();
        tBlock = new TBlock();
        tExecution = new TExecution();
        
        gantt = new Gantt(this);
        add(gantt);

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
        updateTComponent(tExecution, toMatrix(appController.getEnEjecucion().toArray()));
    }

    private ArrayList<ArrayList<String>> toMatrix(ArrayList<String> array) {
        ArrayList<ArrayList<String>> matrix = new ArrayList<>();
        matrix.add(array);
        return matrix;
    }
}
