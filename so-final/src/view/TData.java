package view;

import sRAD_java.gui.sComponent.STable;

import java.util.ArrayList;

public class TData extends TComponent{
    public TData() {
        header = new ArrayList<>();
        header.add("Proceso");
        header.add("T. de llegada");
        header.add("Ráfaga");
        header.add("T. de comienzo");
        header.add("T. de final");
        header.add("T. de retorno");
        header.add("T. de espera");
    }

    @Override
    public void updateComponent(ArrayList<ArrayList<String>> data) {
        ArrayList<ArrayList<String>> tContent = new ArrayList<>();
        tContent.add(header);

        for (ArrayList<String> array: data) {
            tContent.add(array);
        }

        sTable = new STable(32, 62, 1194, 130, tContent, 169, 18);
    }
}
