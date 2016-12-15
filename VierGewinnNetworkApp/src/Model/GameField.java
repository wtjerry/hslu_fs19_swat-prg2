package Model;

import Model.Network.Settings;
import java.util.ArrayList;

public class GameField {

    private final int height;
    private final int width;
    private final int myDiskId;
    private final int opponentDiskId;

    // example of a array with height (rows, r) 4 and width (columns, c) 6:
    // { c0r0, c1r0, c2r0, c3r0, c4r0, c5r0, c0r1, c1r1, c2r1 ... c2r3, c3r3, c4r3, c5r3 }
    // to get the index of, for example, c2r1 we can do 2 +1*6 (c+r*width) = 8 
    private final int[] gameArray;

    public GameField(int myDiskId, int opponentDiskId) {
        this.width = Settings.getGameFieldWidth();
        this.height = Settings.getGameFieldHeight();
        this.gameArray = new int[this.width * this.height];
        this.myDiskId = myDiskId;
        this.opponentDiskId = opponentDiskId;
    }

    public DiskPosition setMyDisk(int column) {
        return addDiskToArray(column, this.myDiskId);
    }

    public DiskPosition setOpponentsDisk(int column) {
        return addDiskToArray(column, this.opponentDiskId);
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
            if (i == this.opponentDiskId* 4) {
                result = TurnResult.OpponentWon;
            }
        }
        return result;
    }

    public ArrayList<Integer> getNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();

        //Vertikal
        for (int gameHeight = 0; gameHeight < height * width; gameHeight += width) {
            for (int gamePosition = 0; gamePosition < (width - 3); gamePosition++) {
                int result = 0;
                for (int gameWidth = 0; gameWidth < 4; gameWidth++) {
                    result += this.gameArray[gameHeight + gamePosition + gameWidth];
                }
                numbers.add(result);
            }
        }

        //Height
        for (int gameWidth = 0; gameWidth < height - 3; gameWidth++) {
            for (int gameposition = 0; gameposition < (width); gameposition++) {
                int result = 0;
                for (int gameheight = 0; gameheight < 4 * width; gameheight += width) {
                    result += this.gameArray[gameheight + gameposition + gameWidth];
                }
                numbers.add(result);
            }
        }

        //Diagonal
        for (int gameWidth = 0; gameWidth < height - 3; gameWidth += width) {
            for (int gameposition = 0; gameposition < (width - 3); gameposition++) {
                int result = 0;
                for (int gameheight = 0; gameheight < 4 * (width + 1); gameheight += width + 1) {
                    result += this.gameArray[gameheight + gameposition + gameWidth];
                }
                numbers.add(result);
            }
        }

        //Diagonal
        for (int gameWidth = 0; gameWidth < height - 3; gameWidth += width) {
            for (int gameposition = 0; gameposition < (width - 3); gameposition++) {
                int result = 0;
                for (int gameheight = 3; gameheight < 4 * (width - 1); gameheight += width - 1) {
                    result += this.gameArray[gameheight + gameposition + gameWidth];
                }
                numbers.add(result);
            }
        }

        return numbers;
    }
}
