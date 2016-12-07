package Controller;

import Model.AIPlayer;
import Model.Game_NEW;
import Model.Network.RequestHandling.NetworkRequestManager;
import Model.NetworkPlayer;
import Model.OpponentPlayedDiskListener;
import Model.Player_NEW;

public class Controller implements OpponentPlayedDiskListener {

	private final NetworkRequestManager networkRequestManager;
	private Game_NEW game;
	
	public Controller(){
		this.networkRequestManager = new NetworkRequestManager();
		this.networkRequestManager.start();
	}
			
	public void playLocalPressed(){
		Player_NEW opponent = new AIPlayer();
		this.game = new Game_NEW(opponent);
		this.game.addListener(this);
		opponent.addListener(this.game);
		//this.ui.changeToGameView(this);
	}
	
	public void playAgainstNetworkOpponentPressed(String ipAddress) {
		Player_NEW opponent = new NetworkPlayer(ipAddress);
		this.game = new Game_NEW(opponent);
		this.game.addListener(this);
		this.networkRequestManager.setOpponentPlayedDiskListener(this.game);
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