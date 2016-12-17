package Model.Network.RequestHandling;

import Controller.Navigator;
import Model.Network.ProtocolKeywords;
import Model.Network.Settings;
import Model.OpponentHasMadeATurnListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkRequestManager {

    private final int poolSize = 10;

    private final int port;
    private final ExecutorService threadPool;
    private boolean continueHandlingRequests;
    private OpponentHasMadeATurnListener opponentHasMadeATurnListener;
    private Navigator navigator;

    public NetworkRequestManager() {
        this.port = Settings.getPort();
        this.threadPool = Executors.newFixedThreadPool(poolSize);
        this.continueHandlingRequests = true;
    }

    public void start() {
        Runnable serverTask = () -> {
            handleRequests();
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

    private void handleRequests() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            while (this.continueHandlingRequests) {
                Socket connectionSocket = serverSocket.accept();
                RequestHandler requestHandler = createRequestHandler(connectionSocket);
                this.threadPool.submit(() -> callHandlerAndAfterwardsCloseSocket(requestHandler, connectionSocket));
            }
        } catch (IOException e) {
            System.err.println("Unable to process client request");
        }
    }

    private void callHandlerAndAfterwardsCloseSocket(RequestHandler requestHandler, Socket connectionSocket) {
        try {
            requestHandler.handle();
            if (connectionSocket.isClosed() == false) {
                connectionSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(NetworkRequestManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private RequestHandler createRequestHandler(Socket socket) throws IOException {
        BufferedReader streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());

        String request = streamIn.readLine();
        
        RequestHandler requestHandler;
        switch (request) {
            case ProtocolKeywords.AvailableNetworkPlayerListingRequest:
                requestHandler = new NetworkListingHandler(streamOut);
                break;
            case ProtocolKeywords.InitGameRequest:
                InetAddress inetAddress = socket.getInetAddress();
                String hostAddress = inetAddress.getHostAddress();
                requestHandler = new InitGameHandler(streamOut, hostAddress, this.navigator);
                break;
            case ProtocolKeywords.DiskPlayed:
                requestHandler = new DiskPlayedHandler(streamIn, this.opponentHasMadeATurnListener);
                break;
            default:
                requestHandler = new DefaultHandler(request);
                break;
        }
        return requestHandler;
    }

    public void stopHandlingRequests() {
        this.continueHandlingRequests = false;
    }

    public void setOpponentHasMadeATurnListener(OpponentHasMadeATurnListener opponentHasMadeATurnListener) {
        this.opponentHasMadeATurnListener = opponentHasMadeATurnListener;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
