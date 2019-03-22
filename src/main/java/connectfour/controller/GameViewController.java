package connectfour.controller;

import connectfour.model.DiskPosition;
import connectfour.model.Game;
import connectfour.model.GameState;
import connectfour.model.TurnResult;
import connectfour.views.interfaces.GameView;
import connectfour.views.interfaces.GameViewListener;
import connectfour.model.WinState;
import connectfour.model.TurnEvaluatedListener;

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
