/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewclasses;

import viewclasses.subPanel.StartViewPanel;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import viewclasses.subPanel.HelpViewPanel;
import viewclasses.subPanel.NetworkViewPanel;

/**
 *
 * @author Dane Wicki
 */
public class ViewHandler extends JFrame{
    private final int MIN_HEIGHT = 300;
    private final int MIN_WIDTH = 400;
    public static final String STARTVIEWNAME = "startview";
    public static final String HELPVIEWNAME = "helpview";
    public static final String NETWORKVIEW = "networkview";
    
    
    public ViewHandler(){
        initComponent();
    }
    
    private void initComponent(){
        URL iconURL = getClass().getResource("test.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setName("Four_connect");
        this.setTitle("Four_connect");
        ImageIcon ico = new ImageIcon("test.ico");
                
        
        this.setSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        this.setResizable(false);
        
        cards = new JPanel(new CardLayout());
        startView = new StartViewPanel(cards);
        helpView = new HelpViewPanel(cards);
        networkView = new NetworkViewPanel(cards);
        cards.add(startView, STARTVIEWNAME);
        cards.add(helpView, HELPVIEWNAME);
        cards.add(networkView, NETWORKVIEW);
        
        this.add(cards);
        this.setVisible(true);        
    }
    
    
    
    private JPanel cards;
    private StartViewPanel startView;
    private HelpViewPanel helpView;
    private NetworkViewPanel networkView;
    
}
