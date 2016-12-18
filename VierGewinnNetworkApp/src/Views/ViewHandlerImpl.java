/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Views.subPanel.LocalGameCreationViewPanel;
import Views.subPanel.GameViewPanel;
import Views.Interfaces.GameView;
import Views.Interfaces.HelpView;
import Views.Interfaces.LocalGameCreationView;
import Views.Interfaces.NetworkView;
import Views.Interfaces.StartView;
import Views.Interfaces.ViewHandler;
import Views.subPanel.StartViewPanel;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Views.subPanel.HelpViewPanel;
import Views.subPanel.NetworkViewPanel;


public class ViewHandlerImpl extends JFrame implements ViewHandler{
    private final int MIN_HEIGHT = 300;
    private final int MIN_WIDTH = 400;
    
    public static final String STARTVIEWNAME = "startview";
    public static final String HELPVIEWNAME = "helpview";
    public static final String NETWORKVIEW = "networkview";
    public static final String LOCALGAMECREATIONVIEW = "localgamecreationview";
    public static final String GAMEVIEW = "gameview";
    
    
    @Override
    public StartView switchToStartView(){
        clayout.show(cards, STARTVIEWNAME);
        return startView;
    }
    @Override
    public HelpView switchToHelpView(){
        clayout.show(cards, HELPVIEWNAME);
        return helpView;
    }
    @Override
    public NetworkView switchToNetworkView(){
        clayout.show(cards, NETWORKVIEW);
        return networkView;
    }
    @Override
    public LocalGameCreationView switchToLocalGameCreationView(){
        clayout.show(cards, LOCALGAMECREATIONVIEW);
        return localGameCreationView;
    }    
    @Override
    public GameView switchToGameView(){
        clayout.show(cards, GAMEVIEW);
        return gameView;
    }
    
    public ViewHandlerImpl(){
        initComponent();
    }
    
    private void initComponent(){
        URL iconURL = this.getClass().getResource("/Views/test.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setName("Four_connect");
        this.setTitle("Four_connect");
        ImageIcon ico = new ImageIcon("test.ico");
                
        
        this.setSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        this.setResizable(false);
        clayout = new CardLayout();
        cards = new JPanel(clayout);
        
        
        startView = new StartViewPanel();
        helpView = new HelpViewPanel();
        networkView = new NetworkViewPanel();
        localGameCreationView = new LocalGameCreationViewPanel();
        gameView = new GameViewPanel();
        
        
        cards.add(startView, STARTVIEWNAME);
        cards.add(helpView, HELPVIEWNAME);
        cards.add(networkView, NETWORKVIEW);
        cards.add(gameView, GAMEVIEW);
        cards.add(localGameCreationView, LOCALGAMECREATIONVIEW);
        
        this.add(cards);
        this.setVisible(true);        
    }
    
    
    private CardLayout clayout;
    private JPanel cards;
    
    private StartViewPanel startView;
    private LocalGameCreationViewPanel localGameCreationView;
    private HelpViewPanel helpView;
    private NetworkViewPanel networkView;
    private GameViewPanel gameView;

    
}
