package Model.Network.RequestHandling;

import Model.Network.ProtocolKeywords;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkListingHandler implements RequestHandler {

	private final Socket connectionSocket;

	NetworkListingHandler(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
	}

	@Override
	public void run() {
		try {
			DataOutputStream streamOut = new DataOutputStream(connectionSocket.getOutputStream());
			streamOut.writeBytes(ProtocolKeywords.AvailableNetworkPlayerListingAnswer + "\n");
			streamOut.flush();
		} catch (IOException ex) {
			Logger.getLogger(NetworkListingHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
