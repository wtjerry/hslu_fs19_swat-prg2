package connectfour.model.network.requesthandling;

import connectfour.controller.Navigator;
import connectfour.model.network.ProtocolKeywords;
import connectfour.model.OpponentHasMadeATurnListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class RequestHandlerFactory {

    private Navigator navigator;
    private OpponentHasMadeATurnListener opponentHasMadeATurnListener;

    public RequestHandler createRequestHandler(Socket socket) throws IOException {
        BufferedReader streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());

        String request = streamIn.readLine();

        RequestHandler requestHandler;
        switch (request) {
            case ProtocolKeywords.AVAILABLE_NETWORK_PLAYER_LISTING_REQUEST:
                requestHandler = new NetworkListingHandler(streamOut);
                break;
            case ProtocolKeywords.INIT_GAME_REQUEST:
                InetAddress inetAddress = socket.getInetAddress();
                String hostAddress = inetAddress.getHostAddress();
                requestHandler = new InitGameHandler(streamOut, hostAddress, this.navigator);
                break;
            case ProtocolKeywords.DISK_PLAYED:
                requestHandler = new DiskPlayedHandler(streamIn, this.opponentHasMadeATurnListener);
                break;
            default:
                requestHandler = new DefaultHandler(request);
                break;
        }
        return requestHandler;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
    
    public void setOpponentHasMadeATurnListener(OpponentHasMadeATurnListener opponentHasMadeATurnListener) {
        this.opponentHasMadeATurnListener = opponentHasMadeATurnListener;
    }
}
