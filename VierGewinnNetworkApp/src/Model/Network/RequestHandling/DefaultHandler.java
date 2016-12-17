package Model.Network.RequestHandling;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultHandler implements RequestHandler {

    private final String request;;

    DefaultHandler(String request) {
        this.request = request;
    }

    @Override
    public void handle() {
        Logger.getLogger(DefaultHandler.class.getName()).log(Level.WARNING, "{0}Received unknown request: ", this.request);
    }
}
