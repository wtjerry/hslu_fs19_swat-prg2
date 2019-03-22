package connectfour.views.interfaces;

import java.util.List;

public interface NetworkView extends ListenerSetter<NetworkViewListener> {
    void showAvailablePlayers(List<String> allPlayers);
}
