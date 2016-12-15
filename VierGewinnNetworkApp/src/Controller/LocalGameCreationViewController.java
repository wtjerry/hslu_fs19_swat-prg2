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
        //todo implement resume game pressed.
    }

    @Override
    public void NewGamePressed() {
        this.navigator.navigateToGameViewForLocalPlay();
    }
}
