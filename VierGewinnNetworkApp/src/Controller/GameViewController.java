package Controller;

import Model.Game_NEW;
import Model.NewOpponentDiskAvailableOnGameFieldListener;
import Views.Interfaces.GameView;
import Views.Interfaces.GameViewListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GameViewController implements GameViewListener, NewOpponentDiskAvailableOnGameFieldListener{

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
        int column = this.game.playDisk(row);
        this.view.showNewDiskForMe(column, row);
    }

    @Override
    public void SaveGamePressed() {
        //todo implement save game handling
    }

    @Override
    public void newOpponentDiskAvailableOnGameField(int column, int row) {
        this.view.showNewDiskForOpponent(column, row);
    }
}
