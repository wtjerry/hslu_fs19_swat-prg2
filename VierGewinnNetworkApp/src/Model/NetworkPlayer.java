package Model;

import Model.Network.ProtocolKeywords;
import Model.Network.Settings;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class NetworkPlayer extends Player {

    private final String opponentAddress;
    private final int port;

    public NetworkPlayer(int diskId, String opponentAddress) {
        super(diskId);
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
                BufferedReader streamFromHost = new BufferedReader(new InputStreamReader(hostSocket.getInputStream()));
                streamToHost.writeBytes(ProtocolKeywords.DiskPlayed + "\n");
                streamToHost.writeBytes(columnOfPreviousTurn + "\n");
                streamToHost.flush();
            }
        } catch (IOException ex) {
        }
    }
}
