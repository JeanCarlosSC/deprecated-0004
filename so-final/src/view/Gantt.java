package view;

import control.AppController;
import model.Proceso;
import sRAD_java.gui.component.Resource;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class Gantt extends JPanel{
    private AppView view;
    private int time;
    private ArrayList<Proceso> processes;
    private AppController appController;

    public Gantt(AppView view, AppController appController) {
        time = 0;
        this.view = view;
        this.appController = appController;
        this.processes = appController.getProcesses();
        setBounds(2, 2, 1196, 201);
        setBackground(Resource.DTII2);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // grid
        int columnGap = 30;
        int rowGap = 37;
        for (int i = 0; i < 39; i++) {
            g.setColor(Resource.DTII5);
            g.drawLine(i*columnGap+30, 0, i*columnGap+30, 210);
            g.setColor(Resource.WHITE);
            g.drawString(i+"", 32+i*columnGap, 4*40+35);
        }

        // content
        time = appController.getTiempo();

        for (int i=0; i<processes.size(); i++) {
            Proceso p = processes.get(i);
            g.drawString(p.getNombre(), 10, i*rowGap+25);

            g.setColor(new Color((i*20)%100+150,(i*50)%100+150,(i*50+50)%100+150));
            if (p.getTiempoDeLLegada()<time) {
                g.drawLine(p.getTiempoDeLLegada()*columnGap+30,i*rowGap+20,
                        Math.min(time, p.getTiempoDeComienzo())*columnGap+30, i*rowGap+20);
            }
            if (p.getTiempoDeComienzo()<time) {
                g.fillRect(p.getTiempoDeComienzo()*columnGap+30,i*rowGap+15,
                        Math.min(time-p.getTiempoDeComienzo(), p.getRafaga())*columnGap, 10);
            }
        }

        // current time
        g.setColor(Color.ORANGE);
        g.drawLine(time*columnGap+30, 0, time*columnGap+30, 210);
    }

    public void updateUI(AppController appController) {
        repaint();
    }
}
