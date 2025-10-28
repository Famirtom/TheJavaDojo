
public class BruteForce2 {
	static char[] array = {'c','a','t'};

	public static void bruteForce(char[] path, boolean[] used, int level) {
		if(level == array.length) {
			for (int i = 0; i < path.length; i++) {
		        System.out.print(path[i]); // stampa senza andare a capo
		    }
		    System.out.println(); // a capo solo alla fine della permutazione
			return;
		}
		for(int i=0; i<array.length; i++) {
			if(!used[i]) {
				path[level]= array[i];
				used[i]=true;
				bruteForce(path,used,level+1);
				used[i]=false;
			}
		}
	}
	public static void main(String[] args) {
		char [] path = new char[3];
		boolean[] used = new boolean[array.length];
		System.out.println("BruteForce per trovare tutte le combinazioni di 'Cat': ");
		bruteForce(path,used,0);
	}
}
