package connect4.Model;

import java.io.Serializable;

class PlayedTurn implements Serializable{
    private final boolean wasMyTurn;
    private final int column;

    public PlayedTurn(boolean wasMyTurn, int column) {
        this.wasMyTurn = wasMyTurn;
        this.column = column;
    }

    public boolean wasMyTurn() {
        return wasMyTurn;
    }

    public int getColumn() {
        return column;
    }    
}
