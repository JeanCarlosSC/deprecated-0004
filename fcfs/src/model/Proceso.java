package model;

public class Proceso {
    private String nombre;
    private int tiempoDeLLegada;
    private int rafaga;
    private int tiempoDeComienzo;
    private int tiempoFinal;
    private int tiempoDeRetorno;
    private int tiempoDeEspera;

    public Proceso(String nombre, int tiempoDeLLegada, int rafaga) {
        this.nombre = nombre;
        this.tiempoDeLLegada = tiempoDeLLegada;
        this.rafaga = rafaga;
        this.tiempoDeComienzo = 0;
        this.tiempoFinal = 0;
        this.tiempoDeRetorno = 0;
        this.tiempoDeEspera = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoDeLLegada() {
        return tiempoDeLLegada;
    }

    public int getRafaga() {
        return rafaga;
    }

    public int getTiempoDeComienzo() {
        return tiempoDeComienzo;
    }

    public int getTiempoFinal() {
        return tiempoFinal;
    }

    public int getTiempoDeRetorno() {
        return tiempoDeRetorno;
    }

    public int getTiempoDeEspera() {
        return tiempoDeEspera;
    }
}
