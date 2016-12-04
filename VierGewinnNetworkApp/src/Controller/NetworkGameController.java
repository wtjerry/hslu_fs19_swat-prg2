package Controller;

import Model.Network.ProtocolKeywords;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class NetworkGameController {

	private final int port;
	private final String opponentAddress;

	public NetworkGameController(int port, String opponentAddress) {
		this.port = port;		
		this.opponentAddress = opponentAddress;
	}

	public void InitiateGame() {
		CompletableFuture f = CompletableFuture.supplyAsync(() -> sendInitGameRequest());
		f.thenAccept(answer -> initGameAnswer((boolean) answer));
	}
	
	private boolean sendInitGameRequest(){
		
		boolean opponentAnwer = false;
		
		try {
			try (Socket hostSocket = new Socket(this.opponentAddress, this.port)) {
				DataOutputStream streamToHost = new DataOutputStream(hostSocket.getOutputStream());
				BufferedReader streamFromHost = new BufferedReader(new InputStreamReader(hostSocket.getInputStream()));
				streamToHost.writeBytes(ProtocolKeywords.InitGameRequest + "\n");
				streamToHost.flush();
				String response = streamFromHost.readLine();
				opponentAnwer = ProtocolKeywords.InitGameAnswer.equals(response);
			}
		} catch (IOException ex) {
		}
		
		return opponentAnwer;
	}

	private void initGameAnswer(boolean answer) {
		//todo jeremy: notify listeners
		System.out.println("opponentAnswer");
		System.out.println(answer);
	}
	
	public void PlayDisk(int row) {
		CompletableFuture.runAsync(() -> sendPlayDisk(row));
	}

	private void sendPlayDisk(int row) {
		try {
			try (Socket hostSocket = new Socket(this.opponentAddress, this.port)) {
				DataOutputStream streamToHost = new DataOutputStream(hostSocket.getOutputStream());
				BufferedReader streamFromHost = new BufferedReader(new InputStreamReader(hostSocket.getInputStream()));
				streamToHost.writeBytes(ProtocolKeywords.PlayDisk + "\n");
				streamToHost.writeBytes(row + "\n");
				streamToHost.flush();
			}
		} catch (IOException ex) {
		}
	}
}
