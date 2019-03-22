package connect4.Views.Interfaces;

public interface LocalGameCreationViewListener {
    void resumeGamePressed();
    void newGamePressed(int width, int height);
    void backPressed();
}
