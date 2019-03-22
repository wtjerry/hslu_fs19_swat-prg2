package connectfour.controller;

import connectfour.views.interfaces.HelpViewListener;

public class HelpViewController implements HelpViewListener {

    private final Navigator navigator;

    HelpViewController(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void backPressed() {
        this.navigator.navigateToStartView();
    }
}
