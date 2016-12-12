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
    public void showNewDisk(int x, int y, int player) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showCurrentPlayer(int player) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setListener(GameViewListener listener) {
    }

}
