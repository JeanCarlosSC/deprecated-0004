package model;

public class Priority {
    int prioridad;
    Cola fcfs;
    Cola sjf;
    Cola rr;
    
    public Priority() {
        fcfs = new Cola();
        sjf = new Cola();
        rr = new Cola();
    }
    
    public void agregarProcesoAFCFS(Nodo proceso) {
        fcfs.insertar(proceso);
    }
    
    public void agregarProcesoASJF(Nodo proceso) {
        sjf.insertar(proceso);
    }
    
    public void agregarProcesoARR(Nodo proceso) {
        rr.insertar(proceso);
    }
    
    public Nodo getEnEjecucion() {
        if(fcfs.noEstaVacio()) {
            prioridad = 3;
            fcfs.organizar(prioridad);
            return fcfs.obtenerSiguienteEnListaDeBloqueo();
        }
        else if(sjf.noEstaVacio()) {
            prioridad = 2;
            sjf.organizar(prioridad);
            return sjf.obtenerSiguienteEnListaDeBloqueo();
        }
        else if(rr.noEstaVacio()) {
            prioridad = 1;
            rr.organizar(prioridad);
            return rr.obtenerSiguienteEnListaDeBloqueo();
        }
        else {
            return null;
        }
    }
    
}
