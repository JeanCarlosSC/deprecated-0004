package control;

import model.ColaBanco;
import model.Nodo;
import view.AppView;

import javax.swing.*;

public class OficinaBancaria {
    private final ColaBanco cola = new ColaBanco();
    private final AppView view;

    public OficinaBancaria() {
        view = new AppView(this);
    }

    public void insertarCliente() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Por favor ingresar el identificador del nodo"));
        int cantidadTransacciones = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Por favor ingresar el numero de transacciones del nodo"));
        Nodo nodoNuevo = new Nodo(id, cantidadTransacciones);
        cola.insertar(nodoNuevo);
        view.updateContent(cola);
    }
    public void atender() {
        if (!cola.ColaVacia()) {
            JOptionPane.showMessageDialog(null, "Se atendió al cliente "
                    + cola.extraer().getID());
        } else {
            JOptionPane.showMessageDialog(null, "La cola esta vacía");
        }
        view.updateContent(cola);
    }
}
