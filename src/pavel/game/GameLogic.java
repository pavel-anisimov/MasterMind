
// class that impelemts the logic of the game.
// it keeps vairous variables associated with the game and
// does calculate wheather the game is finished.
package pavel.game;
import java.util.Random;

public class GameLogic {
	private int countTurns;
	private int countActivePins;
	private int countPinsInLine;
	private int curientPin;
	private int [] guess = {-1, -1, -1, -1};
	private int [] randomNumbers = new int [4];
	private int guessThePin = 0;
	private int guessTheColor = 0;

	// simple constractor
	public GameLogic(){
		countActivePins = 0;
		countTurns = 0;
		countPinsInLine = 0;		
	}
		
	// method for generating random numbers for further guessing
	// accepts an array containing curently working pins.
	public void doRandom(int pfr[]){		
		int indx = 0;
		for (int i = 0; i < pfr.length; i++){
			if (pfr[i] != 999)
				indx++;
		}
		Random generator = new Random();		
		for (int i = 0; i < 4; i++){
			randomNumbers[i] = pfr[generator.nextInt(indx)];
		}		
	}
	
	// check if the game is won
	public boolean isWin(){
		if (randomNumbers.equals(guess))
			return true;
		else
			return false;
	}
	
	// checking the scores of the game.
	/// doesn't return anything, just sets data withing the object.
	public void checkScores (){
		guessThePin = 0;
		guessTheColor = 0;

		int [] temp = new int [4];
		System.arraycopy(guess, 0, temp, 0, 4);

		int [] randomNumbersCopy = new int [4];
		System.arraycopy(randomNumbers, 0, randomNumbersCopy, 0, guess.length);
		
		for (int i = 0; i < 4; i++){
			if (guess [i] == randomNumbersCopy[i]) {
				guessThePin++;
			}
		}
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				if (temp[i] == randomNumbersCopy[j]){
					guessTheColor++;
					temp [i] = -2;
					randomNumbersCopy[j] = -1;
				}
			}
		}
		guessTheColor = guessTheColor - guessThePin;

		if (guessTheColor < 0)
			guessTheColor = 0;
	}

	// check if the line is closed
	public boolean isLineClosed(){
		if (guess[0] == -1 || guess[1] == -1 || guess[2] == -1 || guess[3] == -1 )
			return false;
		else
			return true;
	}

	// resetting a guess array. Important do after each line is closed 
	public void renewGuess(){
		guess[0] = guess[1] = guess[2] = guess[3] = -1;
		countPinsInLine = 0;
		guessThePin = 0;
		guessTheColor = 0;
	}

	public int[] getScore(){		
		return guess;		
	}
		
	public int getActivePins(){
		return countActivePins;
	}
	

	public void addActivePins () { // increases avtive pins by one
		this.countActivePins++;		
	}

	public void addPinsInLine () { // increases pins in line by one
		this.countPinsInLine++;		
	}
	
	public void subPinsInLine () { // decreases pins in line by one
		this.countPinsInLine--;		
		if (countPinsInLine < 0)
			this.countPinsInLine = 0;		
	}	
	
	public int getPinsInLine(){
		return countPinsInLine;
	}

	public void setCurientPin(int curientPin) {
		this.curientPin = curientPin;
	}

	public int getCurientPin() {
		return curientPin;
	}

	public void addTurn() {
		this.countTurns++;
	}

	public int getTurn() {
		return countTurns;
	}

	public void setGuess(int [] guess) {
		this.guess = guess;
	}
	
	public void setGuess(int g, int n) {
		this.guess[n] = g;
	}

	public int [] getGuess() {
		return guess;
	}
	
	public int getGuess(int n) {
		return guess[n];
	}

	public int getGuessedThePin() {
		return guessThePin;
	}

	public int getGuessedTheColor() {
		return guessTheColor;
	}
}
