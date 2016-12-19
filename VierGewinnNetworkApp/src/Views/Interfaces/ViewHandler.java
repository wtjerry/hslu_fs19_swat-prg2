package Views.Interfaces;

public interface ViewHandler {
    public StartView switchToStartView();
    public LocalGameCreationView switchToLocalGameCreationView();
    public HelpView switchToHelpView();
    public NetworkView switchToNetworkView();
    public GameView switchToGameView(int width, int height);
}
