package Views.subPanel;

import Views.Interfaces.GameView;
import Views.Interfaces.GameViewListener;
import Views.subPanel.Components.PlayGround;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author dane
 */
public class GameViewPanel extends JPanel implements GameView{

    private GameViewListener gameViewListener;
    private PlayGround playGround;

    public GameViewPanel() {
        this.initComponent();
    }

    protected final void initComponent() {
        this.playGround = new PlayGround(7, 4);
        playGround.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                gameViewListener.DiskColumnPressed(playGround.getColumn());
            }
        });
        this.add(this.playGround);
    }
    
    @Override
    public void showNewDiskForMe(int column, int row) {
        playGround.playerDiskPlayed(column, row);
        System.out.println("x-> " + column + "   y-> " + row);
        //todo implement showing my next disk on screen
        //this.log.append("showNewDiskForMe, column: " + column + ", row: " + row + "\n");
    }
    
    @Override
    public void showNewDiskForOpponent(int column, int row) {
        playGround.opponentDiskPlayed(column, row);
        System.out.println("x-> " + column + "   y-> " + row);
        //todo implement showing opponents next disk on screen
        //this.log.append("showNewDiskForOpponent, column: " + column + ", row: " + row + "\n");        
    }

    @Override
    public void setListener(GameViewListener listener) {
        this.gameViewListener = listener;
    }

    @Override
    public void showIWonDialog() {
        //todo implement i won dialog
        JOptionPane.showMessageDialog(this, "I won");
    }

    @Override
    public void showOpponentWonDialog() {
        //todo implement opponent won dialog
        JOptionPane.showMessageDialog(this, "Opponent won");
    }
    public void start(){
        playGround.start();
    }
}
