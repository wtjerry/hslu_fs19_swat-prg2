package connectfour.views.interfaces;

public interface LocalGameCreationViewListener {
    void resumeGamePressed();
    void newGamePressed(int width, int height);
    void backPressed();
}
