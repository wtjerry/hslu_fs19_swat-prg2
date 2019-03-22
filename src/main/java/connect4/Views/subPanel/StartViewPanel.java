/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4.Views.subPanel;

import connect4.Views.Interfaces.StartView;
import connect4.Views.Interfaces.StartViewListener;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartViewPanel extends JPanel implements StartView{
    
    private StartViewListener startViewListener;
    
    public StartViewPanel(){
        initComponent();
    }
    
    private void initComponent(){
        GridLayout layout = new GridLayout(3, 1);
        JButton startAI = new JButton("Play against AI");
        JButton startNetGame = new JButton("Play over Network");
        JButton openHelp = new JButton("Help");
        
        this.setBorder(BorderFactory.createEmptyBorder(80, 20, 80, 20));
        layout.setVgap(5);
        
        startAI.addActionListener(x -> { 
            if (this.startViewListener != null) {                
                this.startViewListener.playerAgainstComputerPressed();
            }
        });
        
        startNetGame.addActionListener(x -> {
            if (this.startViewListener != null) {
                this.startViewListener.playOverNetworkPressed();
            }
        });
        
        openHelp.addActionListener(x -> {
            if (this.startViewListener != null) {
                this.startViewListener.openHelpPressed();
            }
        });
                
        startAI.setPreferredSize(startNetGame.getPreferredSize());
        openHelp.setPreferredSize(startNetGame.getPreferredSize());
        
        this.setLayout(layout);
        this.add(startAI);
        this.add(startNetGame);
        this.add(openHelp);
    }

    @Override
    public void setListener(StartViewListener listener) {
        this.startViewListener = listener;
    }
}
