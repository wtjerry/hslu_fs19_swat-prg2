package connectfour.model;

import connectfour.model.network.Settings;

public class GameField implements ValidTurnChecker {

    private static final int FREE_FIELD = 0;
    private static final int NUMBER_OF_STONES_TO_WIN = 4;

    private final int height;
    private final int width;
    private final int myDiskId;
    private final int opponentDiskId;

    /*
    example of a array with height (rows, r) 4 and width (columns, c) 6:
    { c0r0, c1r0, c2r0, c3r0, c4r0, c5r0, c0r1, c1r1, c2r1 ... c2r3, c3r3, c4r3, c5r3 }
    to get the index of, for example, c2r1 we can do 2 +1*6 (c+r*width) = 8
    */
    private final int[] gameArray;

    public GameField(){
        this.width = Settings.getGameFieldWidth();
        this.height = Settings.getGameFieldHeight();
        this.gameArray = new int[this.width * this.height];

        this.myDiskId = PrimeNumberProvider.nextPrimeNumber();
        this.opponentDiskId = PrimeNumberProvider.nextPrimeNumber();        
    }

    DiskPosition setMyDisk(int column) {
        return addDiskToArray(column, this.myDiskId);
    }

    DiskPosition setOpponentsDisk(int column) {
        return addDiskToArray(column, this.opponentDiskId);
    }

    private DiskPosition addDiskToArray(int column, int diskId) {
        if (column <= this.width) {
            for (int indexOfCurrentHeight = 0; indexOfCurrentHeight < this.height; indexOfCurrentHeight++) {
                int index = column + indexOfCurrentHeight * this.width;
                if (this.gameArray[index] == FREE_FIELD) {
                    this.gameArray[index] = diskId;
                    return new DiskPosition(column, indexOfCurrentHeight);
                }
            }
        }

        return DiskPosition.getInvalidDiskPosition();
    }
    
    WinState checkIfSomebodyWon() {
        //starting bottom left corner. left to right and bottom to top.
        for (int row = 0; row < this.height; row++) {
            for (int column = 0; column < this.width; column++) {
                
                WinState xDisksInARowToTheRightWinResult = this.hasSomebodyXDisksInARowToTheRight(column, row);
                if (xDisksInARowToTheRightWinResult != WinState.NOBODY_WON) {
                    return xDisksInARowToTheRightWinResult;
                }
                
                WinState xDisksInARowToTheTopWinResult = this.hasSomebodyXDisksInARowToTheTop(column, row);
                if (xDisksInARowToTheTopWinResult != WinState.NOBODY_WON) {
                    return xDisksInARowToTheTopWinResult;
                }
                
                WinState xDisksInARowToTheTopRightWinResult = this.hasSomebodyXDisksInARowToTheTopRight(column, row);
                if (xDisksInARowToTheTopRightWinResult != WinState.NOBODY_WON) {
                    return xDisksInARowToTheTopRightWinResult;
                }
                
                WinState xDisksInARowToTheTopLeftWinResult = this.hasSomebodyXDisksInARowToTheTopLeft(column, row);
                if (xDisksInARowToTheTopLeftWinResult != WinState.NOBODY_WON) {
                    return xDisksInARowToTheTopLeftWinResult;
                }
            }
        }

        return WinState.NOBODY_WON;
    }

    @Override
    public boolean isValidTurn(int column) {
        int topRowIndex = this.height - 1;
        int gameFieldIndex = column + topRowIndex * width;
        return this.gameArray[gameFieldIndex] == FREE_FIELD;
    }

    private WinState hasSomebodyXDisksInARowToTheRight(int column, int row) {
        if (this.diskHasEnoughNeighboursToTheRightToPotentiallyHave4InARow(column)) {
            int sumOfNext4DiskIdsToTheRight = 0;
            for (int currentXOffsetToTheRight = 0; currentXOffsetToTheRight < NUMBER_OF_STONES_TO_WIN; currentXOffsetToTheRight++) {
                final int index = (column + currentXOffsetToTheRight) + (row * this.width);
                sumOfNext4DiskIdsToTheRight += this.gameArray[index];
            }
            return this.areThoseXDisksOfTheSamePlayer(sumOfNext4DiskIdsToTheRight);
        }

        return WinState.NOBODY_WON;
    }
    
