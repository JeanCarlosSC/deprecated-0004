package model;

import java.util.ArrayList;

import java.util.ArrayList;

public class Priority {
    Procesador colaPrioridad;
    Nodo Fcfs = new Nodo(1);
    Nodo Sjf = new Nodo(2);
    Nodo RR = new Nodo(3);
    
    public Priority(){
        colaPrioridad = new Procesador();
        colaPrioridad.insertar(Fcfs);
        colaPrioridad.insertar(Sjf);
        colaPrioridad.insertar(RR);
    }
    
    public ArrayList getordenPrioridad(){
        ArrayList orden = new ArrayList();
        orden.add(colaPrioridad.inicioCola);
        orden.add(colaPrioridad.inicioCola.siguiente);
        orden.add(colaPrioridad.finalCola);
        return  orden;
    }
    
}
