package view;

import sRAD_java.gui.sComponent.STable;

import java.util.ArrayList;

public abstract class TComponent {
    protected STable sTable;

    abstract void updateComponent(ArrayList<ArrayList<String>> data);
    public STable getComponent() {
        return sTable;
    }
}
