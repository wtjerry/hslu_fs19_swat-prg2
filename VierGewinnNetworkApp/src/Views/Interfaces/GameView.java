package Views.Interfaces;

public interface GameView extends ListenerSetter<GameViewListener> {
    public void showNewDiskForMe(int column, int row);
    public void showNewDiskForOpponent(int column, int row);
    public void showIWonDialog();
    public void showOpponentWonDialog();
}
