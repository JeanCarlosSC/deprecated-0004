package view.sComponent;

import javax.swing.*;
import java.awt.*;

public class SLabel extends JLabel {

    public SLabel (int x, int y, ImageIcon icon) {
        setProperties(x, y, icon, null);
    }

    /**
     * icon label
     */
    public void setProperties(int x, int y, ImageIcon icon) {
        this.setSize(icon.getIconWidth(), icon.getIconHeight());
        this.setLocation(x, y);
        this.setIcon(icon);
    }

    public void setProperties(int x, int y, ImageIcon icon, Cursor cursor) {
        setProperties(x, y, icon);
        this.setCursor(cursor);
    }

}
