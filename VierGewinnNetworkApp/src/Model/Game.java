package Model;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Game implements OpponentHasMadeATurnListener {

    private final Player opponent;
    private final GameField gameField;

    private GameState currentGameState;
    private OpponentTurnEvaluatedListener opponentTurnEvaluatedListener;

    public Game(Player opponent, GameState startGameState) {
        this.opponent = opponent;
        this.currentGameState = startGameState;
        this.gameField = new GameField();
    }

    public TurnResult playDisk(int column) {
        
        
        Logger.getLogger(Game.class.getName()).log(Level.INFO, "********** playDisk: {0}", column);
        
        
        
        if (this.currentGameState == GameState.OpponentsTurn) {
            throw new IllegalStateException("I played a disk while it was opponents turn.");
        }

        this.currentGameState = GameState.OpponentsTurn;
        DiskPosition diskPosition = this.gameField.setMyDisk(column);
        WinState winCheckResult = this.gameField.checkIfSomebodyWon();
        
        this.opponent.makeYourTurnNowAsync(column);

        return new TurnResult(winCheckResult, diskPosition);
    }

    @Override
    public void opponentHasMadeATurn(int column) {
        
        
        Logger.getLogger(Game.class.getName()).log(Level.INFO, "********** opponentHasMadeATurn: {0}", column);
        
        
        
        if (this.currentGameState == GameState.MyTurn) {
            throw new IllegalStateException("Opponent played a disk while it was my turn.");
        }

        this.currentGameState = GameState.MyTurn;

        DiskPosition diskPosition = this.gameField.setOpponentsDisk(column);
        WinState winCheckResult = this.gameField.checkIfSomebodyWon();
        
        Logger.getLogger(Game.class.getName()).log(Level.INFO, "********** opponentHasMadeATurn: checkpoint 2");
        if (this.opponentTurnEvaluatedListener != null) {
            Logger.getLogger(Game.class.getName()).log(Level.INFO, "********** opponentHasMadeATurn: checkpoint 3");
            TurnResult turnResult = new TurnResult(winCheckResult, diskPosition);
            this.opponentTurnEvaluatedListener.opponentTurnEvaluated(turnResult);
        }
        
        
        
        Logger.getLogger(Game.class.getName()).log(Level.INFO, "++++++ opponentHasMadeATurn END");
        
        
    }

    public void setListener(OpponentTurnEvaluatedListener opponentTurnEvaluatedListener) {
        this.opponentTurnEvaluatedListener = opponentTurnEvaluatedListener;
    }
}
