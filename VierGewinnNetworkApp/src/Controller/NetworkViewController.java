package Controller;

import Model.Network.NetworkPlayerSearcher;
import Model.Network.NewPlayersFoundListener;
import Views.Interfaces.NetworkView;
import Views.Interfaces.NetworkViewListener;
import java.util.List;

public class NetworkViewController implements NetworkViewListener, NewPlayersFoundListener{

    private final NetworkView view;
    private final Navigator navigator;
    private final NetworkPlayerSearcher networkPlayerSearcher;

    NetworkViewController(NetworkView view, Navigator navigator, NetworkPlayerSearcher networkPlayerSearcher) {
        this.view = view;
        this.navigator = navigator;
        this.networkPlayerSearcher = networkPlayerSearcher;
    }

    void init() {
        this.networkPlayerSearcher.setListener(this);
        this.networkPlayerSearcher.startSearching();
    }

    @Override
    public void BackPressed() {
        this.networkPlayerSearcher.stopSearching();
        this.navigator.navigateToStartView();
    }

    @Override
    public void StartGamePressed(String ipAddress) {
        this.networkPlayerSearcher.stopSearching();
        this.navigator.navigateToGameViewForNetworkPlay(ipAddress);
    }

    @Override
    public void NewPlayersFound(List<String> newPlayers) {
        this.view.showAvailablePlayers(newPlayers);
    }
}
