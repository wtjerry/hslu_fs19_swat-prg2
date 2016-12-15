package Controller;

import Model.DiskPosition;
import Model.Game;
import Model.NewOpponentDiskAvailableOnGameFieldListener;
import Views.Interfaces.GameView;
import Views.Interfaces.GameViewListener;

public abstract class GameViewController implements GameViewListener, NewOpponentDiskAvailableOnGameFieldListener{

    private final GameView view;
    private final Navigator navigator;
    
    protected Game game;

    GameViewController(GameView view, Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    abstract void init();
    
    @Override
    public void DiskColumnPressed(int column) {
        DiskPosition diskPosition = this.game.playDisk(column);
        this.view.showNewDiskForMe(diskPosition.getColumn(), diskPosition.getRow());
    }

    @Override
    public void SaveGamePressed() {
        //todo implement save game handling
    }

    @Override
    public void newOpponentDiskAvailableOnGameField(DiskPosition diskPosition) {
        this.view.showNewDiskForOpponent(diskPosition.getColumn(), diskPosition.getRow());
    }
}
