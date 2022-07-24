package view;

import sRAD_java.gui.sComponent.STable;

import java.util.ArrayList;

public interface TComponent {
    void updateComponent(ArrayList<ArrayList<String>> data);
    STable getComponent();
}
