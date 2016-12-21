package main;

import Controller.Navigator;
import Model.Network.NetworkPlayerSearcher;
import Model.Network.RequestHandling.NetworkRequestManager;
import Model.Network.RequestHandling.RequestHandlerFactory;
import Model.Network.Settings;
import Views.ViewHandlerImpl;

public class Main {

    public static void main(String[] args) {
        Settings.init();
        
        RequestHandlerFactory requestHandlerFactory = new RequestHandlerFactory();
        NetworkRequestManager networkRequestManager = new NetworkRequestManager(requestHandlerFactory);
        networkRequestManager.start();
        
        final ViewHandlerImpl viewHandler = new ViewHandlerImpl() {};
        final NetworkPlayerSearcher networkPlayerSearcher = new NetworkPlayerSearcher();
        final Navigator navigator = new Navigator(viewHandler, networkPlayerSearcher, networkRequestManager);
        
        requestHandlerFactory.setNavigator(navigator);
        
        navigator.navigateToStartView();
    }
}
