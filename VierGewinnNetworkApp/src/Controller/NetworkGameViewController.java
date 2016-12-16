package Controller;

import Model.Game;
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
        Player opponent = new NetworkPlayer(this.ipAddress);
        this.game = new Game(opponent, startGameState);
        this.game.setListener(this);
        this.networkRequestManager.setOpponentHasMadeATurnListener(this.game);
    }
}
