package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player_NEW {

    private final List<OpponentPlayedDiskListener> diskplayedListeners;

    public Player_NEW() {
        this.diskplayedListeners = new ArrayList<>();
    }

    public abstract void opponentPlayedDisk(int row);

    public void addListener(OpponentPlayedDiskListener diskPlayedListener) {
        this.diskplayedListeners.add(diskPlayedListener);
    }

    protected void notifyDiskPlayedListeners(int row) {
        this.diskplayedListeners.forEach(x -> x.opponentPlayedDisk(row));
    }
}
