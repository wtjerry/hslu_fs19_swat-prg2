package Model.Network.RequestHandling;

import Model.OpponentPlayedDiskListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiskPlayedHandler implements RequestHandler {

	private final Socket socket;
	private final OpponentPlayedDiskListener opponentPlayedDiskListener;

	DiskPlayedHandler(Socket socket, OpponentPlayedDiskListener opponentPlayedDiskListener) {
		this.socket = socket;
		this.opponentPlayedDiskListener = opponentPlayedDiskListener;
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
			
			if (this.opponentPlayedDiskListener != null) {
				this.opponentPlayedDiskListener.opponentPlayedDisk(row);
			}
		} catch (IOException ex) {
			Logger.getLogger(NetworkListingHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
