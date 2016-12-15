package Model;

public abstract class Player {

    private final int diskId;
    private OpponentHasMadeATurnListener opponentHasMadeATurnListener;

    public Player(int diskId) {
        this.diskId = diskId;
    }
    
    public abstract void makeYourTurnNowAsync(int columnOfPreviousDisk);

    public int getDiskId() {
        return this.diskId;
    }
    
    public void setListener(OpponentHasMadeATurnListener opponentHasMadeATurnListeners) {
        this.opponentHasMadeATurnListener = opponentHasMadeATurnListeners;
    }

    protected void notifyOpponentHadMadeATurn(int column) {
        if (this.opponentHasMadeATurnListener != null) {
            this.opponentHasMadeATurnListener.opponentHasMadeATurn(column);
        }
    }
}
