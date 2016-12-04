package Model.Network.RequestHandling;

import Model.Network.NetworkPlayerSearcher;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultHandler implements RequestHandler {

	private final String request;
	private final Socket socket;

	DefaultHandler(String request, Socket socket) {
		this.request = request;
		this.socket = socket;
	}

	@Override
	public void run() {
		Logger.getLogger(NetworkPlayerSearcher.class.getName()).log(Level.WARNING, "Received unknown request: {0}", this.request);
		if (!this.socket.isClosed()) {
			try {
				this.socket.close();
			} catch (IOException ex) {
				Logger.getLogger(DefaultHandler.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}	
}
