package sRAD_java.gui.component;

import javax.swing.border.Border;
import java.awt.*;

import static sRAD_java.gui.component.Resource.*;

/**
 * bg = background
 */
public class Theme {
    //general
    public static Color bg2 = DTII1;
    //frame
    public static Color fBg = WHITE;
    //button
    public static Color btBg = DTII5;
    public static Color btBg2 = DTII8;
    public static Color btF = DTII14;
    //ventana emergente
    public static Color veBg = DTII4;
    //panel
    public static Color pBg = DTII3;
    public static Border pB = DTII4Border; //border
    //font
    public static Color fFg = DTII14; //foreground text
    //main bar
    public static Color mbFg = DTII7; //foreground
    //tabbed pane
    public static Color tpBg = WP4;
    public static Color tpFg = BLACK; // foreground
    // text area
    public static Color taF = DTII14;

    public static void setLightTheme() {
        btBg = MDB5;
        btBg2 = MDB4;
        btF = BLACK;
        taF = BLACK;
    }
    
    public static void setDarkTheme() {
        // frame
        fBg = DTII3;
    }
}
