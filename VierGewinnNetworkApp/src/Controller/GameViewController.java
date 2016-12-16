package Controller;

import Model.DiskPosition;
import Model.Game;
import Model.GameState;
import Model.TurnResult;
import Views.Interfaces.GameView;
import Views.Interfaces.GameViewListener;
import Model.OpponentTurnEvaluatedListener;

public abstract class GameViewController implements GameViewListener, OpponentTurnEvaluatedListener{

    private final GameView view;
    private final Navigator navigator;
    
    protected Game game;

    GameViewController(GameView view, Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    abstract void init(GameState startGameState);
    
    @Override
    public void DiskColumnPressed(int column) {
        TurnResult turnResult = this.game.playDisk(column);
        //todo jerry if somebody won, somehow notify ui and detach NetworkHandler for DiskPlayed
        DiskPosition diskPosition = turnResult.getDiskPosition();
        this.view.showNewDiskForMe(diskPosition.getColumn(), diskPosition.getRow());
    }

    @Override
    public void SaveGamePressed() {
        //todo implement save game handling
    }

    @Override
    public void opponentTurnEvaluated(TurnResult turnResult) {
        //todo jerry if somebody won, somehow notify ui and detach NetworkHandler for DiskPlayed
        DiskPosition diskPosition = turnResult.getDiskPosition();
        this.view.showNewDiskForOpponent(diskPosition.getColumn(), diskPosition.getRow());
    }
}
