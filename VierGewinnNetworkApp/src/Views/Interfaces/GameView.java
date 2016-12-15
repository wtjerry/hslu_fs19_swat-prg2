package Views.Interfaces;

public interface GameView extends ListenerSetter<GameViewListener> {
    public void showNewDiskForMe(int column, int row);
    public void showNewDiskForOpponent(int column, int row);
        
    //todo probably not needed as the concept whos turn it currently is, is already handled by showNewDisk(.. player)
    public void showCurrentPlayer(int player);
}
