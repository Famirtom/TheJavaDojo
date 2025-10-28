package Lab;
import java.util.*;

public class lab6 {

	// Board size and winning Lines
	static final int N= 9;
	static final int []	[] LINES = {
			{0,1,2},{3,4,5},{6,7,8}, // rows
			{0,3,6},{1,4,7},{2,5,8}, // columns
			{0,4,8},{2,4,6} // diagonals
	};
	
	// Counter to keep track of results
	static class counters{
		long games = 0;
		long xWins= 0;
		long oWins = 0;
		long draws = 0;
	}
	
	// Check if player p has won
	static boolean win(char[] b, char p	) {
		for(int [] L: LINES) // for each iteration of the loop,
			// this variable will hold one element from the LINES collection
			// for each line l the code checks if 
			// all three positions on the board b[L[0]] 1 and 2 are marked by the player p
			if (b[L[0]] == p && b[L[1]] == p && b[L[2]] == p) return true;
		return false;
	}
	
	// check if the board is full
	static boolean full(char[] b) {
		for (char c : b) if ( c == '.') return false;
		return true;
	}
	
	// DFS recursion
	static void dfs(char[] board, char player, counters c) {
		//  base case: win or draw
		if(win(board, 'X')) {c.games++; c.xWins++; return;}
		if (win(board, 'O')) {c.games++; c.oWins++; return;}
		if (full(board)) {c.games++; c.draws++; return;}
		
		// otherwise: try each empty square
		for (int i= 0; i<N;i++){
			if(board[i] == '.') {
				board[i] = player; // make move
				dfs(board, player == 'X' ? 'O' : 'X' , c);
				board[i] = '.'; // backtrack (undo move)
			}
		}
	}
	
	// main entry point
	public static void main(String[] args) {
		char[] board= new char[N];
		Arrays.fill(board, '.'); // empty cell '.'
		counters c = new counters();
		
		dfs(board, 'X', c); // X always start
		
		System.out.println("Total games: " + c.games);
		System.out.println("X wins: " + c.xWins);
		System.out.println("O wins: " + c.oWins);
		System.out.println("Draws: " + c.draws);
		
	}
}
