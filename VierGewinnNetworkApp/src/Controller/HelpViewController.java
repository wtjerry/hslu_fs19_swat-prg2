package Controller;

import Views.Interfaces.HelpViewListener;

public class HelpViewController implements HelpViewListener {

    private final Navigator navigator;

    HelpViewController(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void BackPressed() {
        this.navigator.navigateToStartView();
    }

    void init() {
    }
}
