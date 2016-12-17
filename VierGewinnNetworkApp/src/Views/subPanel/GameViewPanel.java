package Views.subPanel;

import Views.Interfaces.GameView;
import Views.Interfaces.GameViewListener;
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

    public GameViewPanel() {
        this.initComponent();
    }

    protected final void initComponent() {
        this.diskColumnTextField = new JTextField("1");
        this.playDiskButton = new JButton("play disk");
        this.log = new JTextArea();

        this.playDiskButton.addActionListener(x -> {
            if (this.gameViewListener != null) {
                final int column = Integer.parseInt( this.diskColumnTextField.getText());
                this.gameViewListener.DiskColumnPressed(column);
            }
        });

        this.add(diskColumnTextField);
        this.add(playDiskButton);
        this.add(log);
        
        this.log.append("view finished initializing\n");
    }

    private JTextField diskColumnTextField;
    private JButton playDiskButton;
    private JTextArea log;
    
    @Override
    public void showNewDiskForMe(int column, int row) {
        //todo implement showing my next disk on screen
        this.log.append("showNewDiskForMe, column: " + column + ", row: " + row + "\n");
    }
    
    @Override
    public void showNewDiskForOpponent(int column, int row) {
        //todo implement showing opponents next disk on screen
        this.log.append("showNewDiskForOpponent, column: " + column + ", row: " + row + "\n");        
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
}
