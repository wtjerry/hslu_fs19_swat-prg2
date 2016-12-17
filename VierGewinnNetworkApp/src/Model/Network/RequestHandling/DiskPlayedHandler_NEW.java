package Model.Network.RequestHandling;

import Model.OpponentHasMadeATurnListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiskPlayedHandler_NEW implements RequestHandler {

    private final BufferedReader streamIn;
    private final Socket socket;
    private final OpponentHasMadeATurnListener opponentHasMadeATurnListener;

    DiskPlayedHandler_NEW(BufferedReader streamIn, Socket socket, OpponentHasMadeATurnListener opponentHasMadeATurnListener) {
        this.streamIn = streamIn;
        this.socket = socket;
        this.opponentHasMadeATurnListener = opponentHasMadeATurnListener;
    }

    @Override
    public void run() {

        Logger.getLogger(DiskPlayedHandler_NEW.class.getName()).log(Level.INFO, "********** run");

        try {
            int column = Integer.parseInt(this.streamIn.readLine());
            Logger.getLogger(DiskPlayedHandler_NEW.class.getName()).log(Level.INFO, "Opponent played disk in column: {0}:", column);
            if (!this.socket.isClosed()) {
                this.socket.close();
            }

            if (this.opponentHasMadeATurnListener != null) {
                this.opponentHasMadeATurnListener.opponentHasMadeATurn(column);
            }
        } catch (IOException ex) {
            Logger.getLogger(DiskPlayedHandler_NEW.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
