package Views.Interfaces;

public interface ViewHandler {
    public StartView switchToStartView();
    public LocalGameCreationView switchToLocalGameCreationView();
    public NetworkView switchToNetworkView();
    public GameView switchToGameView();
}
