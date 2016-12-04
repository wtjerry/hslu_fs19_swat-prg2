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

public class NetworkPlayerSearcher implements Runnable {

	private final int timeoutInMilliseconds = 50;
	private final int port;
			
	private String baseIpAddress = null;
	private NewPlayersFoundListener newPlayersFoundListener;
	private boolean continueSearching;

	public NetworkPlayerSearcher(int port) {
		this.port = port;
		this.continueSearching = true;
	}	
	
	@Override
	public void run() {
		if (this.baseIpAddress == null) {
			throw new RuntimeException("baseIpAddress not set.") {};
		}
		
		while (this.continueSearching) {			
			try {
				SearchPlayersAndThrowEvent();
				Thread.sleep(10000);
			}catch (IOException | InterruptedException ex) {
				Logger.getLogger(NetworkPlayerSearcher.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private void SearchPlayersAndThrowEvent() throws IOException {
		final List<String> availableHosts = this.GetAvailableHostsInNetwork();
		final List<String> answeringHosts = this.GetAnsweringHosts(availableHosts);
		if (this.newPlayersFoundListener != null) {
			newPlayersFoundListener.NewPlayersFound(answeringHosts);
		}
	}
	
	private List<String> GetAvailableHostsInNetwork() throws UnknownHostException, IOException {
		List<String> availableHosts = new ArrayList<>();

		InetAddress localHostLANAddress = LocalIpProvider.getLocalHostLANAddress();
		String localHostAddress = localHostLANAddress.getHostAddress();
		
		for (int i = 0; i < 255; i++) {
			String host = baseIpAddress + i;
			if (!host.equals(localHostAddress)) {
				boolean isReachable = InetAddress.getByName(host).isReachable(this.timeoutInMilliseconds);
				if (isReachable) {
					availableHosts.add(host);
				}
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
					streamToHost.writeBytes(ProtocolKeywords.AvailableNetworkPlayerListingRequest + "\n");
					streamToHost.flush();
					String response = streamFromHost.readLine();
					if (ProtocolKeywords.AvailableNetworkPlayerListingAnswer.equals(response)) {
						answeringHosts.add(host);
					}
				}
			} catch (IOException ex) {
			}
		}

		return answeringHosts;
	}
	
	public void setBaseIpAddress(String baseIpAddress) {
		this.baseIpAddress = baseIpAddress;
	}
	
	public void setListener(NewPlayersFoundListener newPlayersFoundListener) {
		this.newPlayersFoundListener = newPlayersFoundListener;
	}
	
	public void stopSearching() {
		this.continueSearching = false;
	}
}
