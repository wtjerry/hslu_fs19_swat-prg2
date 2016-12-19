package Controller;

import Model.Game;
import Model.GameField;
import Model.GameState;
import Model.Network.RequestHandling.NetworkRequestManager;
import Model.NetworkPlayer;
import Model.Player;
import Views.Interfaces.GameView;

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

    @Override
    public void CloseGamePressed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
