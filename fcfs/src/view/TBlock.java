package view;

import sRAD_java.gui.sComponent.STable;

import java.util.ArrayList;

public class TBlock implements TComponent{
    private STable sTable;
    private ArrayList<String> header;

    public TBlock() {
        header = new ArrayList<>();
        header.add("Proceso");
        header.add("T. de llegada");
        header.add("Ráfaga");
        header.add("T. de espera");
    }

    @Override
    public void updateComponent(ArrayList<ArrayList<String>> data) {
        ArrayList<ArrayList<String>> tContent = new ArrayList<>();
        tContent.add(header);

        for (ArrayList<String> array: data) {
            tContent.add(array);
        }

        sTable = new STable(32, 232, 570, 110, tContent, 140, 18);
    }

    @Override
    public STable getComponent() {
        return sTable;
    }
}
