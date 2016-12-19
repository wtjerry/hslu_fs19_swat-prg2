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
        this.navigator.navigateToGameViewForResumingLocalGame();
    }

    @Override
    public void NewGamePressed() {
        this.navigator.navigateToGameViewForLocalPlay();
    }
    @Override
    public void NewGamePressed(int x, int y) {
        this.navigator.navigateToGameViewForLocalPlay(x, y);
    }
    @Override
    public void BackPressed() {
        this.navigator.navigateToStartView();
    }
}
