/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4.Views.subPanel;

import connect4.Views.Interfaces.HelpView;
import connect4.Views.Interfaces.HelpViewListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Dane Wicki
 */
public class HelpViewPanel extends JPanel implements HelpView{
    private HelpViewListener helpViewListener;
    
    public HelpViewPanel(){
        initComponent();
    }
    
    private void initComponent(){
        JTextArea helpText = new JTextArea("How to play This Game:\n TO BE DEFINED");
        JButton back = new JButton("close");
        
        back.addActionListener(x -> {
            if (this.helpViewListener != null) {
                this.helpViewListener.backPressed();
            }
        });
        
        this.add(back);     
        this.add(helpText);
    }

    @Override
    public void setListener(HelpViewListener listener) {
        this.helpViewListener = listener;
    }
}
