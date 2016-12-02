/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewclasses.subPanel;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import viewclasses.ViewHandler;

/**
 *
 * @author Dane Wicki
 */
public class StartViewPanel extends CardsClass{
    public StartViewPanel(JPanel cards){
        super(cards);
        initComponent();
    }
    @Override
    protected final void initComponent(){
        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        GridLayout layout2 = new GridLayout(3, 1);        
        startAI = new JButton("Play again AI");
        startNetGame = new JButton("Play over Network");
        openHelp = new JButton("Help");
        
        this.setBorder(BorderFactory.createEmptyBorder(80, 20, 80, 20));
        layout2.setVgap(5);
        
        
        // Implementation of all ActionListener
        startAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startAIActionPerformed(evt);
            }
        });
        startNetGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startNetGameActionPerformed(ae);
            }
        });
        openHelp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                openHelpActionPerformed(evt);
            }
        });
        
        startAI.setPreferredSize(startNetGame.getPreferredSize());
        openHelp.setPreferredSize(startNetGame.getPreferredSize());
        
        this.setLayout(layout2);
        this.add(startAI);
        this.add(startNetGame);
        this.add(openHelp);
    }
    public void startAIActionPerformed(ActionEvent evt){
    }
    
    private void startNetGameActionPerformed(ActionEvent evt){  
        super.switchToCard(ViewHandler.NETWORKVIEW);      
    }

    private void openHelpActionPerformed(ActionEvent evt) {
        super.switchToCard(ViewHandler.HELPVIEWNAME);
    }
    
    
    JButton startAI;
    JButton startNetGame;
    JButton openHelp;
}
