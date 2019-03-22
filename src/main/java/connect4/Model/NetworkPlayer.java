package connect4.Model;

import connect4.Model.Network.ProtocolKeywords;
import connect4.Model.Network.Settings;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkPlayer extends Player {
    private final String opponentAddress;
    private final int port;

    public NetworkPlayer(String opponentAddress) {
        this.opponentAddress = opponentAddress;
        this.port = Settings.getPort();
    }

    @Override
    public void makeYourTurnNowAsync(int columnOfPreviousTurn) {
        CompletableFuture.runAsync(() -> this.sendPlayDisk(columnOfPreviousTurn));
    }

    private void sendPlayDisk(int columnOfPreviousTurn) {
        try {
            try (Socket hostSocket = new Socket(this.opponentAddress, this.port)) {
                DataOutputStream streamToHost = new DataOutputStream(hostSocket.getOutputStream());
                streamToHost.writeBytes(ProtocolKeywords.DISK_PLAYED + "\n");
                streamToHost.writeBytes(columnOfPreviousTurn + "\n");
                streamToHost.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(NetworkPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
