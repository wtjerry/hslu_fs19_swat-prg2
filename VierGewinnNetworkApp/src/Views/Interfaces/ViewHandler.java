package Views.Interfaces;

public interface ViewHandler {
    public StartView switchToStartView();
    public LocalGameCreationView switchToLocalGameCreationView();
    public HelpView switchToHelpView();
    public NetworkView switchToNetworkView();
    public GameView swtichToGameView(int x, int y);
    public GameView switchToGameView();
}
