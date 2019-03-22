package connect4.Controller;

import connect4.Views.Interfaces.HelpViewListener;

public class HelpViewController implements HelpViewListener {

    private final Navigator navigator;

    HelpViewController(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void backPressed() {
        this.navigator.navigateToStartView();
    }

    void init() {
    }
}
