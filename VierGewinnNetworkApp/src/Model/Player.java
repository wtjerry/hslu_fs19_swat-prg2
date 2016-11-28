package Model;

import java.util.Random;

/* This class represents a Player in the game. */
public class Player {

	//name of the Player
	private String name;

	//state of the Player
	private PlayerStates state;

	//id of the player
	private int diskId;

	public Player(String pName) {
		this.name = pName;
		this.diskId = getPrimNumber();
		this.state = PlayerStates.WaitingForInvitationResponse;
	}

	//Get Name of the Player
	public String getName() {
		return this.name;
	}

	//Get state of the Player
	public PlayerStates getState() {
		return this.state;
	}

	//Get disk ID of the Player
	public int getDiskId() {
		return this.diskId;
	}

	//Set Name of the Player
	public void setPlayerState(PlayerStates state) {
		this.state = state;
	}

	private int getPrimNumber() {
		int num = 0;
		Random rand = new Random(); // generate a random number
		num = rand.nextInt(1000) + 1;
		while (!isPrime(num)) {
			num = rand.nextInt(1000) + 1;
		}
		return num;
	}

	//Checks to see if the requested value is prime.
	private static boolean isPrime(int inputNum) {
		if (inputNum <= 3 || inputNum % 2 == 0) {
			return inputNum == 2 || inputNum == 3; //this returns false if number is <=1 & true if number = 2 or 3
		}
		int divisor = 3;
		while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0)) {
			divisor += 2; //iterates through all possible divisors
		}
		return inputNum % divisor != 0; //returns true/false
	}

}
