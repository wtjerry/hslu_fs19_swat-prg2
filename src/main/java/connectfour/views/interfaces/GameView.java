package connectfour.views.interfaces;

public interface GameView extends ListenerSetter<GameViewListener> {
    void showNewDiskForMe(int column, int row);
    void showNewDiskForOpponent(int column, int row);
    void showIWonDialog();
    void showOpponentWonDialog();

    void setPlayer(int gameState);
    
}
