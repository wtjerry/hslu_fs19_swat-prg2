package Model;

public class Game_NEW implements OpponentHasMadeATurnListener {

    private final Player_NEW opponent;
    private GameState_NEW currentGameState;
    private NewOpponentDiskAvailableOnGameFieldListener newOpponentDiskAvailableOnGameFieldListener;

    public Game_NEW(Player_NEW opponent) {
        this.opponent = opponent;
        this.currentGameState = GameState_NEW.OpponentsTurn;
    }

    public int playDisk(int column) {
        if (this.currentGameState == GameState_NEW.OpponentsTurn) {
            throw new IllegalStateException("I played a disk while it was opponents turn.");
        }
        
        this.currentGameState = GameState_NEW.OpponentsTurn;
        //todo this.gameField.setMyDisk(column);
        //todo evaluate whether somebody won
        //todo jerry if somebody won, somehow notify ui (controller listener) and detach NetworkHandler for DiskPlayed
        this.opponent.makeYourTurnNowAsync(column);
        
        int row = 2; // todo check on which row my played disk landed in the end
        return row;
    }

    public void setListener(NewOpponentDiskAvailableOnGameFieldListener newOpponentDiskAvailableOnGameFieldListener) {
        this.newOpponentDiskAvailableOnGameFieldListener = newOpponentDiskAvailableOnGameFieldListener;
    }

    @Override
    public void opponentHasMadeATurn(int column) {
        if (this.currentGameState == GameState_NEW.MyTurn) {
            throw new IllegalStateException("Opponent played a disk while it was my turn.");
        }

        this.currentGameState = GameState_NEW.MyTurn;

        //todo this.gameField.setOpponentDisk(column);
        //todo evaluate whether somebody won
        //todo jerry if somebody won, somehow notify ui (controller listener) and detach NetworkHandler for DiskPlayed
        
        if (this.newOpponentDiskAvailableOnGameFieldListener != null) {
            int row = 3; // todo check on which row the disk landed in the end
            this.newOpponentDiskAvailableOnGameFieldListener.newOpponentDiskAvailableOnGameField(column, row);
        }
    }
}
