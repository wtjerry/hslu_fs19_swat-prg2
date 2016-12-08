package Controller;

import Views.Interfaces.GameView;
import Views.Interfaces.GameViewListener;

public class GameViewController implements GameViewListener{

    private final GameView view;
    private final Navigator navigator;

    GameViewController(GameView view, Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    void init() {
    }
    
    @Override
    public void DiskRowPressed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SaveGamePressed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
