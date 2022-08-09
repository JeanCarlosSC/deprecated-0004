package control;

import java.util.ArrayList;
import java.util.Comparator;

import model.Method;
import model.Proceso;
import sRAD_java.gui.component.Theme;
import view.AppView;

public class AppController {
    private AppView view;
    private ArrayList<ArrayList<String>> datos;
    private ArrayList<ArrayList<String>> datosBackup;
    private ArrayList<Proceso> processes;
    private ArrayList<Proceso> processesBackup;
    private Proceso enEjecucion;
    private ArrayList<Proceso> blockList;
    private int currentTime;
    private boolean isEjecutando;
    private Method method;

    public AppController() {
        enEjecucion = null;
        processes = new ArrayList<>();
        datos = new ArrayList<>();
        blockList = new ArrayList<>();
        isEjecutando = false;
        method = Method.FCFS;
        showGUI();

        addProcess(new Proceso("A", 0, 8));
        addProcess(new Proceso("B", 1, 4));
        addProcess(new Proceso("C", 2, 9));
        addProcess(new Proceso("D", 3, 5));
        addProcess(new Proceso("E", 4, 2));

        run();
    }

    private void run() {
        updateData(0);
        startSimulation();
    }

    public synchronized void startSimulation() {
        currentTime = 0;
        isEjecutando = true;
        int size=0;
        while(isEjecutando) {
            addToBlockList();
            deleteFromExecutionList();
            addToExecutionList();

            view.step();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(enEjecucion==null) {
                if(method == Method.RR) {
                    break;
                }
                if(method == Method.FCFS) {
                    method = Method.SJF;
                    size = processes.size();
                    for(int i=0; i<size; i++) {
                        Proceso p = processes.get(i);
                        Proceso newP = new Proceso(p.getNombre(), p.getTiempoDeLLegada()+currentTime, p.getRafaga());
                        addProcess(newP);
                    }
                    updateData(currentTime);
                }
                else if(method == Method.SJF) {
                    method = Method.RR;
                    for(int i=0; i<size; i++) {
                        Proceso p = processes.get(i);
                        Proceso newP = new Proceso(p.getNombre(), p.getTiempoDeLLegada()+currentTime, p.getRafaga());
                        addProcess(newP);
                    }
                    updateData(currentTime);
                }
            }
            currentTime++;
        }
    }

    public void restart() {
        stop();
        startSimulation();
    }
    
    public void stop() {
        isEjecutando = false;
        blockList = new ArrayList<>();
        enEjecucion = null;
    }
    
    private void deleteFromExecutionList() {
        if(enEjecucion != null && enEjecucion.getTiempoFinal() <= currentTime) {
            enEjecucion = null;
        }
    }
    
    private void addToExecutionList() {
        if(enEjecucion == null && !blockList.isEmpty()) {
            while(!blockList.isEmpty()) {
                if(blockList.get(0).getTiempoFinal()!=0 && blockList.get(0).getTiempoFinal() > currentTime) {
                    enEjecucion = blockList.get(0);
                    blockList.remove(0);
                    break;
                }
                blockList.remove(0);
            }
        }
    }
    
    private void addToBlockList() {
        for (Proceso process: processes) {
            if(process.getTiempoDeLLegada() == currentTime) {
                blockList.add(process);
            }
        }
    }

    public void updateData(int from) {
        if(method == Method.FCFS) {
            updateDataFCFS();
        }
        else if(method == Method.SJF) {
            updateDataSJF(from);
        }
        else {
            updateDataRR();
        }
    }

    private synchronized void updateDataRR() {
        if(processesBackup == null) {
            processesBackup = new ArrayList<>();
            for (int i=0; i<processes.size(); i++) {
                processesBackup.add(
                    new Proceso(
                        processes.get(i).getNombre(),
                        processes.get(i).getTiempoDeLLegada(),
                        processes.get(i).getRafaga()
                    )
                );
            }
            datosBackup = (ArrayList<ArrayList<String>>) datos.clone();
        }
        int tiempoDeComienzoSiguiente = 0;

        processes.sort(Comparator.comparing(Proceso::getTiempoDeLLegada));

        for(int i = 0; i< processes.size(); i++) {
            if (processes.get(i).getTiempoFinal() == 0) {
                int rafaga = processes.get(i).getRafaga();
                int nuevaRafaga = 0;
                if(rafaga > 3) {
                    nuevaRafaga = rafaga - 3;
                    rafaga = 3;
                    processes.get(i).setRafaga(3);
                }
                processes.get(i).setTiempoDeComienzo(Math.max(tiempoDeComienzoSiguiente, processes.get(i).getTiempoDeLLegada()));
                processes.get(i).setTiempoFinal(processes.get(i).getTiempoDeComienzo() + rafaga);
                processes.get(i).setTiempoDeRetorno(processes.get(i).getTiempoFinal() - processes.get(i).getTiempoDeLLegada());
                processes.get(i).setTiempoDeEspera(processes.get(i).getTiempoDeRetorno() - rafaga);

                if(nuevaRafaga > 0) {
                    processes.add(new Proceso(processes.get(i).getNombre(), processes.get(i).getTiempoFinal(), nuevaRafaga));
                }
                tiempoDeComienzoSiguiente = processes.get(i).getTiempoFinal();

                ArrayList<String> dato = new ArrayList<>();
                dato.add(processes.get(i).getNombre());
                dato.add(processes.get(i).getTiempoDeLLegada()+"");
                dato.add(processes.get(i).getRafaga()+"");
                dato.add(processes.get(i).getTiempoDeComienzo()+"");
                dato.add(processes.get(i).getTiempoFinal()+"");
                dato.add(processes.get(i).getTiempoDeRetorno()+"");
                dato.add(processes.get(i).getTiempoDeEspera()+"");
                try {
                    datos.set(i, dato);
                }
                catch (java.lang.IndexOutOfBoundsException e) {
                    datos.add(i, dato);
                }
            }
        }

        view.loadGUI();
    }

