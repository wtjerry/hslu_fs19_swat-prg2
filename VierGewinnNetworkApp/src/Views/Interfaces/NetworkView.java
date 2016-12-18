package Views.Interfaces;

import java.util.List;

public interface NetworkView extends ListenerSetter<NetworkViewListener> {
    public void showAvailablePlayers(List<String> allPlayers);
}
