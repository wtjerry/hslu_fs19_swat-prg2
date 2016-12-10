package Controller;

import Model.Network.NetworkPlayerSearcher;
import Views.Interfaces.GameView;
import Views.Interfaces.LocalGameCreationView;
import Views.Interfaces.NetworkView;
import Views.Interfaces.StartView;
import Views.Interfaces.ViewHandler;

public class Navigator {

    private final ViewHandler viewHandler;
    private final NetworkPlayerSearcher networkPlayerSearcher;

    public Navigator(ViewHandler viewHandler, NetworkPlayerSearcher networkPlayerSearcher) {
        this.viewHandler = viewHandler;
        this.networkPlayerSearcher = networkPlayerSearcher;
    }

    public void navigateToStartView() {
        StartView view = this.viewHandler.switchToStartView();
        StartViewController controller = new StartViewController(this);
        view.addListener(controller);
        controller.init();
    }

    public void navigateToLocalGameCreationView() {
        LocalGameCreationView view = this.viewHandler.switchToLocalGameCreationView();
        LocalGameCreationViewController controller = new LocalGameCreationViewController(this);
        view.addListener(controller);
        controller.init();
    }

    public void navigateToNetworkView() {
        NetworkView view = this.viewHandler.switchToNetworkView();
        NetworkViewController controller = new NetworkViewController(view, this);
        view.addListener(controller);
        controller.init();
    }

    public void navigateToGameView() {
        GameView view = this.viewHandler.switchToGameView();
        GameViewController controller = new GameViewController(view, this);
        view.addListener(controller);
        controller.init();
    }
}
