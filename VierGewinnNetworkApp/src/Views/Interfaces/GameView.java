package Views.Interfaces;

public interface GameView extends ListenerSetter<GameViewListener> {
    public void showNewDisk(int x, int y, int player);
    public void showCurrentPlayer(int player);
}
