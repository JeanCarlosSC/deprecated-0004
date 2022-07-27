package view;

import control.AppController;

import java.util.ArrayList;

import model.Proceso;
import sRAD_java.gui.sComponent.SButton;
import sRAD_java.gui.sComponent.SFrame;
import sRAD_java.gui.sComponent.SLabel;
import sRAD_java.gui.sComponent.SScrollPane;

public class AppView extends SFrame{
    private AppController appController; // reference
    private Gantt gantt; // gantt diagram

    // table
    private TData tData;
    private TBlock tBlock;
    private TExecution tExecution;

    // label
    private SLabel lTime;
       
    public AppView(AppController controller) {
        super(1280, 720, "First Come First Served");

        this.appController = controller;

        tData = new TData();
        tBlock = new TBlock();
        tExecution = new TExecution();
        
        gantt = new Gantt(this, appController);

        SScrollPane sGantt = new SScrollPane(32, 400, 1200, 205, gantt);
        add(sGantt);

        loadGUI();
    }

    public void loadGUI() {
        loadLabels();
        updateTComponent(tData, appController.getDatos());
        loadButtons();
        repaint();
    }

    private void loadButtons() {
        SButton bStart = new SButton(1132, 630, 100, 32, "Iniciar");
        bStart.addActionListener(e -> {
            new Thread(() -> appController.restart()).start();
        });
        add(bStart);

        SButton bDetener = new SButton(1000, 630, 100, 32, "Detener");
        bDetener.addActionListener(e -> {
            new Thread(() -> appController.stop()).start();
        });
        add(bDetener);
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

        updateTComponent(tBlock, toMatrix(appController.getBloqueados()));
        updateTComponent(tExecution, toMatrix(appController.getEnEjecucion()));
        gantt.updateUI(appController);
    }

    private ArrayList<ArrayList<String>> toMatrix(ArrayList<Proceso> array) {
        ArrayList<ArrayList<String>> matrix = new ArrayList<>();
        for (Proceso process: array) {
            matrix.add(process.toArray());
        }
        return matrix;
    }

    private ArrayList<ArrayList<String>> toMatrix(Proceso process) {
        ArrayList<ArrayList<String>> matrix = new ArrayList<>();
        if(process != null) {
            matrix.add(process.toArray());
        }
        return matrix;
    }

}
