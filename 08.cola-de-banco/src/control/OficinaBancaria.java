package control;

import model.ColaBanco;
import model.Nodo;
import view.AppView;

import javax.swing.*;

public class OficinaBancaria {
    private ColaBanco cola = new ColaBanco();
    private AppView view;

    public OficinaBancaria() {
        view = new AppView(this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {/*

        int opcion = 0, nodo_informacion = 0, nodo_transacciones = 0;

        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Menu de opciones\n\n"
                        + "2. Extraer nodo\n"

                switch (opcion) {
                    case 2:

                        break;
                    case 3:
                        cola.mostrarContenido();
                        break;
                    case 4:
                        opcion = 4;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion no disponible");
                        break;
                }
            } catch (NumberFormatException e) {
            }
        } while (opcion != 4);*/
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
