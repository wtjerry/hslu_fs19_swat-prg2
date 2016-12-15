/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.subPanel;

import Views.Interfaces.NetworkView;
import Views.Interfaces.NetworkViewListener;
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
        
        String selectedIpAddress = "192.168.1.8";
        startGame.addActionListener(x -> {
            if (this.networkViewListener != null) {
                this.networkViewListener.StartGamePressed(selectedIpAddress);
            }
        });
                
        this.add(listScrollPane);
        this.add(back);
        this.add(startGame);
    }
    @Override
    public void showAvailablePlayers(List<String> allPlayers) {
        //todo implement show available players
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
