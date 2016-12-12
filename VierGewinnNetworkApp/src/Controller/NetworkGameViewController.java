package Controller;

import Model.Game_NEW;
import Model.Network.RequestHandling.NetworkRequestManager;
import Model.NetworkPlayer;
import Model.Player_NEW;
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
    void init() {
        Player_NEW opponent = new NetworkPlayer(this.ipAddress);
        this.game = new Game_NEW(opponent);
        this.game.addListener(this);
        this.networkRequestManager.setOpponentPlayedDiskListener(this);
    }
}
