package control;

import view.AppView;

import java.util.ArrayList;

import static sRAD_java.logic.Extension.isInt;

public class AppController {
    private AppView view;
    private ArrayList<ArrayList<String>> datos;

    public AppController() {
        datos = new ArrayList<>();
        view = new AppView(this) {
            @Override
            public void insertarDato(String proceso, String tDeLlegada, String rafaga) {
                if(isInt(tDeLlegada) && isInt(rafaga) && !proceso.isEmpty()) {
                    ArrayList<String> nuevoDato = new ArrayList<>();
                    nuevoDato.add(proceso);
                    nuevoDato.add(tDeLlegada);
                    nuevoDato.add(rafaga);
                    nuevoDato.add("0");
                    nuevoDato.add("0");
                    nuevoDato.add("0");
                    nuevoDato.add("0");
                    datos.add(nuevoDato);
                }
            }
        };
    }
    public ArrayList<ArrayList<String>> getDatos() {
        return datos;
    }
}
