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
        super(1280, 720, "Proyecto final");

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
        loadCombobox();
        repaint();
    }

    private void loadCombobox() {
        String[] options = new String[3];
        options[0] = "FCFS";
        options[1] = "SJF";
        options[2] = "RR";
        SComboBox comboBox = new SComboBox(SComboBox.DECORADO, 736, 670, 100, 32, options);
        comboBox.addActionListener(e -> {
            switch (comboBox.getSelectedIndex()) {
                case 1:
                    appController.setMethod(1);
                    break;
                case 2:
                    appController.setMethod(2);
                    break;
                default:
                    appController.setMethod(0);
                    break;
            }
        });
        add(comboBox);
    }

    private void loadButtons() {
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
                appController.addProcess(new Proceso(name, tiempoDeLlegada, rafaga));
                appController.updateData(0);
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

}
