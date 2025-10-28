package Ese;

public class Gene {	
	// 1 solo loop in quanto l'array è 10X20 i geni hanno 20 parametri quindi non 
	// ho bisogno di riscrivere un altro
	// loop ma solo se voglio printarlo 
	public static int[][] makePopulation(){
		int[][]population = new int [10][20];
		for(int r=0; r<population.length; r++) {
			population[r]= makeGene();
		}	
		return population;
	}
	
	// metodo per stampare population 
	// void perchè non ritorna nnt ma è un metodo solo per stampare la popolazione
	//per stamparlo nel main devo fare nome del metodo(variabile).
	public static void  printPopulation(int [][]population ){
		for(int r=0; r<population.length;r++) {
			for(int c=0; c<population[r].length; c++) {
				System.out.print(population[r][c]);
			}
			System.out.println();
		}
	}
	// la somma dei valori del gene 
	// ho dichiarato un metodo int summGene dato come parametro int [] gene 
	// summ iniziale è 0 
	// creato un loop che esplora tutti e 20 le posizioni.
	// summ per ogni ciclo addizione i parametri di gene
	// il metodo mi restituisce summ.
	public static int summGene(int[] gene) {
		int summ = 0;
		for(int i=0; i<gene.length; i++) {
			summ = summ+ gene[i];
		}
		return summ;
	}
	// motedo per trovare il migliroe gene
	// paramentro dato è population 
	
	public static int bestGene(int [][] population) {
		int maxSumm = 0;
		// questo mi da l'index del migliore gene
		int indexBest = 0;
		// per ogni gene nella popolazione
		for(int i=0; i<population.length; i++) {
			// somma corrente è uguale alla somma di quel gene 
			int currentSumm= summGene(population[i]);
			// se la somma corretne è più grande di maxsumm allora maxSumm divneta la somma corrente
			// mettendo nel loop questo precesso si ripete per ogni riga
			// fino a trovare il valore più alto 
			if(currentSumm > maxSumm) {
				maxSumm= currentSumm;
				indexBest = i;
			}
		}
		System.out.println("Best gene is the number: "+ indexBest);
		return maxSumm;
	}
	public static int[] mutateGene(int[] gene, double rate) {
		for(int i=0; i<gene.length; i++) {
			if(Math.random() < rate) { // probabilità di mutazione
				int newValue = (int)(Math.random()*10); // nuova cifra
				gene[i]=newValue; // sostituisco con la vecchia
			}
		}
		
		/*
		// mutationIdex è uguale ad una posizione random del gene
		int mutationIndex = (int)(Math.random()* gene.length);
		// assegno al nuovo valore un numero random da 0  a 9
		int newValue = (int)(Math.random()* 10);
		// dichiaro che nella posizione di mutationIndex del gene sostituisco il suo valore con
		// quello di newValue.
		gene[mutationIndex] = newValue;	
		
		// ritorno il gene mutato
		 */
		return gene;		
	}
	public static void  printGene(int [] gene) {
		for(int i=0; i<gene.length; i++) {
			System.out.print(gene[i]+ " ");
		}
		System.out.println();
	}
	
	public static int[] makeGene(){
		int []  gene = new int[20];
		for(int i=0; i<gene.length; i++) {
			gene[i]= (int)(Math.random()* 10);
		}
		return gene;
	}
	
	
	public static void main(String [] args) {
		
		int [] gene = makeGene();
		int [] mutated = mutateGene(gene, 0.2);
		int [][] pop = makePopulation();
		// come si stampa con void main in quanto void non ritorna nnt 
		System.out.println("Population: ");
		printPopulation(pop);
		
		System.out.println();
		System.out.println("Original: ");
		printGene(gene);
		System.out.println("Mutate: ");
		printGene(mutated);
		
		System.out.println();
		System.out.println("Best summ is :" + bestGene(pop));
	}
	
}
