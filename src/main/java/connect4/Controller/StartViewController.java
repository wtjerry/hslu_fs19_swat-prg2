package connect4.Controller;

import connect4.Views.Interfaces.StartViewListener;

public class StartViewController implements StartViewListener {

    private final Navigator navigator;

    public StartViewController(Navigator navigator) {
        this.navigator = navigator;
    }

    public void init() {
    }

    @Override
    public void playerAgainstComputerPressed() {
        this.navigator.navigateToLocalGameCreationView();
    }

    @Override
    public void playOverNetworkPressed() {
        this.navigator.navigateToNetworkView();
    }

    @Override
    public void openHelpPressed() {
        this.navigator.navigateToHelpView();
    }
}
