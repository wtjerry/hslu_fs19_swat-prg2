package Controller;

import Model.AIPlayer;
import Model.Game;
import Views.Interfaces.GameView;

public class LocalGameViewController extends GameViewController {

    public LocalGameViewController(GameView view, Navigator navigator) {
        super(view, navigator);
    }

    @Override
    void init() {
        AIPlayer opponent = new AIPlayer();
        this.game = new Game(opponent);
        this.game.setListener(this);
        opponent.setListener(this.game);
        opponent.startWithFirstDisk();
    }
}
