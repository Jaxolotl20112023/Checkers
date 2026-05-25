package pack;

public class Board {
	
	private String[][] board; 
	private int x;
	private int y; 
	private int new_x; 
	private int new_y;
	private int x_change; 
	private int y_change; 
	private int white_captures=0;
	private int black_captures=0; 
	
	private String turn;
	private String direction;
	private String[] move_set;
	private String opponent; 
	
	private boolean is_king; 
	
	public Board() {
		board = new String[][] {
			{" 0 "," 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," "},
			{" - "," b "," - "," b "," - "," b "," - "," b "," 1 "},
			{" b "," - "," b "," - "," b "," - "," b "," - "," 2 "},
			{" - "," b "," - "," b "," - "," b "," - "," b "," 3 "},
			{" - "," - "," - "," - "," - "," - "," - "," - "," 4 "},
			{" - "," - "," - "," - "," - "," - "," - "," - "," 5 "},
			{" w "," - "," w "," - "," w "," - "," w "," - "," 6 "},
			{" - "," w "," - "," w "," - "," w "," - "," w "," 7 "},
			{" w "," - "," w "," - "," w "," - "," w "," - "," 8 "},
		};
		
		move_set = new String[] {"l","r","bl","br","cl","cr","cbl","cbr"}; 
	}
	
	public void draw() {
		for (int i=0;i<9;i++) {
			System.out.println();
			for (int j=0;j<9;j++) {
				System.out.print(board[i][j]);
			}
		}
	}
	
	public String get_piece(int x, int y) {
		return board[y][x]; 
	}
	
	public boolean move_piece(int x, int y, String direction, String turn) {
		this.x = x; 
		this.y = y; 
		this.turn = turn;  
		this.direction = direction; 
		is_king = check_king(); 
			
		if (turn.toLowerCase().contains("w")) {
			y_change = 1;
			x_change = 1; 
		} else {
			y_change = -1;
			x_change = 1; 
		}
		
		
		assign_movement(direction); 

		
		check_king();
//		System.out.println(new_x+","+new_y); 
		
		return valid_move();
	}
	
	public boolean check_king() 
	{
		if (board[y][x].contains(turn.toUpperCase())) {
			return true; 
		}
		if ((new_y==1 && board[y][x].contains("w")) || (new_y==8 && board[y][x].contains("b")) ) {
			board[y][x] = board[y][x].toUpperCase(); 
			return true; 
		} 
		
		return false; 
	}
	
	public void assign_movement(String direction)
	{
		

		switch(direction) {
			case "l" : 
				new_y = y - y_change; 
				new_x = x -  x_change; 
				break;
		
			case "r" :
				new_y = y - y_change;
				new_x = x + x_change;
				break; 
				
			case "bl" : 
				if (is_king) 
				{
					new_y = y + y_change;
					new_x = x - x_change;
				
				}
				break;
				
			case "br" : 
				if (is_king)
				{
					new_y = y + y_change;
					new_x = x + x_change; 
				
				}
				break;
				
			case "cl" : 
				new_y = (y - 2*y_change); 
				new_x = (x - 2*x_change);
				break; 
				
			case "cr" : 
				new_y = (y - 2*y_change); 
				new_x = (x + 2*x_change);
				break; 
			
			case "cbl" : 
				if (is_king)  
				{
					new_y = (y + 2*y_change); 
					new_x = (x - 2*x_change); 
				}
				break;
				
			case "cbr" :
				if (is_king) 
				{
					new_y = (y + 2*y_change); 
					new_x = (x + 2*x_change); 
				
				}
				break; 
			
			default : 
				new_y = y; 
				new_x = x;
		}
		
	}
	
	public boolean valid_move() {
		opponent = turn.toLowerCase().contains("w") ? "b" : "w"; 
		
		if (new_x < 0 || new_y < 0) {
			return false; 
		}
		
		if (board[new_y][new_x].contains(" - ")) {
			
			if (direction.contains("c") && !board[(y+new_y)/2][(x+new_x)/2].toLowerCase().contains(opponent)) {
				System.out.println("Invalid Move Capture!"); 
				return false; 
			}
			
			board[new_y][new_x] = board[y][x]; 
			board[y][x] = " - "; 
			
			
			if (direction.contains("c")) {
//				System.out.println("opponent: " + opponent); 
				white_captures = opponent == "b" ? white_captures+=1 : white_captures;
				black_captures = opponent == "w" ? black_captures+=1 : black_captures; 
				
//				System.out.println("White captures: " + white_captures); 
//				System.out.println("Black captures: " + black_captures);
				
				if (white_captures == 12) {
					System.out.println("White Wins!");
					System.exit(0); 
				}
				else if (black_captures == 12) {
					System.out.println("Black Wins!");
					System.exit(0); 
				}
			
				board[(y+new_y)/2][(x+new_x)/2] = " - "; 
				y = new_y; 
				x = new_x;
				multi_captures();
			}
			

			
		} else {
			return false; 
		}
		return true;
	}
	
	public void multi_captures() {
		int old_y,old_x; 
		for (int i=4; i<move_set.length; i++) {
			
			old_y = new_y;
			old_x = new_x;
			
			assign_movement(move_set[i]); 
			
//			System.out.println(move_set[i]+ " : "+new_x+","+new_y); 
			
			try {
				
				new_y = !valid_move() ? old_y :new_y; 
				new_x = !valid_move() ? old_x :new_x; 
				
			} catch (Exception e) {
//				System.out.println("Invalid"); 
			}
			
		}
		
		is_king = check_king();
		
		return; 
	}
	
}
