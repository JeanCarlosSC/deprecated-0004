package control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.Proceso;
import sRAD_java.gui.component.Theme;
import view.AppView;

public class AppController {
    private AppView view;
    private ArrayList<ArrayList<String>> datos;
    private ArrayList<Proceso> processes;
    private Proceso enEjecucion;
    private ArrayList<Proceso> blockList;
    private int currentTime;
    private boolean isWorking;
    
    public AppController() {
        enEjecucion = null;
        processes = new ArrayList<>();
        datos = new ArrayList<>();
        blockList = new ArrayList<>();
        isWorking = true;
        showGUI();

        addProcess(new Proceso("A", 0, 8));
        addProcess(new Proceso("B", 1, 4));
        addProcess(new Proceso("C", 2, 9));
        addProcess(new Proceso("D", 3, 5));
        addProcess(new Proceso("E", 4, 2));

        run();
    }

    private void run() {
        updateData();
        startSimulation();
    }

    private void startSimulation() {
        currentTime = 0;
        while(isWorking) {
            addToBlockList();
            deleteFromExecutionList();
            addToExecutionList();

            // agregar a ejecucion
            view.step();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentTime++;
        }
    }

    private void deleteFromExecutionList() {
        if(enEjecucion != null && enEjecucion.getTiempoFinal() == currentTime) {
            enEjecucion = null;
        }
    }
    private void addToExecutionList() {
        if(enEjecucion == null && !blockList.isEmpty()) {
            enEjecucion = blockList.get(0);
            blockList.remove(0);
        }
    }
    private void addToBlockList() {
        for (Proceso process: processes) {
            if(process.getTiempoDeLLegada() == currentTime) {
                blockList.add(process);
            }
        }
    }

    private void updateData() {
        int tiempoDeComienzoSiguiente = 0;

        Collections.sort(processes, Comparator.comparing(Proceso::getTiempoDeLLegada));

        for(int i = 0; i< processes.size(); i++) {
            processes.get(i).setTiempoDeComienzo(Math.max(tiempoDeComienzoSiguiente, processes.get(i).getTiempoDeLLegada()));
            processes.get(i).setTiempoFinal(processes.get(i).getTiempoDeComienzo() + processes.get(i).getRafaga());
            processes.get(i).setTiempoDeRetorno(processes.get(i).getTiempoFinal() - processes.get(i).getTiempoDeLLegada());
            processes.get(i).setTiempoDeEspera(processes.get(i).getTiempoDeRetorno() - processes.get(i).getRafaga());

            tiempoDeComienzoSiguiente = processes.get(i).getTiempoFinal();

            ArrayList<String> dato = new ArrayList<>();
            dato.add(processes.get(i).getNombre());
            dato.add(processes.get(i).getTiempoDeLLegada()+"");
            dato.add(processes.get(i).getRafaga()+"");
            dato.add(processes.get(i).getTiempoDeComienzo()+"");
            dato.add(processes.get(i).getTiempoFinal()+"");
            dato.add(processes.get(i).getTiempoDeRetorno()+"");
            dato.add(processes.get(i).getTiempoDeEspera()+"");
            datos.set(i, dato);
        }

        view.loadGUI();
    }

    private void showGUI() {
        Theme.setDarkTheme();
        view = new AppView(this);
    }
    public void addProcess(Proceso proceso) {
        processes.add(proceso);
        datos.add(proceso.toArray());
        view.loadGUI();
    }

    public ArrayList<ArrayList<String>> getDatos() {
        return datos;
    }

    public ArrayList<Proceso> getBloqueados() {
        return blockList;
    }

    public Proceso getEnEjecucion() {
        return enEjecucion;
    }

    public int getTiempo() {
        return currentTime;
    }
}
