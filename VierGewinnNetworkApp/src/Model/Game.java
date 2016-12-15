package Model;
import Model.Network.Settings;
import java.util.*;

public class Game {

	private final int width;
	private final int height;
	private final int myDiskId;
	private final Player opponent;
    
         // example of a array with height (rows, r) 4 and width (columns, c) 6:
         // { c0r0, c1r0, c2r0, c3r0, c4r0, c5r0, c0r1, c1r1, c2r1 ... c2r3, c3r3, c4r3, c5r3 }
         // to get the index of, for example, c2r1 we can do 2 +1*6 (c+r*width) = 8 
	private final int[] gameArray;

	public Game(int myDiskId, Player opponent) {
		this.width = Settings.getGameFieldWidth();
		this.height = Settings.getGameFieldHeight();
                  this.myDiskId = myDiskId;
		this.opponent = opponent;
		this.gameArray = new int[height * width];
	}
	
	public void playDisk(int column, boolean oponent)
	{
		if(column <= this.width)
		{
			if(oponent)
			{
				addDiskToArray(column, this.myDiskId);
			}
			else
			{
				addDiskToArray(column, this.opponent.getDiskId());
			}
		}
	}
	
	private void addDiskToArray(int column, int diskId)
	{
		for(int gameHeight = 0;gameHeight < this.height;gameHeight+=this.width)
		{
			if(this.gameArray[column + gameHeight] != 0)
			{
				this.gameArray[column + gameHeight] = diskId;
			}
		}
	}
	
	public TurnResult checkIfSomebodyWon()
	{
		TurnResult result = TurnResult.NobodyWon;
		for(int i : this.getNumbers())
		{
			if(i == this.myDiskId* 4)
			{
				result = TurnResult.IWon;
			}
			if(i == this.opponent.getDiskId() * 4)
			{
				result = TurnResult.OpponentWon;
			}
		}
	    return result;
	}  

	public ArrayList<Integer> getNumbers()
	{
		ArrayList<Integer> numbers = new ArrayList<>();

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
