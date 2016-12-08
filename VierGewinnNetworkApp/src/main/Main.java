package main;

import Controller.Navigator;
import Model.Network.NetworkPlayerSearcher;
import Views.ViewHandlerImpl;

public class Main {

    public static void main(String[] args) {
        final ViewHandlerImpl viewHandler = new ViewHandlerImpl() {};
        final NetworkPlayerSearcher networkPlayerSearcher = new NetworkPlayerSearcher();
        final Navigator navigator = new Navigator(viewHandler, networkPlayerSearcher);
        
        navigator.navigateToStartView();
    }
}
