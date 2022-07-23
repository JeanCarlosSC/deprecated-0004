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
        actualizarDatos();
        iniciarSimulacion();
    }

    private void iniciarSimulacion() {
        currentTime = 0;
        while(isWorking) {
            addToBlockList();

            // agregar a ejecucion
            for (Proceso proceso: processes) {
                if(enEjecucion == null) {
                    if(!blockList.isEmpty()) {
                        enEjecucion = blockList.get(0);
                        blockList.remove(0);
                    }
                }
            }
            view.step();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentTime++;
        }
    }

    private void addToBlockList() {
        for (Proceso process: processes) {
            if(process.getTiempoDeLLegada() == currentTime) {
                blockList.add(process);
            }
        }
    }

    private void actualizarDatos() {
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
        view.updateGUI();
    }

    private void showGUI() {
        Theme.setDarkTheme();
        view = new AppView(this);
    }
    public ArrayList<ArrayList<String>> getDatos() {
        return datos;
    }

    public void addProcess(Proceso proceso) {
        processes.add(proceso);

        ArrayList<String> dato = new ArrayList<>();
        dato.add(proceso.getNombre());
        dato.add(proceso.getTiempoDeLLegada()+ "");
        dato.add(proceso.getRafaga()+ "");
        dato.add(proceso.getTiempoDeComienzo()+ "");
        dato.add(proceso.getTiempoFinal()+ "");
        dato.add(proceso.getTiempoDeRetorno()+ "");
        dato.add(proceso.getTiempoDeEspera()+ "");
        datos.add(dato);

        view.updateGUI();
    }

    public ArrayList<ArrayList<String>> getBloqueados() {
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        for (Proceso proceso: blockList) {
            ArrayList<String> dato = new ArrayList<>();
            dato.add(proceso.getNombre());
            dato.add(proceso.getTiempoDeLLegada()+"");
            dato.add(proceso.getRafaga()+"");
            dato.add(proceso.getTiempoDeComienzo()+"");
            dato.add(proceso.getTiempoFinal()+"");
            dato.add(proceso.getTiempoDeRetorno()+"");
            dato.add(proceso.getTiempoDeEspera()+"");
            arrayList.add(dato);
        }
        return arrayList;
    }

    public Proceso getEnEjecucion() {
        return enEjecucion;
    }

    public int getTiempo() {
        return currentTime;
    }
}
