package connect4.Controller;

import connect4.Model.DiskPosition;
import connect4.Model.Game;
import connect4.Model.GameState;
import connect4.Model.TurnResult;
import connect4.Views.Interfaces.GameView;
import connect4.Views.Interfaces.GameViewListener;
import connect4.Model.WinState;
import connect4.Model.TurnEvaluatedListener;

public abstract class GameViewController implements GameViewListener, TurnEvaluatedListener {

    private final GameView view;
    private final Navigator navigator;
    
    Game game;

    GameViewController(GameView view, Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    abstract void init(GameState startGameState);

    public abstract void initForResumeGame();
    
    @Override
    public abstract void saveGamePressed();
    @Override public void closeGamePressed(){
        this.navigator.navigateToStartView();
    }
    
    @Override
    public void diskColumnPressed(int column) {
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
        if (winState == WinState.OPPONENT_WON) {
            this.view.showOpponentWonDialog();
            this.navigator.navigateToStartView();
        }
        if (winState == WinState.I_WON) {
            this.view.showIWonDialog();
            this.navigator.navigateToStartView();
        }
    }
}
