package connectfour.controller;

import connectfour.model.network.Settings;
import connectfour.views.interfaces.LocalGameCreationViewListener;

public class LocalGameCreationViewController implements LocalGameCreationViewListener {

    private final Navigator navigator;

    LocalGameCreationViewController(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void resumeGamePressed() {
        this.navigator.navigateToGameViewForResumingLocalGame();
    }

    @Override
    public void newGamePressed(int width, int height) {
        Settings.setGameFieldWidth(width);
        Settings.setGameFieldHeight(height);
        this.navigator.navigateToGameViewForLocalPlay();
    }

    @Override
    public void backPressed() {
        this.navigator.navigateToStartView();
    }
}
