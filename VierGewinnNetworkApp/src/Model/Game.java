package Model;
import java.util.*;

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
	
	public void setDisk(int row, boolean oponent)
	{
		if(row <= this.width)
		{
			if(oponent)
			{
				addDiskToArray(row, player1.getDiskId());
			}
			else
			{
				addDiskToArray(row, player2.getDiskId());
			}
		}
	}
	
	private void addDiskToArray(int row, int diskId)
	{
		for(int gameHeight = 0;gameHeight < this.height;gameHeight+=6)
		{
			if(this.gameArray[row + gameHeight] != 0)
			{
				this.gameArray[row + gameHeight] = diskId;
			}
		}
	}
	
	
	//Jesus
	public int checkResult()
	{
		int result = 0;
		for(int i : getNumbers())
		{
			if(i == this.player1.getDiskId() * 4)
			{
				result = 1;
			}
			if(i == this.player2.getDiskId() * 4)
			{
				result = 2;
			}
		}
	    return result;
	}  

	public ArrayList<Integer> getNumbers()
	{
		// List besser?
		int possibilities = 4*height*width + 18 - 9*(width+height);
		ArrayList<Integer> numbers = new ArrayList<Integer>();

		//Vertikal
		for(int gameheight = 0;gameheight < height*width;gameheight+=width)
		{
			for(int gameposition = 0;gameposition < (width - 3);gameposition++)
			{
				int result = 0;
				for(int gamewidht = 0; gamewidht < 4;gamewidht++)
				{
					result += this.gameArray[gameheight + gameposition + gamewidht];
				}
				numbers.add(result);
			}
		}
		
		//Height
		for(int gamewidht = 0;gamewidht < height - 3;gamewidht++)
		{
			for(int gameposition = 0;gameposition < (width);gameposition++)
			{
				int result = 0;
				for(int gameheight = 0; gameheight < 4*width;gameheight+=width)
				{
					result += this.gameArray[gameheight + gameposition + gamewidht];
				}
				numbers.add(result);
			}
		}
		
		//Diagonal
		for(int gamewidht = 0;gamewidht < height - 3;gamewidht+=width)
		{
			for(int gameposition = 0;gameposition < (width - 3);gameposition++)
			{
				int result = 0;
				for(int gameheight = 0; gameheight < 4*(width+1);gameheight+=width+1)
				{
					result += this.gameArray[gameheight + gameposition + gamewidht];
				}
				numbers.add(result);
			}
		}
		
		//Diagonal
		for(int gamewidht = 0;gamewidht < height - 3;gamewidht+=width)
		{
			for(int gameposition = 0;gameposition < (width - 3);gameposition++)
			{
				int result = 0;
				for(int gameheight = 3; gameheight < 4*(width-1);gameheight+=width-1)
				{
					result += this.gameArray[gameheight + gameposition + gamewidht];
				}
				numbers.add(result);
			}
		}
		
		return numbers;
	}
}
