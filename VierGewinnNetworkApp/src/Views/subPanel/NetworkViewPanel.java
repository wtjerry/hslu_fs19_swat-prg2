/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.subPanel;

import Views.Interfaces.NetworkView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class NetworkViewPanel extends JPanel implements NetworkView{
    public NetworkViewPanel(){
        initComponent();
    }
    
    protected final void initComponent(){
        listScrollPane = new JScrollPane();
        computerList = new JList<>();
        back = new JButton("Back");
        startGame = new JButton("Start Game");
        
        listScrollPane.setViewportView(computerList);
        
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                backActionPerformed(ae);
            }
        });
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startGameActionPerformed(ae);
            }
        });
        
        this.add(listScrollPane);
        this.add(back);
        this.add(startGame);
    }
    private void startGameActionPerformed(ActionEvent ae) {
        //super.switchToCard(ViewHandler.STARTVIEWNAME);
    }
    private void backActionPerformed(ActionEvent ae){
        //super.switchToCard(ViewHandler.STARTVIEWNAME);
    }
    //private JButton search;

    @Override
    public void showAvailablePlayers(List<String> allPlayers) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private JScrollPane listScrollPane;
    private JList<String> computerList;
    private JButton startGame;
    private JButton back;
}