    private synchronized void updateDataSJF(int from) {
        if (processesBackup != null) {
            processes = processesBackup;
            datos = datosBackup;
        }
        int tiempoDeComienzoSiguiente = from;

        ArrayList<Proceso> procesos= (ArrayList) processes.clone();
        procesos.sort(Comparator.comparing(Proceso::getTiempoDeLLegada));

        boolean sorted = true;
        boolean active = false;
        
        for(int i = 0; i< processes.size(); i++) {
            if(i != 0) {
                procesos.remove(0);
            }
            if(procesos.get(0).getTiempoFinal()==0) {
                if(!sorted) {
                    procesos.sort(Comparator.comparing(Proceso::getRafaga));
                    sorted = true;
                }
                if(!active) {
                    active = true;
                    sorted = false;
                }
                procesos.get(0).setTiempoDeComienzo(Math.max(tiempoDeComienzoSiguiente, procesos.get(0).getTiempoDeLLegada()));
                procesos.get(0).setTiempoFinal(procesos.get(0).getTiempoDeComienzo() + procesos.get(0).getRafaga());
                procesos.get(0).setTiempoDeRetorno(procesos.get(0).getTiempoFinal() - procesos.get(0).getTiempoDeLLegada());
                procesos.get(0).setTiempoDeEspera(procesos.get(0).getTiempoDeRetorno() - procesos.get(0).getRafaga());

                tiempoDeComienzoSiguiente = procesos.get(0).getTiempoFinal();

                ArrayList<String> dato = new ArrayList<>();
                dato.add(procesos.get(0).getNombre());
                dato.add(procesos.get(0).getTiempoDeLLegada()+"");
                dato.add(procesos.get(0).getRafaga()+"");
                dato.add(procesos.get(0).getTiempoDeComienzo()+"");
                dato.add(procesos.get(0).getTiempoFinal()+"");
                dato.add(procesos.get(0).getTiempoDeRetorno()+"");
                dato.add(procesos.get(0).getTiempoDeEspera()+"");
                datos.set(i, dato);
            }
        }

        view.loadGUI();
    }
    
    private synchronized void updateDataFCFS() {
        if(processesBackup != null) {
            processes = processesBackup;
            datos = datosBackup;
        }
        int tiempoDeComienzoSiguiente = 0;

        processes.sort(Comparator.comparing(Proceso::getTiempoDeLLegada));

        for(int i = 0; i< processes.size(); i++) {
            processes.get(i).setTiempoDeComienzo(Math.max(tiempoDeComienzoSiguiente, processes.get(i).getTiempoDeLLegada()));
            processes.get(i).setTiempoFinal(processes.get(i).getTiempoDeComienzo() + processes.get(i).getRafaga());
            processes.get(i).setTiempoDeRetorno(processes.get(i).getTiempoFinal() - processes.get(i).getTiempoDeLLegada());
            processes.get(i).setTiempoDeEspera(processes.get(i).getTiempoDeRetorno() - processes.get(i).getRafaga());

            tiempoDeComienzoSiguiente = processes.get(i).getTiempoFinal();

            ArrayList<String> dato = new ArrayList<>();
            dato.add(processes.get(i).getNombre());
            dato.add(processes.get(i).getTiempoDeLLegada()+"");
            dato.add(processes.get(i).getRafaga()+"");
            dato.add(processes.get(i).getTiempoDeComienzo()+"");
            dato.add(processes.get(i).getTiempoFinal()+"");
            dato.add(processes.get(i).getTiempoDeRetorno()+"");
            dato.add(processes.get(i).getTiempoDeEspera()+"");
            datos.set(i, dato);
        }

        view.loadGUI();
    }

    private void showGUI() {
        Theme.setDarkTheme();
        view = new AppView(this);
    }
    
    public void addProcess(Proceso proceso) {
        processes.add(proceso);
        datos.add(proceso.toArray());
        view.loadGUI();
    }

    public ArrayList<ArrayList<String>> getDatos() {
        return datos;
    }

    public ArrayList<Proceso> getBloqueados() {
        return blockList;
    }

    public Proceso getEnEjecucion() {
        return enEjecucion;
    }

    public int getTiempo() {
        return currentTime;
    }

    public ArrayList<Proceso> getProcesses() {
        return processes;
    }

    public void setMethod(int i) {
        switch (i) {
            case 1:
                method = Method.SJF;
                break;
            case 2:
                method = Method.RR;
                break;
            default:
                method = Method.FCFS;
                break;
        }
        new Thread(() -> run()).start();
    }
}
