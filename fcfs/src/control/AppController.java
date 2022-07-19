package control;

import view.AppView;

public class AppController {
    private AppView view;
    
    public AppController() {
        view = new AppView(this);
    }
}
