package model;

import control.Launcher;
import static java.lang.reflect.Array.get;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Cola {
    int FCFS = 3;
    int SJF = 2;
    int RR = 1;
    
    int prioridad = FCFS;
    
    private final Nodo proceso = new Nodo(0);
    public Nodo inicioCola, finalCola;

    public Cola() {
        inicioCola = proceso;
        finalCola = proceso;
    }

    public boolean isColaVacia() {
        return inicioCola == proceso;
    }

    // metodo para insertar en la cola
    public void insertar(Nodo nodo) {
        nodo.siguiente = proceso;

        if (isColaVacia()) {
            inicioCola = nodo;
        } else {
            finalCola.siguiente = nodo;
        }
        finalCola = nodo;
    }

    // metodo para extraer en la cola
    public Nodo extraer() {
        if (!isColaVacia()) {
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

    public boolean noEstaVacio() {
        return !isColaVacia();
    }

    public Nodo obtenerSiguienteEnListaDeBloqueo() {
        return extraer();
    }

    public void organizar(int criterio) {
        switch(criterio) {
            case 3 -> prioridad = FCFS;
            case 2 -> prioridad = SJF;
            case 1 -> prioridad = RR;
        }
        Collections.sort(Launcher.getListActual(), (o1, o2) -> {
            return prioridad; 
        });
    }

    private static <T>
    int fcfs(List<? extends Comparable<? super T>> list, T key) {
        int low = 0;
        int high = list.size()-1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = list.get(mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid;
        }
        return -(low + 1);
    }

    private static <T>
    int sjf(List<? extends Comparable<? super T>> list, T key) {
        int low = 0;
        int high = list.size()-1;
        ListIterator<? extends Comparable<? super T>> i = list.listIterator();

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = (Comparable<? super T>) get(i, mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid;
        }
        return -(low + 1);
    }
    
    private static <T> T rr(ListIterator<? extends T> i, int index) {
        T obj = null;
        int pos = i.nextIndex();
        if (pos <= index) {
            do {
                obj = i.next();
            } while (pos++ < index);
        } else {
            do {
                obj = i.previous();
            } while (--pos > index);
        }
        return obj;
    }
}
