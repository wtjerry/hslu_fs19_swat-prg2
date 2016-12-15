package Model;

public abstract class Player_NEW {

    private OpponentHasMadeATurnListener opponentHasMadeATurnListener;

    public abstract void makeYourTurnNowAsync(int rowOfPreviousDisk);

    public void setListener(OpponentHasMadeATurnListener opponentHasMadeATurnListeners) {
        this.opponentHasMadeATurnListener = opponentHasMadeATurnListeners;
    }

    protected void notifyDiskPlayedListeners(int row) {
        if (this.opponentHasMadeATurnListener != null) {
            this.opponentHasMadeATurnListener.opponentHasMadeATurn(row);
        }
    }
}
