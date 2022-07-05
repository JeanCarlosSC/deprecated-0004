package view.component;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Resource {

//----------------------------------------COLOUR PALETTES---------------------------------------------------------------
    //basic
    public final static Color BLACK = new Color(0, 0, 0);

    public final static Color MDB4 = new Color(101, 107, 235);
    public final static Color MDB5 = new Color(174, 177, 238);

    //others and specials
    public final static Color blackTransparent = new Color(0F, 0F, 0F, 0.8F);

    public final static Color DTII5 = new Color(81, 81, 81); //mainToolbar bottom border
    public final static Color DTII6 = new Color(85, 85, 85);//console border
    public final static Color DTII8 = new Color(114, 115, 122); //var names without use font
    public final static Color DTII14 = new Color(187,187,187); //main color font
// -----------------------------------------FONT------------------------------------------------------------------------
    //Windows standard (es windows porque funciona en windows, es decir, no se ha basado en fuentes del SO)
    public final static Font fontTitle = new Font("Gill Sans MT Condensed", Font.BOLD, 32);
    public final static Font fontTitle2 = new Font("Arial", Font.BOLD, 18);
    public final static Font fontTitleMini = new Font("Arial", Font.PLAIN, 14); //used in title of mainBar, buttons

    public final static Font fontText = new Font("Courier New", Font.PLAIN, 17);

    public final static Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

//----------------------------------------BORDER (B)----------------------------------------------------------------
    public static Border blackBorderTransparent = BorderFactory.createLineBorder(blackTransparent, 2, false);
    public final static Border DTII4Border = BorderFactory.createLineBorder(DTII6, 2, false);

}
