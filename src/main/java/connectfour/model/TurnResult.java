package connectfour.model;

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
