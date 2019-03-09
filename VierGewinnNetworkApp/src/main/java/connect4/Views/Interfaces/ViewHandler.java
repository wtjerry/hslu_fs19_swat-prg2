package connect4.Views.Interfaces;

public interface ViewHandler {
    StartView switchToStartView();
    LocalGameCreationView switchToLocalGameCreationView();
    HelpView switchToHelpView();
    NetworkView switchToNetworkView();
    GameView switchToGameView(int width, int height);
}
