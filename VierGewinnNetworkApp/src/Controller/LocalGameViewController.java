package Controller;

import Model.AIPlayer;
import Model.Game;
import Model.GameField;
import Model.GameState;
import Views.Interfaces.GameView;

public class LocalGameViewController extends GameViewController {

    public LocalGameViewController(GameView view, Navigator navigator) {
        super(view, navigator);
    }

    @Override
    void init(GameState startGameState) {
        GameField gameField = new GameField();
        AIPlayer opponent = new AIPlayer(gameField);
        this.game = new Game(gameField, opponent, startGameState);
        this.game.setListener(this);
        opponent.setListener(this.game);
        opponent.startWithFirstDisk();
    }
}
