package Controller;

import Model.Game;
import Model.Network.RequestHandling.NetworkRequestManager;
import Model.NetworkPlayer;
import Model.Player;
import Model.PrimeNumberProvider;
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
        int myDiskId = PrimeNumberProvider.nextPrimeNumber();
        int opponentDiskId = PrimeNumberProvider.nextPrimeNumber();
        Player opponent = new NetworkPlayer(opponentDiskId, this.ipAddress);
        this.game = new Game(myDiskId, opponent);
        this.game.setListener(this);
        this.networkRequestManager.setOpponentHasMadeATurnListener(this.game);
    }
}
