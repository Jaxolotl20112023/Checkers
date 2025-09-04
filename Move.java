package main;
import java.util.Scanner; 

public class Move {
	
	// variables accessible to other classes
	private int xMove; 
	private int yMove; 
	private char direction; 
	
	public Move(int xMove, int yMove, char direction) {
		this.xMove = xMove;
		this.yMove = yMove; 
		this.direction = direction; 
	}
	
	// function that gets the x value of the piece
	public int getX() {
		
		// converts to a string to store user input 
		String strX = Integer.toString(xMove); 
		
		// gets the user input
		Scanner scanner = new Scanner(System.in); 
		// stores it in the string version of x
		strX = scanner.nextLine(); 
		
		// converts x back into an integer
		int intX = Integer.parseInt(strX);
		
		// returns the value of x
		return intX; 
		
	}
	
	// function that gets the y value of the piece
	public int getY() {
		
		// converts to a string to store user input
		String strY = Integer.toString(xMove); 
		
		// gets the user input
		Scanner scanner = new Scanner(System.in); 
		// stores it in the string version of y
		strY = scanner.nextLine(); 
		
		// converts it back to an integer
		int intY = Integer.parseInt(strY);
		
		// returns the value of y
		return intY; 
	}
	
	// function that determines the direction of movement 
	public char whereMove() {
		
		// converts the direction to a string to store user input
		String strD = Character.toString(direction);
		
		// gets user input
		Scanner scanner = new Scanner(System.in);
		// stores it in string version of direction
		strD = scanner.nextLine(); 
		
		// converts the string into a char
		char charD = strD.charAt(0);
		
		// returns the value of direction
		return charD; 
	}
}
