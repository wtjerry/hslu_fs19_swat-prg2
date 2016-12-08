package Views.Interfaces;

import java.util.List;

public interface NetworkView {
    public void showAvailablePlayers(List<String> allPlayers);
    public void addListener(NetworkViewListener networkViewListener);
}
