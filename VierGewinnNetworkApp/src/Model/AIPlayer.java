package Model;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AIPlayer extends Player_NEW {

    @Override
    public void makeYourTurnNowAsync(int columnOfPreviousTurn) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game_NEW.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.playDiskAndNotify();
        });
    }

    public void startWithFirstDisk() {
        this.playDiskAndNotify();
    }
    
    private void playDiskAndNotify(){
        //todo determine where to play my next disk. May be done as easy as just choosing a random column
        int columnOfMyNextDisk = 1;
        this.notifyOpponentHadMadeATurn(columnOfMyNextDisk);
    }
}
