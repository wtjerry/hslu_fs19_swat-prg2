package Network;

import Views.NetworkPlayersListUI;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkPlayersSearch implements Runnable {

	private final int dedicatedPort = 5400;
	private final int timeoutInMilliseconds = 50;
			
	private final String baseIpAddress;
	private NewPlayersFoundListener newPlayersFoundListener;

	public NetworkPlayersSearch(String baseIpAddress) {
		this.baseIpAddress = baseIpAddress;
	}	
	
	@Override
	public void run() {
		final List<String> availableHosts = this.GetAvailableHostsInNetwork();
		final List<String> answeringHosts = this.GetAnsweringHosts(availableHosts);
		if (this.newPlayersFoundListener != null) {
			newPlayersFoundListener.NewPlayersFound(answeringHosts);
		}
	}
	
	public void setListener(NewPlayersFoundListener newPlayersFoundListener) {
		this.newPlayersFoundListener = newPlayersFoundListener;
	}
	
	private List<String> GetAvailableHostsInNetwork() {
		List<String> availableHosts = new ArrayList<>();

		for (int i = 0; i < 255; i++) {
			String host = baseIpAddress + i;
			try {
				boolean isReachable = InetAddress.getByName(host).isReachable(this.timeoutInMilliseconds);
				if (isReachable) {
					availableHosts.add(host);
				}
			} catch (UnknownHostException ex) {
				Logger.getLogger(NetworkPlayersSearch.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(NetworkPlayersSearch.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return availableHosts;
	}

	private List<String> GetAnsweringHosts(final List<String> availableHosts) {
		List<String> answeringHosts = new ArrayList<>();

		for (String host : availableHosts) {
			try {
				try (Socket clientSocket = new Socket(host, this.dedicatedPort)) {
					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					outToServer.writeBytes("I want to play a game.");
					String response = inFromServer.readLine();
					Logger.getLogger(NetworkPlayersListUI.class.getName()).log(Level.FINE, "response: {0}", response);
					if ("Oh shit...".equals(response)) {
						answeringHosts.add(host);
					}
				}
			} catch (IOException ex) {
				//Logger.getLogger(NetworkPlayersListUI.class.getName()).log(Level.SEVERE, "io exception for host {0}. This guy probably does not want to play.", host);
			}
		}

		return answeringHosts;
	}
}
