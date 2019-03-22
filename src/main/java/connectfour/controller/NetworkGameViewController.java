package connectfour.controller;

import connectfour.model.Game;
import connectfour.model.GameField;
import connectfour.model.GameState;
import connectfour.model.network.requesthandling.NetworkRequestManager;
import connectfour.model.NetworkPlayer;
import connectfour.model.Player;
import connectfour.views.interfaces.GameView;

public class NetworkGameViewController extends GameViewController{

    private final NetworkRequestManager networkRequestManager;
    private final String ipAddress;

    public NetworkGameViewController(GameView view, Navigator navigator, NetworkRequestManager networkRequestManager, String ipAddress) {
        super(view, navigator);
        this.networkRequestManager = networkRequestManager;
        this.ipAddress = ipAddress;
    }

    @Override
    void init(GameState startGameState) {
        GameField gameField = new GameField();
        Player opponent = new NetworkPlayer(this.ipAddress);
        this.game = new Game(gameField, opponent, startGameState);
        this.game.setListener(this);
        this.networkRequestManager.setOpponentHasMadeATurnListener(this.game);
    }

    @Override
    public void saveGamePressed() {
        throw new UnsupportedOperationException("Saving a network game is not supported.");
    }

    @Override
    public void initForResumeGame() {
        throw new UnsupportedOperationException("Resuming a network game is not supported.."); 
    }
}
