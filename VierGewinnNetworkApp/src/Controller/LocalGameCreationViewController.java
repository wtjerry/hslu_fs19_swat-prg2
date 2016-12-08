package Controller;

import Views.Interfaces.LocalGameCreationViewListener;

public class LocalGameCreationViewController implements LocalGameCreationViewListener {

    private final Navigator navigator;

    public LocalGameCreationViewController(Navigator navigator) {
        this.navigator = navigator;
    }

    void init() {
    }
    
    @Override
    public void ResumeGamePressed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void NewGamePressed() {
        this.navigator.navigateToGameView();
    }
}
