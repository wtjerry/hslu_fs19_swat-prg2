package Model.Network.RequestHandling;

import Model.Network.ProtocolKeywords;
import java.io.DataOutputStream;
import java.io.IOException;

public class NetworkListingHandler_NEW implements RequestHandler_NEW {

    private final DataOutputStream streamOut;

    NetworkListingHandler_NEW(DataOutputStream streamOut) {
        this.streamOut = streamOut;
    }

    @Override
    public void handle() throws IOException {
        this.streamOut.writeBytes(ProtocolKeywords.AvailableNetworkPlayerListingAnswer + "\n");
        this.streamOut.flush();
    }
}
