package Model.Network.RequestHandling;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultHandler_NEW implements RequestHandler_NEW {

    private final String request;;

    DefaultHandler_NEW(String request) {
        this.request = request;
    }

    @Override
    public void handle() {
        Logger.getLogger(DefaultHandler_NEW.class.getName()).log(Level.WARNING, "Received unknown request: {0}", this.request);
    }
}
