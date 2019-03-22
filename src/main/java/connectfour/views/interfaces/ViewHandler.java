package connectfour.views.interfaces;

public interface ViewHandler {
    StartView switchToStartView();
    LocalGameCreationView switchToLocalGameCreationView();
    HelpView switchToHelpView();
    NetworkView switchToNetworkView();
    GameView switchToGameView(int width, int height);
}
