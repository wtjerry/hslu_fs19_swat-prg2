package Controller;

import Views.Interfaces.NetworkView;
import Views.Interfaces.NetworkViewListener;

public class NetworkViewController implements NetworkViewListener{

    private final NetworkView view;
    private final Navigator navigator;

    NetworkViewController(NetworkView view, Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    void init() {
    }

    @Override
    public void BackPressed() {
        this.navigator.navigateToStartView();
    }

    @Override
    public void StartGamePressed(String ipAddress) {
        this.navigator.navigateToGameViewForNetworkPlay(ipAddress);
    }
}
