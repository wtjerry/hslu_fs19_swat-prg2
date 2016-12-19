package Controller;

import Model.DiskPosition;
import Model.Game;
import Model.GameState;
import Model.TurnResult;
import Views.Interfaces.GameView;
import Views.Interfaces.GameViewListener;
import Model.WinState;
import Model.TurnEvaluatedListener;

public abstract class GameViewController implements GameViewListener, TurnEvaluatedListener {

    private final GameView view;
    private final Navigator navigator;
    
    protected Game game;

    GameViewController(GameView view, Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    abstract void init(GameState startGameState);

    public abstract void initForResumeGame();
    
    @Override
    public abstract void SaveGamePressed();
    @Override public void CloseGamePressed(){
        this.navigator.navigateToStartView();
    }
    
    @Override
    public void DiskColumnPressed(int column) {
        this.game.playDisk(column);
    }

    @Override
    public void myTurnEvaluated(TurnResult turnResult) {
        DiskPosition diskPosition = turnResult.getDiskPosition();
        this.view.showNewDiskForMe(diskPosition.getColumn(), diskPosition.getRow());

        this.notifyViewAboutWinIfRequired(turnResult.getWinState());
    }
    
    @Override
    public void opponentTurnEvaluated(TurnResult turnResult) {
        DiskPosition diskPosition = turnResult.getDiskPosition();
        this.view.showNewDiskForOpponent(diskPosition.getColumn(), diskPosition.getRow());

        this.notifyViewAboutWinIfRequired(turnResult.getWinState());
    }

    private void notifyViewAboutWinIfRequired(WinState winState) {
        if (winState == WinState.OpponentWon) {
            this.view.showOpponentWonDialog();
            this.navigator.navigateToStartView();
        }
        if (winState == WinState.IWon) {
            this.view.showIWonDialog();
            this.navigator.navigateToStartView();
        }
    }
}
