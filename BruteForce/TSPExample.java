import java.io.*;
import java.util.*;
public class TSPExample {
		
	static double bestLen = Double.POSITIVE_INFINITY;
	static int[] bestPath = null;
		static int [][] cities = {
				{1,1},
				{5,5},
				{10,3},
				{2,7},
		};
	public static double distance(int city1, int city2) {
		
		// coordinate of first city
		double x1 = cities[city1][0];
		double y1 = cities[city1][1];
		
		// coordinate of second city
		double x2 = cities[city2][0];
		double y2 = cities[city2][1];
		
		// distance
		double distance = Math.sqrt((x1-x2)*(x1-x2) +(y1-y2)*(y1-y2));
		return distance;
	}
	public static double lengthOfPath(int[] path) {
	    double totalDistance = 0.0;
	    for (int i = 0; i < path.length - 1; i++) {
	        totalDistance += distance(path[i], path[i + 1]);
	    }
	    totalDistance += distance(path[path.length - 1], path[0]); // ritorno alla città iniziale
	    return totalDistance;
	}
	public static int[][] readCitiesFromFile(String filename) throws IOException {
        List<int[]> cityList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            cityList.add(new int[]{x, y});
        }
        br.close();
        return cityList.toArray(new int[cityList.size()][]);
    }


	
	public static void printPath(int[] path) {
	    for (int i = 0; i < path.length; i++) {
	        System.out.print(path[i]);
	        if (i < path.length - 1) {
	            System.out.print(" - "); // trattino tra le città
	        }
	    }
	    System.out.println(); // a capo dopo aver stampato tutto il percorso
	}

	
	public static void permuta(int[] path, boolean[] used, int level) {
		if(level == path.length) {
			// base case
			printPath(path);
			
			 double len = lengthOfPath(path);
			 System.out.println("Length = " + len);
			 
			// aggiorna percorso migliore se più corto
			    if(len < bestLen) {
			        bestLen = len;
			        bestPath = path.clone();  // copia dell'array
			    }
			return;
		}
		// recursive step: try each unused city
		for(int i=0;i<path.length;i++) {
			if(!used[i]) {
				path[level] = i; // put city in the path
				used[i] = true;  // mark as used
				permuta(path,used, level +1);// go deeper
				used[i]= false; //backtrack
			} 
		}
	}
		
	public static void main (String [] args) {
		System.out.println("Hello World");
		System.out.println();
		
		for(int row=0;row< cities.length;row++) {
			System.out.println("City "+ row +": (" +cities[row][0] + ","+ cities[row][1] +")");
			
		}
		System.out.println();
		int[] naturalPath = {0,1,2,3};
		double pathLength = lengthOfPath(naturalPath);
		System.out.println("Total path length 0-1-2-3-0: " + pathLength);

		
		int n = cities.length; // for 4 cities 0,1,2,3
		int[] path = new int[n];
		boolean[] used = new boolean[n];
		System.out.println("Total Permutation: ");
		path[0] = 0; //starting city
		used[0] = true;
		permuta(path,used,1);
		
		System.out.println("\nShortest path found:");
        printPath(bestPath);
        System.out.println("Shortest length = " + bestLen);
        System.out.println();
		
        try {
            cities = readCitiesFromFile("cities.txt");
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
            return; // esce dal main se il file non c'è
        }

		 System.out.println("Cities:");
	        for(int i=0; i<cities.length; i++) {
	            System.out.println("City " + i + ": (" + cities[i][0] + "," + cities[i][1] + ")");
	        }
	        System.out.println();
		
	}
}	

