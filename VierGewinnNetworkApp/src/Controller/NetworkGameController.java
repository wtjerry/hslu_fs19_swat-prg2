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

	public NetworkGameController(int port) {
		this.port = port;		
	}

	public void InitiateGame(String opponentAddress) {
		CompletableFuture f = CompletableFuture.supplyAsync(() -> sendRequestToOpponent(opponentAddress));
		f.thenAccept(answer -> opponentAnswer((boolean) answer));
	}
	
	private boolean sendRequestToOpponent(String opponentAddress){
		
		boolean opponentAnwer = false;
		
		try {
			try (Socket hostSocket = new Socket(opponentAddress, this.port)) {
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

	private void opponentAnswer(boolean answer) {
		//todo jeremy: notify listeners
		System.out.println("opponentAnswer");
		System.out.println(answer);
	}
}
