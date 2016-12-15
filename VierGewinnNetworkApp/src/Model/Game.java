package Model;

import Model.Network.Settings;
import java.util.*;

public class Game implements OpponentHasMadeATurnListener {

    private final int width;
    private final int height;
    private final int myDiskId;
    private final Player opponent;
    private GameState currentGameState;

    // example of a array with height (rows, r) 4 and width (columns, c) 6:
    // { c0r0, c1r0, c2r0, c3r0, c4r0, c5r0, c0r1, c1r1, c2r1 ... c2r3, c3r3, c4r3, c5r3 }
    // to get the index of, for example, c2r1 we can do 2 +1*6 (c+r*width) = 8 
    private final int[] gameArray;

    private NewOpponentDiskAvailableOnGameFieldListener newOpponentDiskAvailableOnGameFieldListener;

    public Game(int myDiskId, Player opponent) {
        this.width = Settings.getGameFieldWidth();
        this.height = Settings.getGameFieldHeight();
        this.myDiskId = myDiskId;
        this.opponent = opponent;
        this.gameArray = new int[height * width];
        this.currentGameState = GameState.OpponentsTurn;
    }

    public DiskPosition playDisk(int column) {
        if (this.currentGameState == GameState.OpponentsTurn) {
            throw new IllegalStateException("I played a disk while it was opponents turn.");
        }

        this.currentGameState = GameState.OpponentsTurn;
        DiskPosition diskPosition = this.setMyDisk(column);
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

        DiskPosition diskPosition = this.setOpponentsDisk(column);
        //todo evaluate whether somebody won
        //todo jerry if somebody won, somehow notify ui (controller listener) and detach NetworkHandler for DiskPlayed
        if (this.newOpponentDiskAvailableOnGameFieldListener != null) {
            this.newOpponentDiskAvailableOnGameFieldListener.newOpponentDiskAvailableOnGameField(diskPosition);
        }
    }

    private DiskPosition setMyDisk(int column) {
        return addDiskToArray(column, this.myDiskId);
    }

    private DiskPosition setOpponentsDisk(int column) {
        return addDiskToArray(column, this.opponent.getDiskId());
    }

    private DiskPosition addDiskToArray(int column, int diskId) {
        if (column <= this.width) {
            for (int indexOfCurrentHeight = 0; indexOfCurrentHeight < this.height; indexOfCurrentHeight++) {
                int index = column + indexOfCurrentHeight * this.width;
                if (this.gameArray[index] == 0) {
                    this.gameArray[index] = diskId;
                    return new DiskPosition(column, indexOfCurrentHeight);
                }
            }
        }
        
        return DiskPosition.getInvalidDiskPosition();
    }

    public TurnResult checkIfSomebodyWon() {
        TurnResult result = TurnResult.NobodyWon;
        for (int i : this.getNumbers()) {
            if (i == this.myDiskId * 4) {
                result = TurnResult.IWon;
            }
            if (i == this.opponent.getDiskId() * 4) {
                result = TurnResult.OpponentWon;
            }
        }
        return result;
    }

    public ArrayList<Integer> getNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();

        //Vertikal
        for (int gameheight = 0; gameheight < height * width; gameheight += width) {
            for (int gameposition = 0; gameposition < (width - 3); gameposition++) {
                int result = 0;
                for (int gamewidht = 0; gamewidht < 4; gamewidht++) {
                    result += this.gameArray[gameheight + gameposition + gamewidht];
                }
                numbers.add(result);
            }
        }

        //Height
        for (int gamewidht = 0; gamewidht < height - 3; gamewidht++) {
            for (int gameposition = 0; gameposition < (width); gameposition++) {
                int result = 0;
                for (int gameheight = 0; gameheight < 4 * width; gameheight += width) {
                    result += this.gameArray[gameheight + gameposition + gamewidht];
                }
                numbers.add(result);
            }
        }

        //Diagonal
        for (int gamewidht = 0; gamewidht < height - 3; gamewidht += width) {
            for (int gameposition = 0; gameposition < (width - 3); gameposition++) {
                int result = 0;
                for (int gameheight = 0; gameheight < 4 * (width + 1); gameheight += width + 1) {
                    result += this.gameArray[gameheight + gameposition + gamewidht];
                }
                numbers.add(result);
            }
        }

        //Diagonal
        for (int gamewidht = 0; gamewidht < height - 3; gamewidht += width) {
            for (int gameposition = 0; gameposition < (width - 3); gameposition++) {
                int result = 0;
                for (int gameheight = 3; gameheight < 4 * (width - 1); gameheight += width - 1) {
                    result += this.gameArray[gameheight + gameposition + gamewidht];
                }
                numbers.add(result);
            }
        }

        return numbers;
    }

    public void setListener(NewOpponentDiskAvailableOnGameFieldListener newOpponentDiskAvailableOnGameFieldListener) {
        this.newOpponentDiskAvailableOnGameFieldListener = newOpponentDiskAvailableOnGameFieldListener;
    }
}
