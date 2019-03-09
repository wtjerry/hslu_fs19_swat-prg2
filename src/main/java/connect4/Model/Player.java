package connect4.Model;

public abstract class Player {

    private OpponentHasMadeATurnListener opponentHasMadeATurnListener;

    public abstract void makeYourTurnNowAsync(int columnOfPreviousDisk);

    public void setListener(OpponentHasMadeATurnListener opponentHasMadeATurnListeners) {
        this.opponentHasMadeATurnListener = opponentHasMadeATurnListeners;
    }

    void notifyOpponentHadMadeATurn(int column) {
        if (this.opponentHasMadeATurnListener != null) {
            this.opponentHasMadeATurnListener.opponentHasMadeATurn(column);
        }
    }
}
