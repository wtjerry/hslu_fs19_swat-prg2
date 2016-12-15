package Model;

public class AIPlayer extends Player_NEW {

    @Override
    public void opponentPlayedDisk(int row) {
        this.playDiskAndNotify();
    }

    public void startWithFirstDisk() {
        this.playDiskAndNotify();
    }
    
    private void playDiskAndNotify(){
        //todo determine where to play my next disk. May be done as easy as just choosing a random column
        int rowOfMyNextDisk = 1;
        this.notifyDiskPlayedListeners(rowOfMyNextDisk);
    }
}
