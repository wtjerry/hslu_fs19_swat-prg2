package Controller;

import Model.AIPlayer;
import Model.Game;
import Model.GameField;
import Model.GameState;
import Model.Network.Settings;
import Views.Interfaces.GameView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

    @Override
    public void initForResumeGame() {
//        ObjectInputStream in;
//        try {
//            String saveGamePath = Settings.getSaveGamePath();
//            in = new ObjectInputStream(new FileInputStream(saveGamePath));
//            Obj obj = (Obj) in.readObject();
//            in.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(GameViewController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException | ClassNotFoundException ex) {
//            Logger.getLogger(GameViewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //todo implement resume game
        //load ser
        //nav to gameView
        //pass ser or load byItself or whatever
        //set both listeners again
        //check whos turn it is and notfiy to make turn
    }
}
