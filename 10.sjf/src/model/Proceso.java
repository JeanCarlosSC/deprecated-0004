package model;

import java.util.ArrayList;

public class Proceso {
    private final String nombre;
    private final int tiempoDeLLegada;
    private final int rafaga;
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

    public void setTiempoDeComienzo(int tiempoDeComienzo) {
        this.tiempoDeComienzo = tiempoDeComienzo;
    }

    public void setTiempoFinal(int tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

    public void setTiempoDeRetorno(int tiempoDeRetorno) {
        this.tiempoDeRetorno = tiempoDeRetorno;
    }

    public void setTiempoDeEspera(int tiempoDeEspera) {
        this.tiempoDeEspera = tiempoDeEspera;
    }

    public ArrayList toArray() {
        ArrayList array = new ArrayList();
        array.add(nombre);
        array.add(tiempoDeLLegada+"");
        array.add(rafaga+"");
        array.add(tiempoDeComienzo+"");
        array.add(tiempoFinal+"");
        array.add(tiempoDeRetorno+"");
        array.add(tiempoDeEspera+"");
        return array;
    }
}
