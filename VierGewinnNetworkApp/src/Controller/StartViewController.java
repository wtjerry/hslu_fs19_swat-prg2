package Controller;

import Views.Interfaces.StartViewListener;

public class StartViewController implements StartViewListener {

    private final Navigator navigator;

    public StartViewController(Navigator navigator) {
        this.navigator = navigator;
    }

    public void init() {
    }

    @Override
    public void PlayerAgainstComputerPressed() {
        this.navigator.navigateToLocalGameCreationView();
    }

    @Override
    public void PlayOverNetworkPressed() {
        this.navigator.navigateToNetworkView();
    }
}