    private boolean diskHasEnoughNeighboursToTheRightToPotentiallyHave4InARow(int column) {
        return (column + NUMBER_OF_STONES_TO_WIN) <= this.width;
    }

    private WinState hasSomebodyXDisksInARowToTheTop(int column, int row) {
        if (this.diskHasEnoughNeighboursToTheTopToPotentiallyHave4InARow(row)) {
            int sumOfNext4DiskIdsToTheTop = 0;
            for (int currentYOffsetToTheTop = 0; currentYOffsetToTheTop < NUMBER_OF_STONES_TO_WIN; currentYOffsetToTheTop++) {
                final int index = column + ((row + currentYOffsetToTheTop) * this.width);
                sumOfNext4DiskIdsToTheTop += this.gameArray[index];
            }
            return this.areThoseXDisksOfTheSamePlayer(sumOfNext4DiskIdsToTheTop);
        }

        return WinState.NOBODY_WON;
    }
    
    private boolean diskHasEnoughNeighboursToTheTopToPotentiallyHave4InARow(int row) {
        return (row + NUMBER_OF_STONES_TO_WIN) <= this.height;
    }

    private WinState hasSomebodyXDisksInARowToTheTopRight(int column, int row) {
        if (this.diskHasEnoughNeighboursToTheTopRightToPotentiallyHave4InARow(column, row)) {
            int sumOfNext4DiskIdsToTheTopRight = 0;
            for (int currentXYOffsetToTheTopRight = 0; currentXYOffsetToTheTopRight < NUMBER_OF_STONES_TO_WIN; currentXYOffsetToTheTopRight++) {
                final int index = (column + currentXYOffsetToTheTopRight) + ((row + currentXYOffsetToTheTopRight) * this.width);
                sumOfNext4DiskIdsToTheTopRight += this.gameArray[index];
            }
            return this.areThoseXDisksOfTheSamePlayer(sumOfNext4DiskIdsToTheTopRight);
        }

        return WinState.NOBODY_WON;
    }
    
    private boolean diskHasEnoughNeighboursToTheTopRightToPotentiallyHave4InARow(int column, int row) {
        return this.diskHasEnoughNeighboursToTheRightToPotentiallyHave4InARow(column) 
                && this.diskHasEnoughNeighboursToTheTopToPotentiallyHave4InARow(row);
    }

    private WinState hasSomebodyXDisksInARowToTheTopLeft(int column, int row) {
        if (this.diskHasEnoughNeighboursToTheTopLeftToPotentiallyHave4InARow(column, row)) {
            int sumOfNext4DiskIdsToTheTopLeft = 0;
            for (int currentXYOffsetToTheTopLeft = 0; currentXYOffsetToTheTopLeft < NUMBER_OF_STONES_TO_WIN; currentXYOffsetToTheTopLeft++) {
                final int index = (column - currentXYOffsetToTheTopLeft) + ((row + currentXYOffsetToTheTopLeft) * this.width);
                sumOfNext4DiskIdsToTheTopLeft += this.gameArray[index];
            }
            return this.areThoseXDisksOfTheSamePlayer(sumOfNext4DiskIdsToTheTopLeft);
        }
        
        return WinState.NOBODY_WON;
    }
    
    private boolean diskHasEnoughNeighboursToTheTopLeftToPotentiallyHave4InARow(int column, int row) {
        return (column + 1 - NUMBER_OF_STONES_TO_WIN) >= 0
                && this.diskHasEnoughNeighboursToTheTopToPotentiallyHave4InARow(row);
    }
    
    private WinState areThoseXDisksOfTheSamePlayer(int sum) {
        WinState result = WinState.NOBODY_WON;
        if (sum == this.myDiskId * NUMBER_OF_STONES_TO_WIN) {
            result = WinState.I_WON;
        }
        if (sum == this.opponentDiskId * NUMBER_OF_STONES_TO_WIN) {
            result = WinState.OPPONENT_WON;
        }

        return result;
    }
}
