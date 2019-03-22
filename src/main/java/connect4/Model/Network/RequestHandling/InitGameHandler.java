package connect4.Model.Network.RequestHandling;

import connect4.Controller.Navigator;
import connect4.Model.Network.ProtocolKeywords;
import java.io.DataOutputStream;
import java.io.IOException;

public class InitGameHandler implements RequestHandler {

    private final DataOutputStream streamOut;
    private final String opponentHost;
    private final Navigator navigator;

    public InitGameHandler(DataOutputStream streamOut, String opponentHost, Navigator navigator) {
        this.streamOut = streamOut;
        this.opponentHost = opponentHost;
        this.navigator = navigator;
    }

    @Override
    public void handle() throws IOException {
        this.streamOut.writeBytes(ProtocolKeywords.INIT_GAME_ANSWER + "\n");
        this.streamOut.flush();

        this.navigator.navigateToGameViewForAcceptingNetworkPlay(this.opponentHost);
    }
}
