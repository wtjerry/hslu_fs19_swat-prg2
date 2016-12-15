package Controller;

import Model.Game_NEW;
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
        //todo ui must know which row AND column the played disk finaly arrived (as it should not calculate that again) get this information from the game field maybe?
        //this.view.showNewDiskForMe(column, row);
    }

    @Override
    public void SaveGamePressed() {
        //todo implement save game handling
    }

    @Override
    public void opponentPlayedDisk(int row) {
        Logger.getLogger(GameViewController.class.getName()).log(Level.INFO, "Opponent played disk in row: {0}:", row);
        //todo ui must know which row AND column the played disk finaly arrived (as it should not calculate that again) get this information from the game field maybe?
        //this.view.showNewDiskForOpponent(column, row);
    }
}
