package Ese;

public class geneEsercizio {

	public static int[][] makePopulation(){
		int[][] population = new int [10][20];
		for(int r=0; r<population.length; r++) {
			population[r]= makeGene();
		}
		return population;
	}
	public static void printGene(int [] gene) {
		for(int i=0; i<gene.length; i++) {
			System.out.print(gene[i] + " ");
		}
		System.out.println();
	}
	
	public static void printPopulation(int [][] population) {
		for(int r=0; r<population.length; r++) {
			for(int c=0; c<population[r].length; c++){
			System.out.print(population[r][c]+ " ");
			}
			System.out.println();
		}
	}
	public static int sumPopulation(int [][] population) {
		int maxsum = 0;
		int bestindex= 0;
		for(int i =0; i<population.length; i++) {
			int currentsum = sumGene(population[i]);
			if(currentsum > maxsum) {
				maxsum = currentsum;
				bestindex =i;
			}
		}
		System.out.println("Best gene is the number: " + bestindex);
		return maxsum;
	}
	public static int minGene(int[][] population) {
		int minGene = sumGene(population[0]);
		int index =0;
		for(int i=0; i<population.length; i++) {
			int currentsum =  sumGene(population[i]);
			if(currentsum < minGene) {
				minGene = currentsum;
				index=i;
			}
		}
		System.out.println("Il gene migliore (minimo) è alla posizione: " + index);
	    return minGene;
	}
	public static void replaceWorstGene(int [][] population, int [] child) {
		int worstGene = sumGene(population[0]);
		int worstIndex = 0;
		for(int i=0; i< population.length; i++) {
			 int currentSum = sumGene(population[i]);
			    if (currentSum < worstGene) {
			        worstGene = currentSum;
			        worstIndex = i;
			    }
		}
			if(sumGene(child) > worstGene) {
				population[worstIndex] = child;
				System.out.println("sostituisce il gene preggiore con il figlio");
			}
		System.out.println("worst gene in place: "+ worstIndex);
	}
	
	public static int [] crossover(int[] a, int[] b) {
		// dichiaro una variabile cut int che è uguale ad una posizione casuale dell'array
		int cut = 1+ (int)(Math.random() * (a.length -1));
		// poi dichiaro un array child che è uguale alla lungheza di a in questo caso 20
		int [] child = new int[a.length];
		for(int i=0; i<child.length;i++) {
			//se la lunghezza dell'array è più grande di cut allora in quelle posizioni
			// prendi i valori di a ed assegnali a child
			if(i< cut) {
				child[i]= a[i];
				// mentre quando non è più grande assegna quelli di b
			}else {
				child[i]= b[i];
			}
		}
		// stampami la posizione del
		System.out.println("Taglio effettuato in posizione: " + cut);
		// tornami l'array child
		return child;
	}
	public static int sumGene(int[] gene) {
		int sum=0;
		for(int i=0; i<gene.length; i++) {
			sum = sum + gene[i];
		}
		return sum;
	}
	
	public static int[] makeGene() {
		int[] gene = new int[20];
		for(int i=0; i<gene.length; i++) {
			gene[i]=(int)(Math.random()*10);
		}
		return gene;
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
	public static void main(String [] args) {
		//int[] gene = makeGene();
		//int [] mutate = mutateGene(gene, 0.2);
		int[][] pop = makePopulation();
		// per stamaprae il crossover devo dichiarare 2 array 
		int[] a = pop[0];
		int[] b	= pop[3];

		
		System.out.println("Original population: ");
		printPopulation(pop);
		System.out.println();
		
		System.out.println("Original: ");
		printGene(pop[3]);
		pop[3] = mutateGene(pop[3], 0.3);
		System.out.println("Mutate: ");
		printGene(pop[3]);
		
		System.out.println();
		//printGene(gen);
		
		System.out.println("Somma del gene in posizione 0: ");
		System.out.println(sumGene(pop[0]));
		System.out.println();
		
		System.out.println("Minore " + minGene(pop));
		System.out.println();
		
		System.out.println("Best somma " + sumPopulation(pop));
		System.out.println();
		System.out.println("Gene in posizione 0");
		printGene(a);
		System.out.println("Gene in posizione 3");
		printGene(b);
		int [] child = crossover(a,b);
		System.out.println("Child gene");
		printGene(child);
		System.out.println();
		
		replaceWorstGene(pop,child);
		System.out.println();
		System.out.println("population after replacement");
		printPopulation(pop);
		
	}
}
