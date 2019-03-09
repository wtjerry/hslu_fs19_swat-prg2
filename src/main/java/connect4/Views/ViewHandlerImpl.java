/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4.Views;

import connect4.Views.subPanel.LocalGameCreationViewPanel;
import connect4.Views.subPanel.GameViewPanel;
import connect4.Views.Interfaces.GameView;
import connect4.Views.Interfaces.HelpView;
import connect4.Views.Interfaces.LocalGameCreationView;
import connect4.Views.Interfaces.NetworkView;
import connect4.Views.Interfaces.StartView;
import connect4.Views.Interfaces.ViewHandler;
import connect4.Views.subPanel.StartViewPanel;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import connect4.Views.subPanel.HelpViewPanel;
import connect4.Views.subPanel.NetworkViewPanel;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class ViewHandlerImpl extends JFrame implements ViewHandler{
    private final int MIN_HEIGHT = 300;
    private final int MIN_WIDTH = 400;
    
    private static final String STARTVIEWNAME = "startview";
    private static final String HELPVIEWNAME = "helpview";
    private static final String NETWORKVIEW = "networkview";
    private static final String LOCALGAMECREATIONVIEW = "localgamecreationview";
    private static final String GAMEVIEW = "gameview";
    
    
    @Override
    public StartView switchToStartView(){
        this.restoreAfterGame();
        clayout.show(cards, STARTVIEWNAME);
        return startView;
    }
    @Override
    public HelpView switchToHelpView(){
        this.restoreAfterGame();
        clayout.show(cards, HELPVIEWNAME);
        return helpView;
    }
    @Override
    public NetworkView switchToNetworkView(){
        this.restoreAfterGame();
        clayout.show(cards, NETWORKVIEW);
        return networkView;
    }
    @Override
    public LocalGameCreationView switchToLocalGameCreationView(){
        this.restoreAfterGame();
        clayout.show(cards, LOCALGAMECREATIONVIEW);
        return localGameCreationView;
    }    
    @Override
    public GameView switchToGameView(int width, int height) {
        gameView.startGame(width, height);
        System.out.println(gameView.getSize().height);
        this.setSize(gameView.getSize().width+50, gameView.getSize().height+140);
        gameMenu.setVisible(true);
        clayout.show(cards, GAMEVIEW);
        return gameView;
    }
    
    private void restoreAfterGame(){
        gameMenu.setVisible(false);
        this.setSize(MIN_WIDTH, MIN_HEIGHT);
    }
    public ViewHandlerImpl(){
        initComponent();
    }
    
    private void initComponent(){
        URL iconURL = this.getClass().getResource("/test.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setName("Four_connect");
        this.setTitle("Four_connect");
                
        
        this.setSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        this.setResizable(false);
        
        clayout = new CardLayout();
        cards = new JPanel(clayout);
        
        startView = new StartViewPanel();
        helpView = new HelpViewPanel();
        networkView = new NetworkViewPanel();
        localGameCreationView = new LocalGameCreationViewPanel();
        gameView = new GameViewPanel();

        JMenuBar menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem gameSaveItem = new JMenuItem("Save");
        JMenuItem gameCloseItem = new JMenuItem("Close");
        JMenuItem helpAboutItem = new JMenuItem("About");
        
        helpAboutItem.addActionListener(x -> new AboutDialog(this, true).setVisible(true));
        gameCloseItem.addActionListener(x -> gameView.closeGame());
        gameSaveItem.addActionListener(x -> gameView.saveGame());
        
        gameMenu.setVisible(false);
        
        gameMenu.add(gameSaveItem);
        gameMenu.add(gameCloseItem);
        helpMenu.add(helpAboutItem);
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        
        
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

    private JMenu gameMenu;

}
