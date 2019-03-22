package connectfour.model.network.requesthandling;

import connectfour.model.network.ProtocolKeywords;
import java.io.DataOutputStream;
import java.io.IOException;

public class NetworkListingHandler implements RequestHandler {

    private final DataOutputStream streamOut;

    NetworkListingHandler(DataOutputStream streamOut) {
        this.streamOut = streamOut;
    }

    @Override
    public void handle() throws IOException {
        this.streamOut.writeBytes(ProtocolKeywords.AVAILABLE_NETWORK_PLAYER_LISTING_ANSWER + "\n");
        this.streamOut.flush();
    }
}
