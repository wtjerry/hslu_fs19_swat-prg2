package connect4.Views.Interfaces;

public interface LocalGameCreationViewListener {
    void ResumeGamePressed();
    void NewGamePressed(int width, int height);
    void BackPressed();
}
