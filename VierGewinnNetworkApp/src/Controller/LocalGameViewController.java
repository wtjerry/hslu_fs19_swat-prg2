package Controller;

import Model.AIPlayer;
import Model.Game_NEW;
import Model.Player_NEW;
import Views.Interfaces.GameView;

public class LocalGameViewController extends GameViewController {

    public LocalGameViewController(GameView view, Navigator navigator) {
        super(view, navigator);
    }

    @Override
    void init() {
        Player_NEW opponent = new AIPlayer();
        this.game = new Game_NEW(opponent);
        this.game.addListener(this);
        opponent.addListener(this.game);
    }
}
