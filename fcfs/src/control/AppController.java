package control;

import java.lang.reflect.Array;
import java.util.ArrayList;

import model.Proceso;
import sRAD_java.gui.component.Theme;
import view.AppView;

public class AppController {
    private AppView view;
    
    private ArrayList<ArrayList<String>> datos;
    private ArrayList<Proceso> procesos;
    
    public AppController() {
        procesos = new ArrayList<>();
        datos = new ArrayList<>();
        showGUI();

        addProcess(new Proceso("A", 1, 5));
        addProcess(new Proceso("B", 2, 4));
        addProcess(new Proceso("C", 3, 6));
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
}
