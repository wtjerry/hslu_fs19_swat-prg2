package Model.Network;

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

	private final int port;
	private final int timeoutInMilliseconds = 50;
			
	private final String baseIpAddress;
	private NewPlayersFoundListener newPlayersFoundListener;
	private boolean continueSearching;

	public NetworkPlayersSearch(String baseIpAddress, int port) {
		this.port = port;
		this.baseIpAddress = baseIpAddress;
		this.continueSearching = true;
	}	
	
	@Override
	public void run() {
		while (this.continueSearching) {			
			SearchPlayersAndThrowEvent();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
				Logger.getLogger(NetworkPlayersSearch.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private void SearchPlayersAndThrowEvent() {
		final List<String> availableHosts = this.GetAvailableHostsInNetwork();
		final List<String> answeringHosts = this.GetAnsweringHosts(availableHosts);
		if (this.newPlayersFoundListener != null) {
			newPlayersFoundListener.NewPlayersFound(answeringHosts);
		}
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
				try (Socket hostSocket = new Socket(host, this.port)) {
					DataOutputStream streamToHost = new DataOutputStream(hostSocket.getOutputStream());
					BufferedReader streamFromHost = new BufferedReader(new InputStreamReader(hostSocket.getInputStream()));
					streamToHost.writeBytes("I want to play a game.\n");
					streamToHost.flush();
					String response = streamFromHost.readLine();
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
	
	public void setListener(NewPlayersFoundListener newPlayersFoundListener) {
		this.newPlayersFoundListener = newPlayersFoundListener;
	}
	
	public void stopSearching() {
		this.continueSearching = false;
	}
}
