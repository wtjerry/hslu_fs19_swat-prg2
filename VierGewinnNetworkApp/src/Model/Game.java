package Model;

public class Game {

	private String name;
	private int width;
	private int height;
	private int[] gameArray;
	private Player player1;
	private Player player2;
	private GameState state;

	public Game(String name, int width, int height, String PlayerName1, String PlayerName2) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.gameArray = new int[height * width];
		this.player1 = new Player(PlayerName1);
		this.player2 = new Player(PlayerName2);
		//gameState
	}

	public void setDisk() {

	}

	public int checkResult() {
		return 0;
	}

	/*public int[] getNumbers()
	{
		
		return {};
	}*/
}
