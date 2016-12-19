package Model;

import Model.Network.Settings;

public class GameField implements ValidTurnChecker {

    final int FreeField = 0;
    final int NumberOfStonesToWin = 4;

    private final int height;
    private final int width;
    private final int myDiskId;
    private final int opponentDiskId;

    // example of a array with height (rows, r) 4 and width (columns, c) 6:
    // { c0r0, c1r0, c2r0, c3r0, c4r0, c5r0, c0r1, c1r1, c2r1 ... c2r3, c3r3, c4r3, c5r3 }
    // to get the index of, for example, c2r1 we can do 2 +1*6 (c+r*width) = 8 
    private final int[] gameArray;

    public GameField(){
        this.width = Settings.getGameFieldWidth();
        this.height = Settings.getGameFieldHeight();
        this.gameArray = new int[this.width * this.height];

        this.myDiskId = PrimeNumberProvider.nextPrimeNumber();
        this.opponentDiskId = PrimeNumberProvider.nextPrimeNumber();        
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
                if (this.gameArray[index] == FreeField) {
                    this.gameArray[index] = diskId;
                    return new DiskPosition(column, indexOfCurrentHeight);
                }
            }
        }

        return DiskPosition.getInvalidDiskPosition();
    }
    
    public WinState checkIfSomebodyWon() {
        //starting bottom left corner. left to right and bottom to top.
        for (int row = 0; row < this.height; row++) {
            for (int column = 0; column < this.width; column++) {
                
                WinState xDisksInARowToTheRightWinResult = this.hasSomebodyXDisksInARowToTheRight(column, row);
                if (xDisksInARowToTheRightWinResult != WinState.NobodyWon) {
                    return xDisksInARowToTheRightWinResult;
                }
                
                WinState xDisksInARowToTheTopWinResult = this.hasSomebodyXDisksInARowToTheTop(column, row);
                if (xDisksInARowToTheTopWinResult != WinState.NobodyWon) {
                    return xDisksInARowToTheTopWinResult;
                }
                
                WinState xDisksInARowToTheTopRightWinResult = this.hasSomebodyXDisksInARowToTheTopRight(column, row);
                if (xDisksInARowToTheTopRightWinResult != WinState.NobodyWon) {
                    return xDisksInARowToTheTopRightWinResult;
                }
                
                WinState xDisksInARowToTheTopLeftWinResult = this.hasSomebodyXDisksInARowToTheTopLeft(column, row);
                if (xDisksInARowToTheTopLeftWinResult != WinState.NobodyWon) {
                    return xDisksInARowToTheTopLeftWinResult;
                }
            }
        }

        return WinState.NobodyWon;
    }

    @Override
    public boolean isValidTurn(int column) {
        int gameFieldWidth = Settings.getGameFieldWidth();
        int topRowIndex = Settings.getGameFieldHeight() - 1;
        int gameFieldIndex = column + topRowIndex * width;
        boolean isStillFree = this.gameArray[gameFieldIndex] == FreeField;
        return isStillFree;
    }

    private WinState hasSomebodyXDisksInARowToTheRight(int column, int row) {
        if (this.diskHasEnoughNeighboursToTheRightToPotentiallyHave4InARow(column)) {
            int sumOfNext4DiskIdsToTheRight = 0;
            for (int currentXOffsetToTheRight = 0; currentXOffsetToTheRight < NumberOfStonesToWin; currentXOffsetToTheRight++) {
                final int index = (column + currentXOffsetToTheRight) + (row * this.width);
                sumOfNext4DiskIdsToTheRight += this.gameArray[index];
            }
            return this.areThoseXDisksOfTheSamePlayer(sumOfNext4DiskIdsToTheRight);
        }

        return WinState.NobodyWon;
    }
    
    private boolean diskHasEnoughNeighboursToTheRightToPotentiallyHave4InARow(int column) {
        return (column + NumberOfStonesToWin) <= this.width;
    }

    private WinState hasSomebodyXDisksInARowToTheTop(int column, int row) {
        if (this.diskHasEnoughNeighboursToTheTopToPotentiallyHave4InARow(row)) {
            int sumOfNext4DiskIdsToTheTop = 0;
            for (int currentYOffsetToTheTop = 0; currentYOffsetToTheTop < NumberOfStonesToWin; currentYOffsetToTheTop++) {
                final int index = column + ((row + currentYOffsetToTheTop) * this.width);
                sumOfNext4DiskIdsToTheTop += this.gameArray[index];
            }
            return this.areThoseXDisksOfTheSamePlayer(sumOfNext4DiskIdsToTheTop);
        }

        return WinState.NobodyWon;
    }
    
    private boolean diskHasEnoughNeighboursToTheTopToPotentiallyHave4InARow(int row) {
        return (row + NumberOfStonesToWin) <= this.height;
    }

    private WinState hasSomebodyXDisksInARowToTheTopRight(int column, int row) {
        if (this.diskHasEnoughNeighboursToTheTopRightToPotentiallyHave4InARow(column, row)) {
            int sumOfNext4DiskIdsToTheTopRight = 0;
            for (int currentXYOffsetToTheTopRight = 0; currentXYOffsetToTheTopRight < NumberOfStonesToWin; currentXYOffsetToTheTopRight++) {
                final int index = (column + currentXYOffsetToTheTopRight) + ((row + currentXYOffsetToTheTopRight) * this.width);
                sumOfNext4DiskIdsToTheTopRight += this.gameArray[index];
            }
            return this.areThoseXDisksOfTheSamePlayer(sumOfNext4DiskIdsToTheTopRight);
        }

        return WinState.NobodyWon;
    }
    
    private boolean diskHasEnoughNeighboursToTheTopRightToPotentiallyHave4InARow(int column, int row) {
        return this.diskHasEnoughNeighboursToTheRightToPotentiallyHave4InARow(column) 
                && this.diskHasEnoughNeighboursToTheTopToPotentiallyHave4InARow(row);
    }

    private WinState hasSomebodyXDisksInARowToTheTopLeft(int column, int row) {
        if (this.diskHasEnoughNeighboursToTheTopLeftToPotentiallyHave4InARow(column, row)) {
            int sumOfNext4DiskIdsToTheTopLeft = 0;
            for (int currentXYOffsetToTheTopLeft = 0; currentXYOffsetToTheTopLeft < NumberOfStonesToWin; currentXYOffsetToTheTopLeft++) {
                final int index = (column - currentXYOffsetToTheTopLeft) + ((row + currentXYOffsetToTheTopLeft) * this.width);
                sumOfNext4DiskIdsToTheTopLeft += this.gameArray[index];
            }
            return this.areThoseXDisksOfTheSamePlayer(sumOfNext4DiskIdsToTheTopLeft);
        }
        
        return WinState.NobodyWon;
    }
    
    private boolean diskHasEnoughNeighboursToTheTopLeftToPotentiallyHave4InARow(int column, int row) {
        return (column + 1 - NumberOfStonesToWin) >= 0
                && this.diskHasEnoughNeighboursToTheTopToPotentiallyHave4InARow(row);
    }
    
    private WinState areThoseXDisksOfTheSamePlayer(int sum) {
        WinState result = WinState.NobodyWon;
        if (sum == this.myDiskId * NumberOfStonesToWin) {
            result = WinState.IWon;
        }
        if (sum == this.opponentDiskId * NumberOfStonesToWin) {
            result = WinState.OpponentWon;
        }

        return result;
    }
}
