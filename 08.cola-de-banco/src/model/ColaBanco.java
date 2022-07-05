package model;

import javax.swing.*;

public class ColaBanco {

    private final Nodo cajero = new Nodo(0);
    public Nodo inicioCola, finalCola;
    String cola = "";

    public ColaBanco() {
        inicioCola = cajero;
        finalCola = cajero;
    }

    public boolean ColaVacia() {
        return inicioCola == cajero;
    }

    //Metodo para insertar en la cola
    public void insertar(Nodo nodo) {
        Nodo nuevo_nodo = nodo;
        nuevo_nodo.siguiente = cajero;

        if (ColaVacia()) {
            inicioCola = nuevo_nodo;
        } else {
            finalCola.siguiente = nuevo_nodo;
        }
        finalCola = nuevo_nodo;
    }

    //Metodo para extraer en la cola
    public Nodo extraer() {
        if (!ColaVacia()) {
            Nodo informacion = inicioCola;
            if (inicioCola.nTransacciones <= 4) {

                if (inicioCola == finalCola) {
                    inicioCola = cajero;
                    finalCola = cajero;
                } else {
                    inicioCola = inicioCola.siguiente;
                }
            } else {
                inicioCola.nTransacciones -= 4;
                Nodo extra = inicioCola;
                inicioCola = inicioCola.siguiente;
                insertar(extra);
            }
            return informacion;
        } else {
            return null;
        }
    }

    //Metodo para mostrar contenido de la cola
    public String getContent() {
        Nodo recorrido = inicioCola;
        cola = "";

        while (recorrido != cajero) {
            cola = String.format("%s\n%12s      %12s", cola, ""+recorrido.id, ""+recorrido.nTransacciones);
            recorrido = recorrido.siguiente;
        }

        return cola;
    }

}
