/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.subPanel;

import Views.Interfaces.HelpView;
import Views.Interfaces.HelpViewListener;
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
    
    protected final void initComponent(){
        helpText = new JTextArea("How to play This Game:\n TO BE DEFINED");
        back = new JButton("close");
        
        back.addActionListener(x -> {
            if (this.helpViewListener != null) {
                this.helpViewListener.BackPressed();
            }
        });
        
        this.add(back);     
        this.add(helpText);
    }
    
    private JTextArea helpText;
    private JButton back;

    @Override
    public void setListener(HelpViewListener listener) {
        this.helpViewListener = listener;
    }
}
