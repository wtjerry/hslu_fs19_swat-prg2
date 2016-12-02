package Model.Network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkPlayerSearchResponder implements Runnable {

	private final int port;
	private boolean continueResponding;

	public NetworkPlayerSearchResponder(int port) {
		this.port = port;
		this.continueResponding = true;
	}	
	
	@Override
	public void run() {
		try {
		ServerSocket localSocket = new ServerSocket(this.port);
		while (this.continueResponding) {
			try (Socket connectionSocket = localSocket.accept()) {
				BufferedReader streamIn
						= new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream streamOut = new DataOutputStream(connectionSocket.getOutputStream());
				String request = streamIn.readLine();
				if ("I want to play a game.".equals(request)) {					
					streamOut.writeBytes("Oh shit...\n");
					streamOut.flush();
				}
			}
		}
		} catch (IOException ex) {
			Logger.getLogger(NetworkPlayerSearchResponder.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void stopResponding() {
		this.continueResponding = false;
	}
}
