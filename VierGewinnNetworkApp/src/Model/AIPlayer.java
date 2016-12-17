package Model;

import Model.Network.Settings;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AIPlayer extends Player{

    private final ValidTurnChecker validTurnChecker;

    public AIPlayer(ValidTurnChecker validTurnChecker) {
        this.validTurnChecker = validTurnChecker;
    }

    @Override
    public void makeYourTurnNowAsync(int columnOfPreviousTurn) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(AIPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.playDiskAndNotify();
        });
    }

    public void startWithFirstDisk() {
        this.playDiskAndNotify();
    }

    private void playDiskAndNotify() {
        int columnOfMyNextDisk;
        do {
            int maxColumn = Settings.getGameFieldWidth();
            columnOfMyNextDisk = new Random().nextInt(maxColumn);
        } while (this.validTurnChecker.isValidTurn(columnOfMyNextDisk) == false);

        this.notifyOpponentHadMadeATurn(columnOfMyNextDisk);
    }
}
