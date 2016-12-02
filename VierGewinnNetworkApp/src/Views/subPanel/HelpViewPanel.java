/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewclasses.subPanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import viewclasses.ViewHandler;

/**
 *
 * @author Dane Wicki
 */
public class HelpViewPanel extends CardsClass{
    public HelpViewPanel(JPanel cards){
        super(cards);
        initComponent();
    }
    @Override
    protected final void initComponent(){
        helpText = new JTextArea("How to play This Game:\n TO BE DEFINED");
        back = new JButton("close");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                backActionPerformed(ae);
            }
        });
        
        this.add(back);     
        this.add(helpText);
    }
    
    private void backActionPerformed(ActionEvent ae){
        super.switchToCard(ViewHandler.STARTVIEWNAME);
        
    }
    
    private JTextArea helpText;
    private JButton back;
}
