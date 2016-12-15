package Controller;

import Model.AIPlayer;
import Model.Game;
import Model.PrimeNumberProvider;
import Views.Interfaces.GameView;

public class LocalGameViewController extends GameViewController {

    public LocalGameViewController(GameView view, Navigator navigator) {
        super(view, navigator);
    }

    @Override
    void init() {
        int myDiskId = PrimeNumberProvider.nextPrimeNumber();
        int opponentDiskId = PrimeNumberProvider.nextPrimeNumber();
        AIPlayer opponent = new AIPlayer(opponentDiskId);
        this.game = new Game(myDiskId, opponent);
        this.game.setListener(this);
        opponent.setListener(this.game);
        opponent.startWithFirstDisk();
    }
}
