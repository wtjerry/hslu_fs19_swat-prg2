package Model.Network.RequestHandling;

import Controller.Navigator;
import Model.Network.ProtocolKeywords;
import java.io.DataOutputStream;
import java.io.IOException;

public class InitGameHandler_NEW implements RequestHandler_NEW {

    private final DataOutputStream streamOut;
    private final String opponentHost;
    private final Navigator navigator;

    public InitGameHandler_NEW(DataOutputStream streamOut, String opponentHost, Navigator navigator) {
        this.streamOut = streamOut;
        this.opponentHost = opponentHost;
        this.navigator = navigator;
    }

    @Override
    public void handle() throws IOException {
        this.streamOut.writeBytes(ProtocolKeywords.InitGameAnswer + "\n");
        this.streamOut.flush();

        this.navigator.navigateToGameViewForAcceptingNetworkPlay(this.opponentHost);
    }
}
