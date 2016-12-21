package Model.Network.RequestHandling;

import Controller.Navigator;
import Model.Network.Settings;
import Model.OpponentHasMadeATurnListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkRequestManager {

    private final int poolSize = 10;

    private final RequestHandlerFactory requestHandlerFactory;
    private final int port;
    private final ExecutorService threadPool;

    private boolean continueHandlingRequests;
    private Navigator navigator;

    public NetworkRequestManager(RequestHandlerFactory requestHandlerFactory) {
        this.requestHandlerFactory = requestHandlerFactory;
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
                RequestHandler requestHandler = this.requestHandlerFactory.createRequestHandler(connectionSocket);
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

    public void stopHandlingRequests() {
        this.continueHandlingRequests = false;
    }

    public void setOpponentHasMadeATurnListener(OpponentHasMadeATurnListener opponentHasMadeATurnListener) {
        this.requestHandlerFactory.setOpponentHasMadeATurnListener(opponentHasMadeATurnListener);
    }
}
