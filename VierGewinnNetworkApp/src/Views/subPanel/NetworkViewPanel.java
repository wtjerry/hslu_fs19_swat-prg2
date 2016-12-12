/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.subPanel;

import Views.Interfaces.NetworkView;
import Views.Interfaces.NetworkViewListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class NetworkViewPanel extends JPanel implements NetworkView{
    
    private NetworkViewListener networkViewListener;
    
    public NetworkViewPanel(){
        initComponent();
    }
    
    protected final void initComponent(){
        listScrollPane = new JScrollPane();
        computerList = new JList<>();
        back = new JButton("Back");
        startGame = new JButton("Start Game");
        
        listScrollPane.setViewportView(computerList);
        
        back.addActionListener(x -> {
            if (this.networkViewListener != null) {
                this.networkViewListener.BackPressed();
            }
        });
        
        startGame.addActionListener(x -> {
            if (this.networkViewListener != null) {
                this.networkViewListener.StartGamePressed();
            }
        });
                
        this.add(listScrollPane);
        this.add(back);
        this.add(startGame);
    }
    @Override
    public void showAvailablePlayers(List<String> allPlayers) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private JScrollPane listScrollPane;
    private JList<String> computerList;
    private JButton startGame;
    private JButton back;

    @Override
    public void setListener(NetworkViewListener networkViewListener) {
        this.networkViewListener = networkViewListener;
    }
}
