package main;

public class Main {
	public static void main (String[] args) {
		// this part of the code is where we initialize all of the variables
		// and get the first move ready
		
		int createBoard; 
		
		// Get/Initialize Board class
		Board board = new Board(); 
		
		// Get/Initialize Move class
		Move move = new Move(0,0,'n'); 
		
		// this is the checkers_board that will be used
		String checkers_board[][] = { 
				{" b ", " - ", " b ", " - ", " b ", " - ", " b ", " - "}, 
				{" - ", " b ", " - ", " b ", " - ", " b ", " - ", " b "}, 
				{" b ", " - ", " b ", " - ", " b ", " - ", " b ", " - "}, 
				{" - ", " - ", " - ", " - ", " - ", " - ", " - ", " - "}, 
				{" - ", " - ", " - ", " - ", " - ", " - ", " - ", " - "}, 
				{" - ", " w ", " - ", " w ", " - ", " w ", " - ", " w "}, 
				{" w ", " - ", " w ", " - ", " w ", " - ", " w ", " - "}, 
				{" - ", " w ", " - ", " w ", " - ", " w ", " - ", " w "}, 
		};
		
		// prints the checkers_board array (look in Board.java on line 100)
		board.printBoard(checkers_board);
		
		// Prints out the instructions and what move number it is
		System.out.println(""); 
		System.out.println("Move left: q, Move right: e, Move back left: a, Move back right: d, Take left: k, Take right: t"); 
		System.out.println("Move: "+1); 
		
		// this is the main game loop with a move cap of 50 
		for (int i=2; i<50; i++) {
			
			// this exception function is used to prevent the code from stopping when the user
			// puts the wrong input
			try {
				// gets the x and y values from user (go to Move file on lines 15 and 20) 
				int x = move.getX(); 
				int y = move.getY(); 
				
				// gets the direction it is moving from user (go to Move file on line 40)
				char movement = move.whereMove(); 
				
				// checks whether it's black's or white's turn
				if (i%2!=0) {
					// prints out the new board according to x,y, and movement/direction 
					createBoard = board.moveBoard(x,y,movement,"b",checkers_board); // (go to Board file on line 58
					i+=createBoard; // determines whether to go backwards in the loop (when there's an error)
					
					// prints out the number of moves
					System.out.println(" "); 
					System.out.println("Move: "+i); 
				} else {
					// same as above, but for white
					createBoard = board.moveBoard(x,y,movement,"w",checkers_board); 
					i+=createBoard;
					System.out.println(" ");
					System.out.println("Move: "+i); 
				}
			} catch (Exception e) {
				// prints if error
				System.out.println("Invalid input"); 
				i--; // moves the for loop back by one
				continue; 
			}
		}
	}
}
