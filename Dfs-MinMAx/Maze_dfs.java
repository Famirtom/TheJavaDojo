package Lab;

public class Maze_dfs {
/* 	 
 	build a Maze DFS
	 1) Print the maze 5X5
	 2) add a visited grid
	 3) write a bare dfs(r,c) with boundary checks
	 4) make dfs search for the exit 'E'
 	 5) mark the found path with '*'
*/ 
	static final char [][] MAZE = {
			{'S','.','.','.','.'},
			{'#','#','.','.','#'},
			{'.','#','#','.','.'},
			{'.','.','.','.','#'},
			{'E','#','#','#','#'}
	};
	public static void printMaze(char[][] m) {
		for(int row=0; row < m.length ; row++) {
			for(int c=0; c<m[row].length; c++) {
				System.out.print(m[row][c]);
			}
			System.out.println();
		}
	}
	public static boolean inBounds(int r, int c) {
		return r >= 0 && c>= 0 && r<MAZE.length && c< MAZE[0].length;
	}
	public static boolean dfs(int r, int c, boolean[][] visited) {
		// stop if out of bound
		if(!inBounds(r,c)) return false;
		// stop if wall or already visited
		if(MAZE[r][c]== '#' || visited[r][c]) return false;
		if(MAZE[r][c]== 'E') {
			System.out.println("reached exit at (" + r + "," + c + ")");
			return true;
		}
		// mark visited and print
		visited[r][c] = true;
		System.out.println("Visiting (" + r + "," + c + ") -> "+ MAZE[r][c]);
		
		// Move in 4 directions: up, down, left, right
		if (dfs(r - 1 , c, visited)) return true; // up
		if (dfs(r +1,c, visited)) return true; // down
		if (dfs(r, c -1, visited)) return true; // left
		if (dfs(r, c + 1, visited)) return true; // right
		
		if (dfs(r - 1, c, visited)) { 
		    if (MAZE[r][c] == '.') MAZE[r][c] = '*';
		    return true;
		}
		if (dfs(r + 1, c, visited)) { 
		    if (MAZE[r][c] == '.') MAZE[r][c] = '*';
		    return true;
		}
		if (dfs(r, c - 1, visited)) { 
		    if (MAZE[r][c] == '.') MAZE[r][c] = '*';
		    return true;
		}
		if (dfs(r, c + 1, visited)) { 
		    if (MAZE[r][c] == '.') MAZE[r][c] = '*';
		    return true;
		}

	return false;
	}
	
	
	public static void main(String []args) {
		
		
		boolean [][] visited = new boolean[MAZE.length][MAZE[0].length];
		int startR = 0, startC = 0;
		for(int r= 0; r< MAZE.length; r++) {
			for(int c= 0; c<MAZE[r].length; c++) {
				if(MAZE[r][c]== 'S'	) {
					startR= r;
					startC =c;
				}
			}
		}
		dfs(startR, startC, visited);
		printMaze(MAZE);
		
		//System.out.println(inBounds(2,3));
		//System.out.println(inBounds(6,1));
	}
}
