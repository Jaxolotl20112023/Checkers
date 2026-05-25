package pack;
import java.util.Scanner;

public class Main {
	
	private static Board board; 
	private static int x; 
	private static int y;
	private static String direction;
	
	private static String current_piece; 
	
	private static boolean run = true; 
	private static String player_turn = " w "; 
	
	
	public static void main(String[] args) {
		System.out.println("Start");
		board = new Board(); 
//		board.draw();
		// TODO Auto-generated method stub
		while (run) {
			board.draw();
			System.out.println(player_turn + " is playing"); 
			
			get_input(); 
			
			try {
				current_piece = board.get_piece(x, y); 
			} catch (Exception e){
				System.out.println("Invalid Move!"); 
				continue; 
			}
			
			if (current_piece.toLowerCase().contains(player_turn)) {
				if (!board.move_piece(x, y, direction , player_turn)) {
					System.out.println("Invalid Move!"); 
					continue; 
				}
			} else {
				System.out.println("Invalid Move!"); 
				continue; 
			}
			
			if (player_turn.contains("w")) {
				player_turn = " b ";
			} else {
				player_turn = " w "; 
			}
			
		}
		
	}
	
	public static void get_input() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("X-coordinate: "); 
		
		try {
			x = scanner.nextInt(); 
		} catch (Exception e) {
			System.out.println("Invalid Input!");
			return; 
		}
		
		System.out.println("Y-coordinate: "); 
		
		try {
			y = scanner.nextInt(); 
		} catch (Exception e) {
			System.out.println("Invalid Input!");
			return; 
		}
		
		System.out.println("Direction (c: capture, b: backwards, l: left, r: right) ex: cbl = capture back left"); 
		
		try {
			direction = scanner.next(); 
		} catch (Exception e) {
			System.out.println("Invalid Input!");
			return; 
		}
		
	}


	

}
