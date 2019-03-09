package connect4.Model.Network.RequestHandling;

import connect4.Model.OpponentHasMadeATurnListener;
import java.io.BufferedReader;
import java.io.IOException;

public class DiskPlayedHandler implements RequestHandler {

    private final BufferedReader streamIn;
    private final OpponentHasMadeATurnListener opponentHasMadeATurnListener;

    DiskPlayedHandler(BufferedReader streamIn, OpponentHasMadeATurnListener opponentHasMadeATurnListener) {
        this.streamIn = streamIn;
        this.opponentHasMadeATurnListener = opponentHasMadeATurnListener;
    }

    @Override
    public void handle() throws IOException {
        int column = Integer.parseInt(this.streamIn.readLine());

        if (this.opponentHasMadeATurnListener != null) {
            this.opponentHasMadeATurnListener.opponentHasMadeATurn(column);
        }
    }
}
