package Views.Interfaces;

public interface ViewHandler {
    public StartView switchToStartView();
    public LocalGameCreationView switchToLocalGameCreationView();
    public void switchToHelpView();
    public NetworkView switchToNetworkView();
    public GameView switchToGameView();
}
