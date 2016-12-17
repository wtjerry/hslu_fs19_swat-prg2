package Model.Network.RequestHandling;

import Model.OpponentHasMadeATurnListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiskPlayedHandler_NEW implements RequestHandler_NEW {

    private final BufferedReader streamIn;
    private final OpponentHasMadeATurnListener opponentHasMadeATurnListener;

    DiskPlayedHandler_NEW(BufferedReader streamIn, OpponentHasMadeATurnListener opponentHasMadeATurnListener) {
        this.streamIn = streamIn;
        this.opponentHasMadeATurnListener = opponentHasMadeATurnListener;
    }

    @Override
    public void handle() throws IOException {

        Logger.getLogger(DiskPlayedHandler_NEW.class.getName()).log(Level.INFO, "********** run");

        int column = Integer.parseInt(this.streamIn.readLine());

        Logger.getLogger(DiskPlayedHandler_NEW.class.getName()).log(Level.INFO, "Opponent played disk in column: {0}:", column);

        if (this.opponentHasMadeATurnListener != null) {
            this.opponentHasMadeATurnListener.opponentHasMadeATurn(column);
        }
    }
}
