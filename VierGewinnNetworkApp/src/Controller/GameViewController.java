package Controller;

import Model.Game_NEW;
import Model.Network.RequestHandling.DefaultHandler;
import Model.OpponentPlayedDiskListener;
import Views.Interfaces.GameView;
import Views.Interfaces.GameViewListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GameViewController implements GameViewListener, OpponentPlayedDiskListener{

    private final GameView view;
    private final Navigator navigator;
    
    protected Game_NEW game;

    GameViewController(GameView view, Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    abstract void init();
    
    @Override
    public void DiskRowPressed(int row) {
        this.game.playDisk(row);
    }

    @Override
    public void SaveGamePressed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void opponentPlayedDisk(int row) {
        Logger.getLogger(GameViewController.class.getName()).log(Level.INFO, "Opponent played disk in row: {0}:", row);
        //this.view.showNewDisk(row, row, row);
    }
}
