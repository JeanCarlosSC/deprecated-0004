package model;

public class Cola {
    private final Nodo proceso = new Nodo(0);
    public Nodo inicioCola, finalCola;

    public Cola() {
        inicioCola = proceso;
        finalCola = proceso;
    }

    public boolean ColaVacia() {
        return inicioCola == proceso;
    }

    // metodo para insertar en la cola
    public void insertar(Nodo nodo) {
        nodo.siguiente = proceso;

        if (ColaVacia()) {
            inicioCola = nodo;
        } else {
            finalCola.siguiente = nodo;
        }
        finalCola = nodo;
    }

    // metodo para extraer en la cola
    public Nodo extraer() {
        if (!ColaVacia()) {
            Nodo informacion = inicioCola;
            if (inicioCola.nTransacciones <= 4) {

                if (inicioCola == finalCola) {
                    inicioCola = proceso;
                    finalCola = proceso;
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
        String cola = "";

        while (recorrido != proceso) {
            cola = String.format("%s\n%12s      %12s", cola, "" + recorrido.id, "" + recorrido.nTransacciones);
            recorrido = recorrido.siguiente;
        }

        return cola;
    }

}
