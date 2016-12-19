package main;

import Controller.Navigator;
import Model.Network.NetworkPlayerSearcher;
import Model.Network.RequestHandling.NetworkRequestManager;
import Model.Network.Settings;
import Views.ViewHandlerImpl;

public class Main {

    public static void main(String[] args) {
        Settings.init();
        
        NetworkRequestManager networkRequestManager = new NetworkRequestManager();
        networkRequestManager.start();
        
        final ViewHandlerImpl viewHandler = new ViewHandlerImpl() {};
        final NetworkPlayerSearcher networkPlayerSearcher = new NetworkPlayerSearcher();
        final Navigator navigator = new Navigator(viewHandler, networkPlayerSearcher, networkRequestManager);
        
        networkRequestManager.setNavigator(navigator);
        
        navigator.navigateToStartView();
    }
}
