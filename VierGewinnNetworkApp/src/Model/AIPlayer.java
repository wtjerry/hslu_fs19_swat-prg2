package Model;

public class AIPlayer extends Player_NEW {

	@Override
	public void opponentPlayedDisk(int row) {
		//determine where to play my next disk
		int rowOfMyNextDisk = 1;
		this.notifyDiskPlayedListeners(rowOfMyNextDisk);
	}
}
