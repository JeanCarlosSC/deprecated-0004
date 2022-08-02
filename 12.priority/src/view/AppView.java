package view;

import control.AppController;

import java.util.ArrayList;

import model.Proceso;
import sRAD_java.gui.sComponent.*;

import javax.swing.*;

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
        super(1280, 720, "Priority");

        this.appController = controller;

        tData = new TData();
        tBlock = new TBlock();
        tExecution = new TExecution();
        
        gantt = new Gantt(this, appController);

        SScrollPane sGantt = new SScrollPane(32, 400, 1200, 245, gantt);
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
        SButton bBloquear = new SButton(730, 670, 100, 32, "Bloquear");
        bBloquear.addActionListener(e -> {
            new Thread(() -> appController.bloquear()).start();
        });
        add(bBloquear);

        SButton bStart = new SButton(1132, 670, 100, 32, "Iniciar");
        bStart.addActionListener(e -> {
            new Thread(() -> appController.restart()).start();
        });
        add(bStart);

        SButton bDetener = new SButton(1000, 670, 100, 32, "Detener");
        bDetener.addActionListener(e -> {
            new Thread(() -> appController.stop()).start();
        });
        add(bDetener);

        SButton bAdd = new SButton(868, 670, 100, 32, "Añadir");
        bAdd.addActionListener(e -> {
            new Thread(() -> {
                String name = JOptionPane.showInputDialog("Ingrese el nombre del proceso");
                int tiempoDeLlegada = Integer.parseInt(JOptionPane.showInputDialog("Ingrese tiempo de llegada"));
                int rafaga = Integer.parseInt(JOptionPane.showInputDialog("Ingrese duración de la rafaga"));
                int prioridad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la prioridad del prooeso"));
                appController.addProcess(new Proceso(name, tiempoDeLlegada, rafaga, prioridad));
                appController.updateData();
            }).start();
        });
        add(bAdd);
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

        if(lTime != null) {
            remove(lTime);
        }
        lTime = new SLabel(40, 660, 200, 32, "Tiempo");
        add(lTime);
    }

    public void step() {
        lTime.setText("Tiempo: "+appController.getTiempo()+" s.");
        repaint();

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

    public TData getTData() {
         return tData;
    }

}
