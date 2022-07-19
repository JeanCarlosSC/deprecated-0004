package control;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import model.Proceso;
import sRAD_java.gui.component.Theme;
import view.AppView;

public class AppController {
    private AppView view;
    private ArrayList<ArrayList<String>> datos;
    private ArrayList<Proceso> procesos;
    private Proceso enEjecucion;
    private ArrayList<Proceso> enBloqueo;
    private int time;
    private boolean isWorking;
    
    public AppController() {
        enEjecucion = null;
        procesos = new ArrayList<>();
        datos = new ArrayList<>();
        enBloqueo = new ArrayList<>();
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
        time = 0;
        while(isWorking) {
            // agregar a bloqueo
            for (Proceso proceso: procesos) {
                if(proceso.getTiempoDeLLegada()==time) {
                    enBloqueo.add(proceso);
                }
            }

            // agregar a ejecucion
            for (Proceso proceso: procesos) {
                if(enEjecucion == null) {
                    if(!enBloqueo.isEmpty()) {
                        enEjecucion = enBloqueo.get(0);
                        enBloqueo.remove(0);
                    }
                }
            }
            view.step();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            time ++;
        }
    }

    private void actualizarDatos() {
        int tiempoDeComienzoSiguiente = 0;

        Collections.sort(procesos, Comparator.comparing(Proceso::getTiempoDeLLegada));

        for(int i=0; i<procesos.size(); i++) {
            procesos.get(i).setTiempoDeComienzo(Math.max(tiempoDeComienzoSiguiente, procesos.get(i).getTiempoDeLLegada()));
            procesos.get(i).setTiempoFinal(procesos.get(i).getTiempoDeComienzo() + procesos.get(i).getRafaga());
            procesos.get(i).setTiempoDeRetorno(procesos.get(i).getTiempoFinal() - procesos.get(i).getTiempoDeLLegada());
            procesos.get(i).setTiempoDeEspera(procesos.get(i).getTiempoDeRetorno() - procesos.get(i).getRafaga());

            tiempoDeComienzoSiguiente = procesos.get(i).getTiempoFinal();

            ArrayList<String> dato= new ArrayList<>();
            dato.add(procesos.get(i).getNombre());
            dato.add(procesos.get(i).getTiempoDeLLegada()+"");
            dato.add(procesos.get(i).getRafaga()+"");
            dato.add(procesos.get(i).getTiempoDeComienzo()+"");
            dato.add(procesos.get(i).getTiempoFinal()+"");
            dato.add(procesos.get(i).getTiempoDeRetorno()+"");
            dato.add(procesos.get(i).getTiempoDeEspera()+"");
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
        procesos.add(proceso);

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
        for (Proceso proceso: enBloqueo) {
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
}
