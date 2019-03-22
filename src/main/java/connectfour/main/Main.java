package connectfour.main;

import connectfour.controller.Navigator;
import connectfour.model.network.NetworkPlayerSearcher;
import connectfour.model.network.requesthandling.NetworkRequestManager;
import connectfour.model.network.requesthandling.RequestHandlerFactory;
import connectfour.model.network.Settings;
import connectfour.views.ViewHandlerImpl;

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
