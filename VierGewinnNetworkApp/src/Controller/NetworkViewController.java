package Controller;

import Model.Network.NetworkPlayerSearcher;
import Model.Network.NewPlayersFoundListener;
import Model.Network.ProtocolKeywords;
import Model.Network.Settings;
import Views.Interfaces.NetworkView;
import Views.Interfaces.NetworkViewListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
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
        boolean potentialOpponentWantToPlay = AskPotentialOpponentToPlay(ipAddress);
        if (potentialOpponentWantToPlay) {
            this.networkPlayerSearcher.stopSearching();
            this.navigator.navigateToGameViewForInitializingNetworkPlay(ipAddress);
        }
    }

    private boolean AskPotentialOpponentToPlay(String ipAddress) {
        boolean potentialOpponentWantToPlay = false;
        try {
            int port = Settings.getPort();
            try (Socket hostSocket = new Socket(ipAddress, port)) {
                DataOutputStream streamToHost = new DataOutputStream(hostSocket.getOutputStream());
                BufferedReader streamFromHost = new BufferedReader(new InputStreamReader(hostSocket.getInputStream()));
                streamToHost.writeBytes(ProtocolKeywords.InitGameRequest + "\n");
                streamToHost.flush();
                String response = streamFromHost.readLine();
                if (ProtocolKeywords.InitGameAnswer.equals(response)) {
                    potentialOpponentWantToPlay = true;
                }
            }
        } catch (IOException ex) {
        }
        
        return potentialOpponentWantToPlay;
    }

    @Override
    public void NewPlayersFound(List<String> newPlayers) {
        this.view.showAvailablePlayers(newPlayers);
    }
}
