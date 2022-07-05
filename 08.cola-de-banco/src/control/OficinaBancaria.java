package control;

import model.ColaBanco;
import model.Nodo;
import view.AppView;

import javax.swing.*;

public class OficinaBancaria {

    public OficinaBancaria() {
        new AppView();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int opcion = 0, nodo_informacion = 0, nodo_transacciones = 0;
        ColaBanco cola = new ColaBanco();

        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Menu de opciones\n\n"
                        + "1. Insertar nodo\n"
                        + "2. Extraer nodo\n"
                        + "3. Mostrar contenido de la Cola\n"
                        + "4. Salir\n"));

                switch (opcion) {
                    case 1:
                        nodo_informacion = Integer.parseInt(JOptionPane.showInputDialog(null, 
                                "Por favor ingresar el identificador del nodo"));
                        nodo_transacciones = Integer.parseInt(JOptionPane.showInputDialog(null, 
                                "Por favor ingresar el numero de transacciones del nodo"));
                        Nodo nodoNuevo = new Nodo(nodo_informacion, nodo_transacciones);
                        cola.insertar(nodoNuevo);
                        break;
                    case 2:
                        if (!cola.ColaVacia()) {
                            JOptionPane.showMessageDialog(null, "Se atendio al nodo "
                                    + cola.extraer().getID());
                        } else {
                            JOptionPane.showMessageDialog(null, "La cola esta vacia");
                        }
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
        } while (opcion != 4);
    }

}
