package Model;

public class Game implements OpponentHasMadeATurnListener {

    private final Player opponent;
    private final GameField gameField;

    private GameState currentGameState;
    private OpponentTurnEvaluatedListener opponentTurnEvaluatedListener;

    public Game(Player opponent) {
        this.opponent = opponent;
        this.currentGameState = GameState.OpponentsTurn;
        this.gameField = new GameField();
    }

    public TurnResult playDisk(int column) {
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
        if (this.currentGameState == GameState.MyTurn) {
            throw new IllegalStateException("Opponent played a disk while it was my turn.");
        }

        this.currentGameState = GameState.MyTurn;

        DiskPosition diskPosition = this.gameField.setOpponentsDisk(column);
        WinState winCheckResult = this.gameField.checkIfSomebodyWon();
        
        if (this.opponentTurnEvaluatedListener != null) {
            TurnResult turnResult = new TurnResult(winCheckResult, diskPosition);
            this.opponentTurnEvaluatedListener.opponentTurnEvaluated(turnResult);
        }
    }

    public void setListener(OpponentTurnEvaluatedListener opponentTurnEvaluatedListener) {
        this.opponentTurnEvaluatedListener = opponentTurnEvaluatedListener;
    }
}
