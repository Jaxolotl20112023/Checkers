package main;
import java.util.ArrayList; 

// I hate my life :(

public class Board {
	// function that deals with non-capturing movement 
	public int movement_func(int x, int y, int xAmount, int yAmount, String move, String board[][]) {
		
		// if the move is white's, then check if x and y will be inside of the board and if there's anything in the way
		if (move == "w" && x+xAmount >= 0 && y+yAmount >= 0 && board[y+yAmount][x+xAmount] == " - ") { 
			// replaces where the piece will go with piece
			board[y+yAmount][x+xAmount] = board[y][x]; 
			// replaces where it previously was with blank
			board[y][x] = " - ";  
		
		// does the same thing as white's turn, but for black
		} else if (move == "b" && x+xAmount >= 0 && y-yAmount <= 7 && board[y-yAmount][x+xAmount] == " - ") {
			board[y-yAmount][x+xAmount] = board[y][x]; 
			board[y][x] = " - ";
		} else {
			// if there is an error it will return -1
			// (go to Main file line 53 for more info)
			return -1; 
		}
		// prints the board and returns 0
		printBoard(board); 
		return 0; 
	}
	
	// function that deals with the capturing of pieces
	public int capturing_func(int x, int y, int xAmount, int yAmount, int xChange, int yChange, String move, String board[][]) {
		
		// these predict where the current piece would land if it captured
		// xAmount is the amount it will move in the x direction to get to where it will land after capturing (potential spot)
		// yAmount is the amount it will move in the y direction to get to where it will land after capturing (potential spot)
		// yChange changes whether to add or subtract yAmount to y
		// xChange changes whether to add or subtract xAmount to x
		
		int white_hopY = y+yAmount; 
		int white_hopX = x+xAmount; 
		int black_hopY = y+(yAmount)*yChange;
		int black_hopX = x+(xAmount); 		
	
		// this checks when a king moves backwards to capture a piece
		// taking_backwards determines whether to add or subtract the y-value of "normal move" or one space moved to the current y
		int taking_backwards = 1; 
		if (board[y][x].contains(move.toUpperCase()) && yAmount == 2) {
			taking_backwards = -1; 
		} 
		
		// these variables checks the x and y values of the "capturing target" 
		int one_white_hopY = y-taking_backwards*(Math.abs(yAmount)-1); 
		int one_white_hopX = x+xChange*(Math.abs(xAmount)-1);
		int one_black_hopY = y-yChange*(Math.abs(yAmount)-1); 
		int one_black_hopX = x+xChange*(Math.abs(xAmount)-1); 

		// if white's turn, then check if the potential spot is empty and checks capturing target has a black piece
		if (move == "w" && board[white_hopY][white_hopX].contains("-") && board[one_white_hopY][one_white_hopX].toLowerCase().contains("b")) {
			// if true then put the current piece into potential spot
			board[white_hopY][white_hopX] = board[y][x]; 
			// makes the capturing target blank
			board[one_white_hopY][one_white_hopX] = " - "; 
			// makes the current spot blank
			board[y][x] = " - "; 
			
		// this is the same for black, but uses the black variable
		} else if (move == "b" && board[black_hopY][black_hopX] == " - " && board[one_black_hopY][one_black_hopX].toLowerCase().contains("w")) {
			board[black_hopY][black_hopX] = board[y][x]; 
			board[one_black_hopY][one_black_hopX] = " - "; 
			board[y][x] = " - "; 
		} else {
			// if the move is invalid it will print to the user
			System.out.println("NOT POSSIBLE DIRECTION >:("); 
			// and return -1
			return -1; 
		}
		// prints the board and returns 0
		printBoard(board); 
		return 0;
	} 
	
	// function is used to makes the pieces move to the right place according to the directon
	public int moveBoard(int x, int y, char direction, String move, String board[][]) {
		// checks if there is a piece 
		if (board[y][x] != " - ") {
			if (direction == 'q') { // check if it's going left
				return movement_func(x,y,-1,-1, move, board); 
				
			} else if (direction == 'e') {	// checks if it's going right. 	
				return movement_func(x,y,1,-1, move, board); // calls move function with correct parameters 
				
			} else if (direction == 'a' && board[y][x].contains(move.toUpperCase())) { // checks if it's going left backwards and if it's a king 	
				return movement_func(x,y,-1,1,move,board);
				
			} else if (direction == 'd' && board[y][x].contains(move.toUpperCase())) { // checks if it's going right backwards and if it's a king
				return movement_func(x,y,1,1,move,board); 
				
			} else if (direction == 'y') { // checks if it's capturing to the left
				return capturing_func(x,y,-2,-2,-1,-1,move, board); 
				
			} else if (direction == 'i') { // checks if it's capturing to the right
				return capturing_func(x,y,2,-2,1,-1,move,board); 
				
			} else if (direction == 'h' && board[y][x].contains(move.toUpperCase())) { // checks if it's capturing to the left backwards and if it's a king
				return capturing_func(x,y,-2,2,-1,-1,move,board); 
				
			} else if (direction == 'k' && board[y][x].contains(move.toUpperCase())) { // checks if it's capturing to the right backwards and if it's a king
				return capturing_func(x,y,2,2,1,-1,move,board); 
			} 
			else {
				// if the direction is invalid then print to user
				System.out.println("NOT POSSIBLE DIRECTION >:("); 
				return -1; 
			}
		} else {
			// if there is no piece then print to user
			System.out.println("NOT VALID PIECE"); 
			return -1; 
		}		
	}
	
	// function that prints out the board
	public void printBoard(String board[][]) {
		// tracks the amount of black and white pieces
		int black_pieces = 0; 
		int white_pieces = 0; 
		
		// prints the column values
		System.out.println(" 0  1  2  3  4  5  6  7 "); 
		System.out.println(""); 
		
		// for loop to print the rows
		for (int i=0;i<8; i++) {
			// for loop to print the columns
			for (int j=0;j<8; j++) {
				
				// checks if there is a white piece at the other end of the board
				if (board[0][j].contains("w")) {
					// then promote it to a king
					board[0][j] = " W ";
				
				// same thing, but for black
				} else if (board[7][j].contains("b")) {
					board[7][j] = " B "; 
				}
				
				// checks if there are any white pieces
				if (board[i][j].toLowerCase().contains("w")) {
					// then add one to the piece counter
					white_pieces++;
					
				// same thing, but for black
				} else if (board[i][j].toLowerCase().contains("b")) {
					black_pieces++;
				}
				
				// print out the board value
				System.out.printf(board[i][j]);
				
			}
			// make it move down a row 
			System.out.println("   "+i);
		} 
		
		// checks if there are no white pieces
		if (white_pieces==0) {
			// then black wins 
			System.out.println("Black Wins!!"); 
			System.exit(0);
		
		// same thing, but for black and white wins instead
		} else if (black_pieces==0) {
			System.out.println("White Wins!!"); 
			System.exit(0);
		}
	}
}
