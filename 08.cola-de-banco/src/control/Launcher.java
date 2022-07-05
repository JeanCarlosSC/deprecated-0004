package control;

import view.component.Theme;

public class Launcher {
    public static void main(String[] args) {
        Theme.setLightTheme();
        new OficinaBancaria();
    }
}
