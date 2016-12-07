package Model;

import java.util.ArrayList;
import java.util.List;

public class Game_NEW implements OpponentPlayedDiskListener{

	private final Player_NEW opponent;
	private GameState_NEW currentGameState;
	private final List<OpponentPlayedDiskListener> opponentPlayedDiskListeners;
	
	public Game_NEW(Player_NEW opponent) {
		this.opponentPlayedDiskListeners = new ArrayList<>();
		this.opponent = opponent;
		this.currentGameState = GameState_NEW.OpponentsTurn;
	}

	public void playDisk(int row) {
		this.currentGameState = GameState_NEW.MyTurn;
		//this.gameField.setMyDisk(row);
		this.opponent.opponentPlayedDisk(row);
	}

	public void addListener(OpponentPlayedDiskListener diskPlayedListener) {
		this.opponentPlayedDiskListeners.add(diskPlayedListener);
	}

	@Override
	public void opponentPlayedDisk(int row) {
		if (this.currentGameState == GameState_NEW.MyTurn) {
			throw new IllegalStateException("Opponent played a disk while it was my turn."); 
		}
		
		this.currentGameState = GameState_NEW.MyTurn;
		
		this.opponentPlayedDiskListeners.forEach(x -> x.opponentPlayedDisk(row));
	}
}
