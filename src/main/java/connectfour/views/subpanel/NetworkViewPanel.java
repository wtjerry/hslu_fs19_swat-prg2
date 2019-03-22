/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour.views.subpanel;

import connectfour.views.interfaces.NetworkView;
import connectfour.views.interfaces.NetworkViewListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class NetworkViewPanel extends JPanel implements NetworkView{

    private final DefaultListModel computerListModel;
    private JList<String> computerList;
    private JButton startGame;
    
    private NetworkViewListener networkViewListener;
    
    public NetworkViewPanel(){
        this.computerListModel = new DefaultListModel();
        initComponent();
    }

    @SuppressWarnings("unchecked")
    private void initComponent(){
        JScrollPane listScrollPane = new JScrollPane();
        computerList = new JList<>(this.computerListModel);
        JButton back = new JButton("Back");
        startGame = new JButton("Start Game");
        
        startGame.setEnabled(false);
        
        computerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listScrollPane.setViewportView(computerList);
        
        back.addActionListener(x -> {
            if (this.networkViewListener != null) {
                this.networkViewListener.backPressed();
            }
        });
        
        startGame.addActionListener(x -> {
            if (this.networkViewListener != null) {
                String selectedIpAddress = this.computerList.getSelectedValue();
                System.out.println(selectedIpAddress);
                this.networkViewListener.startGamePressed(selectedIpAddress);
            }
        });
                
        this.add(listScrollPane);
        this.add(back);
        this.add(startGame);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void showAvailablePlayers(List<String> allPlayers) {
        this.computerListModel.removeAllElements();
        allPlayers.forEach(this.computerListModel::addElement);
        if(allPlayers.isEmpty())
            startGame.setEnabled(false);
        else{
            startGame.setEnabled(true);
            this.computerList.setSelectedIndex(0);
        }
    }

    @Override
    public void setListener(NetworkViewListener networkViewListener) {
        this.networkViewListener = networkViewListener;
    }
}
