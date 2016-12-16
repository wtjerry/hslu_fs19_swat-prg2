package Controller;

import Model.AIPlayer;
import Model.Game;
import Model.GameState;
import Views.Interfaces.GameView;

public class LocalGameViewController extends GameViewController {

    public LocalGameViewController(GameView view, Navigator navigator) {
        super(view, navigator);
    }

    @Override
    void init(GameState startGameState) {
        AIPlayer opponent = new AIPlayer();
        this.game = new Game(opponent, startGameState);
        this.game.setListener(this);
        opponent.setListener(this.game);
        opponent.startWithFirstDisk();
    }
}
