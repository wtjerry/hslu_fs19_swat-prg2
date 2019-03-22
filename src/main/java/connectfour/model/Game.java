package connectfour.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Game implements OpponentHasMadeATurnListener {

    private final Player opponent;
    private final GameField gameField;
    private final List<PlayedTurn> playedTurns;
    
    private GameState currentGameState;
    private TurnEvaluatedListener turnEvaluatedListener;

    public Game(GameField gameField, Player opponent, GameState startGameState) {
        this.opponent = opponent;
        this.gameField = gameField;
        this.playedTurns = new ArrayList<>();
        this.currentGameState = startGameState;
    }

    public void playDisk(int column) {
        if (this.currentGameState == GameState.OPPONENTS_TURN) {
            throw new IllegalStateException("I played a disk while it was opponents turn.");
        }

        this.currentGameState = GameState.OPPONENTS_TURN;
        DiskPosition diskPosition = this.gameField.setMyDisk(column);
        WinState winCheckResult = this.gameField.checkIfSomebodyWon();
        
        this.playedTurns.add(new PlayedTurn(true, column));
        
        this.opponent.makeYourTurnNowAsync(column);

        if (this.turnEvaluatedListener != null) {
            TurnResult turnResult = new TurnResult(winCheckResult, diskPosition);
            this.turnEvaluatedListener.myTurnEvaluated(turnResult);
        }
    }

    @Override
    public void opponentHasMadeATurn(int column) {
        if (this.currentGameState == GameState.MY_TURN) {
            throw new IllegalStateException("Opponent played a disk while it was my turn.");
        }

        this.currentGameState = GameState.MY_TURN;

        DiskPosition diskPosition = this.gameField.setOpponentsDisk(column);
        WinState winCheckResult = this.gameField.checkIfSomebodyWon();
        
        this.playedTurns.add(new PlayedTurn(false, column));
        
        if (this.turnEvaluatedListener != null) {
            TurnResult turnResult = new TurnResult(winCheckResult, diskPosition);
            this.turnEvaluatedListener.opponentTurnEvaluated(turnResult);
        }
    }

    public void setListener(TurnEvaluatedListener opponentTurnEvaluatedListener) {
        this.turnEvaluatedListener = opponentTurnEvaluatedListener;
    }

    public void saveTo(ObjectOutputStream gameSaveStream) throws IOException {
        gameSaveStream.writeObject(this.playedTurns);
        gameSaveStream.flush();
    }

    @SuppressWarnings("unchecked")
    public void resume(ObjectInputStream in) throws ClassNotFoundException, IOException {

        List<PlayedTurn> turnsToBeReplayed = (ArrayList<PlayedTurn>) in.readObject();
        
        for (PlayedTurn turn : turnsToBeReplayed) {
            if (turn.wasMyTurn()) {
                this.playDisk(turn.getColumn());
            } else {
                this.opponentHasMadeATurn(turn.getColumn());
            }
        }
    }
}
