package control;

import java.util.ArrayList;
import java.util.Comparator;

import model.Method;
import model.Proceso;
import sRAD_java.gui.component.Theme;
import view.AppView;

public class AppController {

    private AppView view;
    private ArrayList<ArrayList<String>> datos;
    private ArrayList<ArrayList<String>> datosBackup;
    private ArrayList<Proceso> processes;
    private ArrayList<Proceso> processesBackup;
    private Proceso enEjecucion;
    private ArrayList<Proceso> blockList;
    private int currentTime;
    private boolean isEjecutando;
    private Method method;

    public AppController() {
        enEjecucion = null;
        processes = new ArrayList<>();
        datos = new ArrayList<>();
        blockList = new ArrayList<>();
        isEjecutando = false;
        method = Method.FCFS;
        showGUI();

        addProcess(new Proceso("A", 0, 8, 2));
        addProcess(new Proceso("B", 1, 4, 3));
        addProcess(new Proceso("C", 2, 9, 7));
        addProcess(new Proceso("D", 3, 5, 4));
        addProcess(new Proceso("E", 4, 2, 5));

        run();
    }

    private void run() {
        updateData();
        startSimulation();
    }

    public synchronized void startSimulation() {
        currentTime = 0;
        isEjecutando = true;
        int size = 0;
        while (isEjecutando) {
            addToBlockList();
            deleteFromExecutionList();
            addToExecutionList();

            view.step();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (enEjecucion == null) {
                break;
            }
            currentTime++;
        }
    }

    public void updateData() {
        int tiempoDeComienzoSiguiente = 0;

        ArrayList<Proceso> procesos = (ArrayList) processes.clone();
        procesos.sort(Comparator.comparing(Proceso::getTiempoDeLLegada));

        for (int i = 0; i < processes.size(); i++) {
            if (i != 0) {
                procesos.remove(0);
            }
            if (i == 1) {
                procesos.sort(Comparator.comparing(Proceso::getPrioridad).reversed());
            }
            procesos.get(0).setTiempoDeComienzo(Math.max(tiempoDeComienzoSiguiente, procesos.get(0).getTiempoDeLLegada()));
            procesos.get(0).setTiempoFinal(procesos.get(0).getTiempoDeComienzo() + procesos.get(0).getRafaga());
            procesos.get(0).setTiempoDeRetorno(procesos.get(0).getTiempoFinal() - procesos.get(0).getTiempoDeLLegada());
            procesos.get(0).setTiempoDeEspera(procesos.get(0).getTiempoDeRetorno() - procesos.get(0).getRafaga());

            tiempoDeComienzoSiguiente = procesos.get(0).getTiempoFinal();

            ArrayList<String> dato = new ArrayList<>();
            dato.add(procesos.get(0).getNombre());
            dato.add(procesos.get(0).getTiempoDeLLegada() + "");
            dato.add(procesos.get(0).getRafaga() + "");
            dato.add(procesos.get(0).getPrioridad() + "");
            dato.add(procesos.get(0).getTiempoDeComienzo() + "");
            dato.add(procesos.get(0).getTiempoFinal() + "");
            dato.add(procesos.get(0).getTiempoDeRetorno() + "");
            dato.add(procesos.get(0).getTiempoDeEspera() + "");
            dato.add(procesos.get(0).getTiempoDeBloqueo() + "");
            for(int j=0; j<datos.size(); j++) {
                if(datos.get(j).get(0).equals(dato.get(0))) {
                    datos.set(j, dato);
                    break;
                }
            }
        }

        view.loadGUI();
    }

    public void restart() {
        stop();
        startSimulation();
    }

    public void stop() {
        isEjecutando = false;
        blockList = new ArrayList<>();
        enEjecucion = null;
    }

    private void deleteFromExecutionList() {
        if (enEjecucion != null && enEjecucion.getTiempoFinal() <= currentTime) {
            enEjecucion = null;
        }
    }

    private void addToExecutionList() {
        if (enEjecucion == null && !blockList.isEmpty()) {
            while (!blockList.isEmpty()) {
                blockList.sort(Comparator.comparing(Proceso::getPrioridad).reversed());
                if (blockList.get(0).getTiempoFinal() != 0 && blockList.get(0).getTiempoFinal() > currentTime) {
                    enEjecucion = blockList.get(0);
                    blockList.remove(0);
                    break;
                }
                blockList.remove(0);
            }
        }
    }

    private void addToBlockList() {
        for (Proceso process : processes) {
            if (process.getTiempoDeLLegada() == currentTime) {
                blockList.add(process);
            }
        }
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

    public void setMethod(int i) {
        switch (i) {
            case 1:
                method = Method.SJF;
                break;
            case 2:
                method = Method.RR;
                break;
            default:
                method = Method.FCFS;
                break;
        }
        new Thread(() -> run()).start();
    }
}
