package Model.Network.RequestHandling;

import Model.Network.ProtocolKeywords;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkRequestManager {

	private final int poolSize = 10;
	
	private final int port;
	private final ExecutorService threadPool;
	private boolean continueHandlingRequests;

	public NetworkRequestManager(int port) {
		this.port = port;
		this.threadPool = Executors.newFixedThreadPool(poolSize);
		this.continueHandlingRequests = true;
	}	
	
	public void start() {
		Runnable serverTask = () -> { handleRequests();};
		Thread serverThread = new Thread(serverTask);
		serverThread.start();
	}

	private void handleRequests() {
		try {
			ServerSocket serverSocket = new ServerSocket(this.port);
			while (this.continueHandlingRequests) {
				Socket connectionSocket = serverSocket.accept();
				BufferedReader streamIn = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				String request = streamIn.readLine();
				RequestHandler requestHandler = createRequestHandler(request, connectionSocket);
				this.threadPool.submit(requestHandler);
			}
		} catch (IOException e) {
			System.err.println("Unable to process client request");
		}
	}

	private RequestHandler createRequestHandler(String request, Socket connectionSocket) {
		RequestHandler requestHandler;
		switch(request) {
			case ProtocolKeywords.AvailableNetworkPlayerListingRequest:
				requestHandler = new NetworkListingHandler(connectionSocket);
				break;
			case ProtocolKeywords.InitGameRequest:
				requestHandler = new InitGameHandler(connectionSocket);
				break;
			case ProtocolKeywords.PlayDisk:
				requestHandler = new DiskPlayedHandler(connectionSocket);
				break;
			default:
				requestHandler = new DefaultHandler(request, connectionSocket);
				break;
		}
		return requestHandler;
	}

	public void stopHandlingRequests() {
		this.continueHandlingRequests = false;
	}
}
