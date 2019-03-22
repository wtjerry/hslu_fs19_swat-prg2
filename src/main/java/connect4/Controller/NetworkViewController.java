package connect4.Controller;

import connect4.Model.Network.NetworkPlayerSearcher;
import connect4.Model.Network.NewPlayersFoundListener;
import connect4.Model.Network.ProtocolKeywords;
import connect4.Model.Network.Settings;
import connect4.Views.Interfaces.NetworkView;
import connect4.Views.Interfaces.NetworkViewListener;

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
