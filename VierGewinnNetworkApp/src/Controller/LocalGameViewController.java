package Controller;

import Model.AIPlayer;
import Model.Game;
import Model.GameField;
import Model.GameState;
import Model.Network.Settings;
import Views.Interfaces.GameView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalGameViewController extends GameViewController {

    public LocalGameViewController(GameView view, Navigator navigator) {
        super(view, navigator);
    }

    @Override
    void init(GameState startGameState) {
        GameField gameField = new GameField();
        AIPlayer opponent = new AIPlayer(gameField);
        this.game = new Game(gameField, opponent, startGameState);
        this.game.setListener(this);
        opponent.setListener(this.game);
        opponent.startWithFirstDisk();
    }

    @Override
    public void SaveGamePressed() {
        try {
            String saveGamePath = Settings.getSaveGamePath();
            try (ObjectOutputStream gameSaveStream = new ObjectOutputStream(new FileOutputStream(saveGamePath))) {
                this.game.saveTo(gameSaveStream);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocalGameViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LocalGameViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
