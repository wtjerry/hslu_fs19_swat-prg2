package connect4.main;

import connect4.Controller.Navigator;
import connect4.Model.Network.NetworkPlayerSearcher;
import connect4.Model.Network.RequestHandling.NetworkRequestManager;
import connect4.Model.Network.RequestHandling.RequestHandlerFactory;
import connect4.Model.Network.Settings;
import connect4.Views.ViewHandlerImpl;

class Main {

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
