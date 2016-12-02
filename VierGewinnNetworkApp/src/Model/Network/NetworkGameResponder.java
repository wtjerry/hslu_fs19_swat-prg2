package Model.Network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkGameResponder implements Runnable {

	private final int dedicatedPort = 5400;
	private boolean continueResponding;

	public NetworkGameResponder() {
		this.continueResponding = true;
	}	
	
	@Override
	public void run() {
		try {
		ServerSocket localSocket = new ServerSocket(this.dedicatedPort);
		while (this.continueResponding) {
			try (Socket connectionSocket = localSocket.accept()) {
				BufferedReader streamIn
						= new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream streamOut = new DataOutputStream(connectionSocket.getOutputStream());
				String request = streamIn.readLine();
				System.out.println("Received: " + request);
				if ("I want to play a game.".equals(request)) {					
					streamOut.writeBytes("Oh shit...\n");
					streamOut.flush();
				}
			}
		}
		} catch (IOException ex) {
			Logger.getLogger(NetworkGameResponder.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void stopResponding() {
		this.continueResponding = false;
	}
}
