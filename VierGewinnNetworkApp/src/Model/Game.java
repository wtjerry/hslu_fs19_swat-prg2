package Model;

public class Game {
	
	private String name;
	private int width;
	private int height;
	private int[] gameArray;
	private Player player1;
	private Player player2;
<<<<<<< HEAD
	public Game(){
=======
	private GameState state;
    
	public Game(String name, int width, int height, String PlayerName1, String PlayerName2)
	{
		this.name = name;
		this.width = width;
		this.height = height;
		this.gameArray = new int[height * width];
		this.player1 = new Player(PlayerName1);
		this.player2 = new Player(PlayerName2);
		//gameState
	}
	
	public void setDisk()
	{
>>>>>>> branch 'Dev' of https://github.com/Meledor/VierGewinntPRG.git
		
	}
	
	public int checkResult()
	{
	 return 0;	
	}
	
	public int[] getNumbers()
	{
		// List besser?
		int possibilities = 4*height*width + 18 - 9*(width+height);
		int[] numbers = new int[possibilities];
		int i = 0; 
		
		// Werte aller vertikalen Möglichkeiten
		for(int y = 1; y <= (width); y++){
			for(int x = 1; x <= (height-3); x++){
				int number = 0;
				for(int n = 1; n <= 4; n++){
					number += gameArray[];
				}
				numbers[i] = number;
				i++;
			}
		}
		
		// Werte aller horizontalen Möglichkeiten
		for(int x = 1; x <= (width-3); x++){
			for(int y = 1; y <= (height); y++){
				int number = 0;
				for(int n = 0; n < 4; n++){
					number += gameArray[((y-1)*6)+n+x];
				}
				numbers[i] = number;
				i++;
			}
		}
		
		
		return numbers;
	}
	

}
