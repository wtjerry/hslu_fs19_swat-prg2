package connectfour.controller;

import connectfour.model.network.NetworkPlayerSearcher;
import connectfour.model.network.NewPlayersFoundListener;
import connectfour.model.network.ProtocolKeywords;
import connectfour.model.network.Settings;
import connectfour.views.interfaces.NetworkView;
import connectfour.views.interfaces.NetworkViewListener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

public class NetworkViewController implements NetworkViewListener, NewPlayersFoundListener {

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
    public void backPressed() {
        this.networkPlayerSearcher.stopSearching();
        this.navigator.navigateToStartView();
    }

    @Override
    public void startGamePressed(String ipAddress) {
        boolean potentialOpponentWantToPlay = askPotentialOpponentToPlay(ipAddress);
        if (potentialOpponentWantToPlay) {
            this.networkPlayerSearcher.stopSearching();
            this.navigator.navigateToGameViewForInitializingNetworkPlay(ipAddress);
        }
    }

    private boolean askPotentialOpponentToPlay(String ipAddress) {
        boolean potentialOpponentWantToPlay = false;
        try {
            int port = Settings.getPort();
            try (Socket hostSocket = new Socket(ipAddress, port)) {
                DataOutputStream streamToHost = new DataOutputStream(hostSocket.getOutputStream());
                BufferedReader streamFromHost = new BufferedReader(new InputStreamReader(hostSocket.getInputStream()));
                streamToHost.writeBytes(ProtocolKeywords.INIT_GAME_REQUEST + "\n");
                streamToHost.flush();
                String response = streamFromHost.readLine();
                if (ProtocolKeywords.INIT_GAME_ANSWER.equals(response)) {
                    potentialOpponentWantToPlay = true;
                }
            }
        } catch (IOException ignored) {
        }
        
        return potentialOpponentWantToPlay;
    }

    @Override
    public void newPlayersFound(List<String> newPlayers) {
        this.view.showAvailablePlayers(newPlayers);
    }
}
