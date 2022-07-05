package view.component;

import java.awt.*;

import static view.component.Resource.*;

/**
 * bg = background
 */
public class Theme {
    //button
    public static Color btBg = DTII5;
    public static Color btBg2 = DTII8;
    public static Color btF = DTII14;
    // text area
    public static Color taF = DTII14;

    public static void setLightTheme() {
        btBg = MDB5;
        btBg2 = MDB4;
        btF = BLACK;
        taF = BLACK;
    }
}
