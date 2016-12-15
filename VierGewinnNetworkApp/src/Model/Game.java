package Model;

public class Game implements OpponentHasMadeATurnListener {

    private final Player opponent;
    private final GameField gameField;

    private GameState currentGameState;
    private NewOpponentDiskAvailableOnGameFieldListener newOpponentDiskAvailableOnGameFieldListener;

    public Game(int myDiskId, Player opponent) {
        this.opponent = opponent;
        this.currentGameState = GameState.OpponentsTurn;
        this.gameField = new GameField(myDiskId, this.opponent.getDiskId());
    }

    public DiskPosition playDisk(int column) {
        if (this.currentGameState == GameState.OpponentsTurn) {
            throw new IllegalStateException("I played a disk while it was opponents turn.");
        }

        this.currentGameState = GameState.OpponentsTurn;
        DiskPosition diskPosition = this.gameField.setMyDisk(column);
        //todo evaluate whether somebody won
        //todo jerry if somebody won, somehow notify ui (controller listener) and detach NetworkHandler for DiskPlayed
        this.opponent.makeYourTurnNowAsync(column);

        return diskPosition;
    }

    @Override
    public void opponentHasMadeATurn(int column) {
        if (this.currentGameState == GameState.MyTurn) {
            throw new IllegalStateException("Opponent played a disk while it was my turn.");
        }

        this.currentGameState = GameState.MyTurn;

        DiskPosition diskPosition = this.gameField.setOpponentsDisk(column);
        //todo evaluate whether somebody won
        //todo jerry if somebody won, somehow notify ui (controller listener) and detach NetworkHandler for DiskPlayed
        if (this.newOpponentDiskAvailableOnGameFieldListener != null) {
            this.newOpponentDiskAvailableOnGameFieldListener.newOpponentDiskAvailableOnGameField(diskPosition);
        }
    }

    public void setListener(NewOpponentDiskAvailableOnGameFieldListener newOpponentDiskAvailableOnGameFieldListener) {
        this.newOpponentDiskAvailableOnGameFieldListener = newOpponentDiskAvailableOnGameFieldListener;
    }
}
