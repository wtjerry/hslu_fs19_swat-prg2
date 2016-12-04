package Model.Network.RequestHandling;

import Model.Network.ProtocolKeywords;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkListingHandler implements RequestHandler {

	private final Socket socket;

	NetworkListingHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			DataOutputStream streamOut = new DataOutputStream(this.socket.getOutputStream());
			streamOut.writeBytes(ProtocolKeywords.AvailableNetworkPlayerListingAnswer + "\n");
			streamOut.flush();
			if (!this.socket.isClosed()) {
				this.socket.close();
			}
		} catch (IOException ex) {
			Logger.getLogger(NetworkListingHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
