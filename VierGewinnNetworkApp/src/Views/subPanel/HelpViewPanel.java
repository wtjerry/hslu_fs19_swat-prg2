/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.subPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Dane Wicki
 */
public class HelpViewPanel extends JPanel{
    public HelpViewPanel(){
        initComponent();
    }
    
    protected final void initComponent(){
        helpText = new JTextArea("How to play This Game:\n TO BE DEFINED");
        back = new JButton("close");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //backActionPerformed(ae);
            }
        });
        
        this.add(back);     
        this.add(helpText);
    }
    
    private JTextArea helpText;
    private JButton back;
}
