package connectfour.model;

import connectfour.model.network.Settings;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameFieldTest {
    @Test
    public void noDiskPlayedNobodyWon() {
        Settings.init();
        GameField gameField = new GameField();
        assertThat(gameField.checkIfSomebodyWon(), is(WinState.NOBODY_WON));
    }
}
