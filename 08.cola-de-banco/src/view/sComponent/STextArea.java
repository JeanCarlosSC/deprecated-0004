package view.sComponent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static view.component.Resource.*;
import static view.component.Theme.taF;

public class STextArea extends JTextArea {
    public STextArea(int x, int y, int width, int height, String text) {
        setProperties(x, y, width, height, false, true, text, taF, null, fontText, null, LEFT_ALIGNMENT);
    }

    public void setProperties(int x, int y, int width, int height, Boolean editable, Boolean lineWrap, String text, Color foreground, Color background,
                              Font font, Border border, Float hAlignment
    ) {
        this.setBounds(x, y, width, height);
        setText(text);
        setEditable(editable);
        setForeground(foreground);
        setFont(font);
        setBackground(background);
        setCaretColor(foreground);
        setBorder(border);
        setWrapStyleWord(lineWrap);
        setLineWrap(lineWrap);
        setAlignmentX(hAlignment);
    }

}
