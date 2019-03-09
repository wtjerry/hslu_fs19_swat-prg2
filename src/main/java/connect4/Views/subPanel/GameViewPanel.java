package connect4.Views.subPanel;

import connect4.Views.Interfaces.GameView;
import connect4.Views.Interfaces.GameViewListener;
import connect4.Views.subPanel.Components.PlayGround;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
 * @author dane
 */
public class GameViewPanel extends JPanel implements GameView{

    private GameViewListener gameViewListener;
    private PlayGround playGround;
    private JTextField stateTextField;
    
    private boolean dialogOpened;
    private int playerState;

    public GameViewPanel() {
        this.initComponent();
    }

    private void initComponent() {
        this.playerState = 1;
        this.dialogOpened = false;
        this.playGround = new PlayGround(6, 4);
        this.stateTextField = new JTextField();
        this.stateTextField.setEditable(false);
        this.stateTextField.setMaximumSize(new Dimension(1000, 20));
        playGround.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if(playGround.notFull())
                    gameViewListener.DiskColumnPressed(playGround.getColumn());
            }
        });

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(stateTextField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playGround, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 139, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stateTextField)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playGround, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        
        
        
        //this.add(this.playGround);
    }
    
    @Override
    public void showNewDiskForMe(int column, int row) {
        this.setPlayer(0);
        playGround.playerDiskPlayedUpsideDown(column, row);
        System.out.println("x-> " + column + "   y-> " + row);
    }
    
    @Override
    public void showNewDiskForOpponent(int column, int row) {
        this.setPlayer(1);
        playGround.opponentDiskPlayedUpsideDown(column, row);
        System.out.println("x-> " + column + "   y-> " + row);
    }

    @Override
    public void setListener(GameViewListener listener) {
        this.gameViewListener = listener;
    }

    @Override
    public void showIWonDialog() {
        if(!this.dialogOpened){
            this.dialogOpened = true;
            JOptionPane.showMessageDialog(this, "You won");
            this.dialogOpened = false;
        }
    }

    @Override
    public void showOpponentWonDialog() {
        if(!this.dialogOpened){
            this.dialogOpened = true;
            JOptionPane.showMessageDialog(this, "Opponent won");
            this.dialogOpened = false;
        }
    }
    public void startGame(){
        this.dialogOpened = false;
        playGround.start();
    }
    public void startGame(int x, int y){
        playGround.start(x, y);
        this.repaint();
        this.dialogOpened = false;
    }
    public void startGame(Dimension size, int x, int y){
        playGround = new PlayGround(size, x, y);
        this.dialogOpened = false;
    }
    public Dimension getSize(){
        return this.playGround.getSize();
    }
    public void closeGame() {
        gameViewListener.CloseGamePressed();
    }
    public void saveGame(){
        gameViewListener.SaveGamePressed();
    }

    @Override
    public void setPlayer(int gameState) {
        switch(gameState){
            case 0:
                stateTextField.setText("Your Turn!");
                playGround.setEnabled(true);
                this.playerState = 1;
                break;
            case 1:
                this.playerState = 0;
                stateTextField.setText("Waiting for opponent...");
                playGround.setEnabled(false);  
                break;
        }
    }
}
