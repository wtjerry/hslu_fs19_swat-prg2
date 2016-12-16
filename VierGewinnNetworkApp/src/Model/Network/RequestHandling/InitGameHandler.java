package Model.Network.RequestHandling;

import Controller.Navigator;
import Model.Network.ProtocolKeywords;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitGameHandler implements RequestHandler {

    private final Socket socket;
    private final Navigator navigator;

    public InitGameHandler(Socket socket, Navigator navigator) {
        this.socket = socket;
        this.navigator = navigator;
    }

    @Override
    public void run() {
        try {
            DataOutputStream streamOut = new DataOutputStream(this.socket.getOutputStream());
            streamOut.writeBytes(ProtocolKeywords.InitGameAnswer + "\n");
            streamOut.flush();
            if (!this.socket.isClosed()) {
                this.socket.close();
            }

            InetAddress inetAddress = this.socket.getInetAddress();
            String hostAddress = inetAddress.getHostAddress();
            this.navigator.navigateToGameViewForAcceptingNetworkPlay(hostAddress);
            
        } catch (IOException ex) {
            Logger.getLogger(InitGameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
