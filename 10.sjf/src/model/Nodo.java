package model;

public class Nodo {
    int id;
    int nTransacciones;
    Nodo siguiente;

    public Nodo(int id, int nTransacciones) {
        this.id = id;
        this.nTransacciones = nTransacciones;
    }
    public Nodo(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
