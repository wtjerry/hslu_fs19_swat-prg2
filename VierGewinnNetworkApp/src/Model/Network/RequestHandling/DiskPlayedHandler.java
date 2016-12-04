package Model.Network.RequestHandling;

import Model.Network.ProtocolKeywords;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiskPlayedHandler implements RequestHandler {

	private final Socket socket;

	public DiskPlayedHandler(Socket socket) {
		//todo jeremy: notify controller to change state / views etc.
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader streamIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			int row = Integer.parseInt(streamIn.readLine());
			Logger.getLogger(DefaultHandler.class.getName()).log(Level.INFO, "Opponent played disk in row: {0}:", row);
			if (!this.socket.isClosed()) {
				this.socket.close();
			}
		} catch (IOException ex) {
			Logger.getLogger(NetworkListingHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
