package connectfour.views.subpanel;

import connectfour.views.interfaces.GameView;
import connectfour.views.interfaces.GameViewListener;
import connectfour.views.subpanel.components.PlayGround;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class GameViewPanel extends JPanel implements GameView{

    private GameViewListener gameViewListener;
    private PlayGround playGround;
    private JTextField stateTextField;
    
    private boolean dialogOpened;

    public GameViewPanel() {
        this.initComponent();
    }

    private void initComponent() {
        this.dialogOpened = false;
        this.playGround = new PlayGround(6, 4);
        this.stateTextField = new JTextField();
        this.stateTextField.setEditable(false);
        this.stateTextField.setMaximumSize(new Dimension(1000, 20));
        playGround.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(playGround.notFull())
                    gameViewListener.diskColumnPressed(playGround.getColumn());
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

    public void startGame(int x, int y){
        playGround.start(x, y);
        this.repaint();
        this.dialogOpened = false;
    }
    @Override
    public Dimension getSize(){
        return this.playGround.getSize();
    }
    public void closeGame() {
        gameViewListener.closeGamePressed();
    }
    public void saveGame(){
        gameViewListener.saveGamePressed();
    }

    @Override
    public void setPlayer(int gameState) {
        if (gameState == 0) {
            stateTextField.setText("Waiting for opponent...");
            playGround.setEnabled(false);
        } else if (gameState == 1) {
            stateTextField.setText("Your Turn!");
            playGround.setEnabled(true);
        }
    }
}
