package connectfour.views;

import connectfour.views.subpanel.LocalGameCreationViewPanel;
import connectfour.views.subpanel.GameViewPanel;
import connectfour.views.interfaces.GameView;
import connectfour.views.interfaces.HelpView;
import connectfour.views.interfaces.LocalGameCreationView;
import connectfour.views.interfaces.NetworkView;
import connectfour.views.interfaces.StartView;
import connectfour.views.interfaces.ViewHandler;
import connectfour.views.subpanel.StartViewPanel;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import connectfour.views.subpanel.HelpViewPanel;
import connectfour.views.subpanel.NetworkViewPanel;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ViewHandlerImpl extends JFrame implements ViewHandler{
    private static final int MIN_HEIGHT = 300;
    private static final int MIN_WIDTH = 400;
    private static final String START_VIEW_NAME = "startview";
    private static final String HELP_VIEW_NAME = "helpview";
    private static final String NETWORK_VIEW = "networkview";
    private static final String LOCAL_GAME_CREATION_VIEW = "localgamecreationview";
    private static final String GAME_VIEW = "gameview";

    @Override
    public StartView switchToStartView(){
        this.restoreAfterGame();
        clayout.show(cards, START_VIEW_NAME);
        return startView;
    }

    @Override
    public HelpView switchToHelpView(){
        this.restoreAfterGame();
        clayout.show(cards, HELP_VIEW_NAME);
        return helpView;
    }

    @Override
    public NetworkView switchToNetworkView(){
        this.restoreAfterGame();
        clayout.show(cards, NETWORK_VIEW);
        return networkView;
    }

    @Override
    public LocalGameCreationView switchToLocalGameCreationView(){
        this.restoreAfterGame();
        clayout.show(cards, LOCAL_GAME_CREATION_VIEW);
        return localGameCreationView;
    }

    @Override
    public GameView switchToGameView(int width, int height) {
        gameView.startGame(width, height);
        System.out.println(gameView.getSize().height);
        this.setSize(gameView.getSize().width+50, gameView.getSize().height+140);
        gameMenu.setVisible(true);
        clayout.show(cards, GAME_VIEW);
        return gameView;
    }
    
    private void restoreAfterGame(){
        gameMenu.setVisible(false);
        this.setSize(MIN_WIDTH, MIN_HEIGHT);
    }

    protected ViewHandlerImpl(){
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

        cards.add(startView, START_VIEW_NAME);
        cards.add(helpView, HELP_VIEW_NAME);
        cards.add(networkView, NETWORK_VIEW);
        cards.add(gameView, GAME_VIEW);
        cards.add(localGameCreationView, LOCAL_GAME_CREATION_VIEW);
        
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
