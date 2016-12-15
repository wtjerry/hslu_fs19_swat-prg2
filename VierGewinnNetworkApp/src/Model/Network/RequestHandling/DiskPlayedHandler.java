package Model.Network.RequestHandling;

import Model.OpponentHasMadeATurnListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiskPlayedHandler implements RequestHandler {

	private final Socket socket;
	private final OpponentHasMadeATurnListener opponentHasMadeATurnListener;

	DiskPlayedHandler(Socket socket, OpponentHasMadeATurnListener opponentHasMadeATurnListener) {
		this.socket = socket;
		this.opponentHasMadeATurnListener = opponentHasMadeATurnListener;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader streamIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			int column = Integer.parseInt(streamIn.readLine());
			Logger.getLogger(DiskPlayedHandler.class.getName()).log(Level.INFO, "Opponent played disk in column: {0}:", column);
			if (!this.socket.isClosed()) {
				this.socket.close();
			}
			
			if (this.opponentHasMadeATurnListener != null) {
				this.opponentHasMadeATurnListener.opponentHasMadeATurn(column);
			}
		} catch (IOException ex) {
			Logger.getLogger(DiskPlayedHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
