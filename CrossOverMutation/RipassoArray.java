package Ese;

public class RipassoArray {
	
	// allora la mia logica è creo una classe publica statica int[]  makeGene 
	// Con return gene quindi mi ritorna sempre il gene quindi l'array
	// il gene entra nel loot e per ogni valore che ha viene asseganto un valore
	// da 0 a 9 quindi ovgni volta che chiamo la classe makeGene creo e chiamo 
	// l'array gene e posso utilizzarlo quante volte voglio.
	public static int[] makeGene() {
		int [] gene = new int [20];
		for(int i=0; i<gene.length; i++) {
			gene[i] = (int)(Math.random() * 10);
		}
		return gene;
	}
	// metodo per popolazione un array di 2 dimensioni
	public static int[][] popolazione(){
		int [][] pop = new int[10][20];
		for(int row=0; row<pop.length; row++) {
			// qui dichiare che le righe sono popolate da makeGene() method.
			// in questo modo genera automaticamente i 20 elementi.
			pop[row] = makeGene();
			for(int col=0; col<pop[row].length; col++) {
				//System.out.print(pop [row][col] +" ");
			}
			//System.out.println();
		}
		return pop;
	}

	public static double MaxSomma(int [][] popolazione) {
		double MaxSomma= 0;
		int indiceMax =0;
		for(int i=0; i<popolazione.length; i++) {
			double SommaGene = somma(popolazione[i]);
			if(SommaGene > MaxSomma) {
				MaxSomma = SommaGene;
				indiceMax = i;
			}		
		}
		System.out.println("Il gene migliore è il numero " + indiceMax);
		return MaxSomma;
	}
	public static double somma(int[] gene) {
		double somma = 0;
		for(int i=0; i<gene.length;i++) {
			somma= somma+ gene[i];
		}
		return somma;
	}
	
	
	public static void main(String [] args) {
		
		int [][]pop = popolazione();
		for(int row=0; row<pop.length; row++) {
			for(int col=0; col<pop[row].length; col++) {
				System.out.print(pop [row][col] +" ");
			}
			System.out.println();
		}
		
		System.out.println("La somma più altà è: " + MaxSomma(pop));
		for(int i=0; i<pop.length;i++) {
			System.out.println("Gene " + i+  " somma= " + somma(pop[i]));
		}
	}

}
