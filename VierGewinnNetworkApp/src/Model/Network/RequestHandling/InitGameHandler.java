package Model.Network.RequestHandling;

import Model.Network.ProtocolKeywords;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitGameHandler implements RequestHandler {

	private final Socket socket;

	public InitGameHandler(Socket socket) {
		//todo jeremy: notify controller to change state / views etc.
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			DataOutputStream streamOut = new DataOutputStream(this.socket.getOutputStream());
			streamOut.writeBytes(ProtocolKeywords.InitGameAnswer + "\n");
			streamOut.flush();
			if (!this.socket.isClosed()) {
				this.socket.close();
			}
		} catch (IOException ex) {
			Logger.getLogger(InitGameHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
