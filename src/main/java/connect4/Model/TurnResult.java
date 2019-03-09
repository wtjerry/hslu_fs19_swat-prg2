package connect4.Model;

public class TurnResult {

    private final WinState winState;
    private final DiskPosition diskPosition;

    public TurnResult(WinState winState, DiskPosition diskPosition) {
        this.winState = winState;
        this.diskPosition = diskPosition;
    }

    public WinState getWinState() {
        return winState;
    }

    public DiskPosition getDiskPosition() {
        return diskPosition;
    }
}
