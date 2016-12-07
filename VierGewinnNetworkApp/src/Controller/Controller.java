package Controller;

import Model.AIPlayer;
import Model.Game_NEW;
import Model.OpponentPlayedDiskListener;
import Model.Player_NEW;

public class Controller implements OpponentPlayedDiskListener {

	private Game_NEW game;
	
	public void playLocalPressed(){
		Player_NEW opponent = new AIPlayer();
		this.game = new Game_NEW(opponent);
		this.game.addListener(this);
		opponent.addListener(this.game);
		//this.ui.changeToGameView(this);
	}
	
	public void playDiskPressed(int row){
		this.game.playDisk(row);
		//this.ui.updateUI();
	}
	
	@Override
	public void opponentPlayedDisk(int row) {
		//this.ui.updateUI();
	}
}