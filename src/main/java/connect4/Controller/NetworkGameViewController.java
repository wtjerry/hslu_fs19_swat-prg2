package connect4.Controller;

import connect4.Model.Game;
import connect4.Model.GameField;
import connect4.Model.GameState;
import connect4.Model.Network.RequestHandling.NetworkRequestManager;
import connect4.Model.NetworkPlayer;
import connect4.Model.Player;
import connect4.Views.Interfaces.GameView;

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
    public void SaveGamePressed() {
        throw new UnsupportedOperationException("Saving a network game is not supported.");
    }

    @Override
    public void initForResumeGame() {
        throw new UnsupportedOperationException("Resuming a network game is not supported.."); 
    }
}
