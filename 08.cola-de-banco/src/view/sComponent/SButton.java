package view.sComponent;

import view.component.Theme;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static view.component.Resource.*;
import static view.component.Theme.btBg2;
import static view.component.Theme.btF;

public class SButton extends JButton {

    //TEXT BUTTON
    public SButton(int x, int y, int width, int height, String text) {
        this(x, y, width, height, text, handCursor, fontTitleMini, Theme.btBg, btF, DTII4Border, btBg2, DTII4Border);
    }
    public SButton(int x, int y, int width, int height, String text, Cursor cursor, Font font, Color background, Color foreground, Border border,
                   Color backgroundEntered, Border borderEntered) {
        setProperties(x, y, width, height, text, cursor, font, background, foreground, border, backgroundEntered, borderEntered);
    }

    public void setProperties(int x, int y, int width, int height, String text, Cursor cursor, Font font, Color background, Color foreground,
                              Border border, Color backgroundEntered, Border borderEntered) {
        setProperties(x, y, width, height, cursor, background);
        setText(text);
        setFont(font);
        setForeground(foreground);
        setBorder(border);
        setHorizontalAlignment(SButton.CENTER);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(backgroundEntered);
                setBorder(borderEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(background);
                setBorder(border);
            }
        });
    }

    public void setProperties(int x, int y, int width, int height, Cursor cursor, Color background) {
        setLocation(x, y);
        setSize(width, height);
        setCursor(cursor);
        setBackground(background);
        setContentAreaFilled(true);
        setFocusPainted(false);
    }

}
