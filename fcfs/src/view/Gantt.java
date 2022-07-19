package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Gantt extends JPanel{
    private AppView view;

    public Gantt(AppView view) {
        this.view = view;
        
        setBounds(32, 400, 1200, 205);
        setBackground(Color.white);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}
