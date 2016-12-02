/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewclasses.subPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import viewclasses.ViewHandler;

/**
 *
 * @author dane
 */
public class NetworkViewPanel extends CardsClass{
    public NetworkViewPanel(JPanel cards){
        super(cards);
        initComponent();
    }
    @Override
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
        super.switchToCard(ViewHandler.STARTVIEWNAME);
    }
    private void backActionPerformed(ActionEvent ae){
        super.switchToCard(ViewHandler.STARTVIEWNAME);
    }
    
    private JScrollPane listScrollPane;
    private JList<String> computerList;
    private JButton startGame;
    private JButton back;
    //private JButton search;
}
