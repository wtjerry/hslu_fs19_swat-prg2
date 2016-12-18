/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.subPanel;

import Views.Interfaces.NetworkView;
import Views.Interfaces.NetworkViewListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class NetworkViewPanel extends JPanel implements NetworkView{
    
    private NetworkViewListener networkViewListener;
    
    public NetworkViewPanel(){
        this.computerListModel = new DefaultListModel();
        initComponent();
    }
    
    protected final void initComponent(){
        listScrollPane = new JScrollPane();
        computerList = new JList<>(this.computerListModel);
        back = new JButton("Back");
        startGame = new JButton("Start Game");
        
        computerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listScrollPane.setViewportView(computerList);
        
        back.addActionListener(x -> {
            if (this.networkViewListener != null) {
                this.networkViewListener.BackPressed();
            }
        });
        
        startGame.addActionListener(x -> {
            if (this.networkViewListener != null) {
                String selectedIpAddress = this.computerList.getSelectedValue();
                this.networkViewListener.StartGamePressed(selectedIpAddress);
            }
        });
                
        this.add(listScrollPane);
        this.add(back);
        this.add(startGame);
    }
    @Override
    public void showAvailablePlayers(List<String> allPlayers) {
        this.computerListModel.removeAllElements();
        allPlayers.forEach(player -> this.computerListModel.addElement(player));
    }
    
    private final DefaultListModel computerListModel;
    private JScrollPane listScrollPane;
    private JList<String> computerList;
    private JButton startGame;
    private JButton back;

    @Override
    public void setListener(NetworkViewListener networkViewListener) {
        this.networkViewListener = networkViewListener;
    }
}
