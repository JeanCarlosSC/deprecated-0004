package control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.Proceso;
import sRAD_java.gui.component.Theme;
import view.AppView;

public class AppController {
    private AppView view;
    private final ArrayList<ArrayList<String>> datos;
    private final ArrayList<Proceso> processes;
    private Proceso enEjecucion;
    private final ArrayList<Proceso> blockList;
    private int currentTime;

    public AppController() {
        enEjecucion = null;
        processes = new ArrayList<>();
        datos = new ArrayList<>();
        blockList = new ArrayList<>();
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
        while(currentTime < 100) {
            addToBlockList();
            deleteFromExecutionList();
            addToExecutionList();

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
            int shortest = 0;
            for(int i=0; i<blockList.size(); i++) {
                if(blockList.get(i).getRafaga() < blockList.get(shortest).getRafaga()) {
                    shortest = i;
                }
            }
            enEjecucion = blockList.get(shortest);
            blockList.remove(shortest);
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

        ArrayList<Proceso> procesos= (ArrayList) processes.clone();
        procesos.sort(Comparator.comparing(Proceso::getTiempoDeLLegada));

        for(int i = 0; i< processes.size(); i++) {
            if(i != 0) {
                procesos.remove(0);
            }
            if(i == 1) {
                procesos.sort(Comparator.comparing(Proceso::getRafaga));
            }
            procesos.get(0).setTiempoDeComienzo(Math.max(tiempoDeComienzoSiguiente, procesos.get(0).getTiempoDeLLegada()));
            procesos.get(0).setTiempoFinal(procesos.get(0).getTiempoDeComienzo() + procesos.get(0).getRafaga());
            procesos.get(0).setTiempoDeRetorno(procesos.get(0).getTiempoFinal() - procesos.get(0).getTiempoDeLLegada());
            procesos.get(0).setTiempoDeEspera(procesos.get(0).getTiempoDeRetorno() - procesos.get(0).getRafaga());

            tiempoDeComienzoSiguiente = procesos.get(0).getTiempoFinal();

            ArrayList<String> dato = new ArrayList<>();
            dato.add(procesos.get(0).getNombre());
            dato.add(procesos.get(0).getTiempoDeLLegada()+"");
            dato.add(procesos.get(0).getRafaga()+"");
            dato.add(procesos.get(0).getTiempoDeComienzo()+"");
            dato.add(procesos.get(0).getTiempoFinal()+"");
            dato.add(procesos.get(0).getTiempoDeRetorno()+"");
            dato.add(procesos.get(0).getTiempoDeEspera()+"");
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

    public ArrayList<Proceso> getProcesses() {
        return processes;
    }
}
