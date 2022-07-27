package view;

import control.AppController;
import model.Proceso;
import sRAD_java.gui.component.Resource;
import sRAD_java.gui.sComponent.SPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class Gantt extends SPanel {
    private AppView view;
    private int time;
    private ArrayList<Proceso> processes;
    private AppController appController;

    public Gantt(AppView view, AppController appController) {
        time = 0;
        this.view = view;
        this.appController = appController;
        this.processes = appController.getProcesses();
        setBounds(2, 2, 1196, 241);
        setBackground(Resource.DTII2);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // grid
        int columnGap = 30;
        int rowGap = 37;
        for (int i = 0; i < Math.max(39, time); i++) {
            g.setColor(Resource.DTII5);
            g.drawLine(i*columnGap+30, 0, i*columnGap+30, getHeight());
            g.setColor(Resource.WHITE);
            g.drawString(i+"", 32+i*columnGap, 15);
        }

        // content
        time = appController.getTiempo();

        for (int i=0; i<processes.size(); i++) {
            Proceso p = processes.get(i);

            int y = i*rowGap+35;
            for (int j = 0; j < processes.size(); j++) {
                if(processes.get(j).getNombre().equals(p.getNombre()) && j!=i && j<i) {
                    y = j*rowGap+35;
                    break;
                }
            }
            if (y == i*rowGap+35) {
                g.drawString(p.getNombre(), 10, i * rowGap + 40);
            }

            g.setColor(new Color((i*20)%100+150,(i*50)%100+150,(i*50+50)%100+150));
            if (p.getTiempoDeLLegada()<time) {
                g.drawLine(p.getTiempoDeLLegada()*columnGap+30,y,
                        Math.min(time, p.getTiempoDeComienzo())*columnGap+30, y);
            }
            if (p.getTiempoDeComienzo()<time) {
                g.fillRect(p.getTiempoDeComienzo()*columnGap+30,y-5,
                        Math.min(time-p.getTiempoDeComienzo(), p.getRafaga())*columnGap, 10);
            }
        }
        int height;
        if (processes.size()<6) {
            height = 241;
        }
        else {
            height = processes.size()*40+40;
        }

        int width;
        if (time<39) {
            width = 1196;
        }
        else {
            width = time*35+40;
        }
        setSize(width, height);


        // current time
        g.setColor(Color.ORANGE);
        g.drawLine(time*columnGap+30, 0, time*columnGap+30, getHeight());
    }

    public void updateUI(AppController appController) {
        repaint();
    }
}
