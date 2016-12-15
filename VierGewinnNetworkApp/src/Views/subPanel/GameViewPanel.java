package Views.subPanel;

import Views.Interfaces.GameView;
import Views.Interfaces.GameViewListener;
import javax.swing.JPanel;

/**
 * @author dane
 */
public class GameViewPanel extends JPanel implements GameView{

    public GameViewPanel() {
    }

    @Override
    public void showNewDiskForMe(int column, int row) {
        //todo implement showing my next disk on screen
    }
    
    @Override
    public void showNewDiskForOpponent(int column, int row) {
        //todo implement showing opponents next disk on screen
    }

    @Override
    public void showCurrentPlayer(int player) {
        //todo probably not needed, see comment on interface 
    }
    
    @Override
    public void setListener(GameViewListener listener) {
    }

}
