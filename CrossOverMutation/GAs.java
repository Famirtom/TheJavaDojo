package Ese;

import java.util.Random;

public class GAs {
	final static int ALPHABET_SIZE=10;
	final static int POPULATION_SIZE = 8;
	final static int MEMBER_SIZE = 5;
	//final static int NUMBER_GENERATIONS = 5;	final static int BEST_PARENT_NUMBER = 2;	final static float MUTATION_RATE = (float)0.0;
	//final static int NUMBER_GENERATIONS = 5;	final static int BEST_PARENT_NUMBER = 4;	final static float MUTATION_RATE = (float)0.1;
	//final static int NUMBER_GENERATIONS = 100;	final static int BEST_PARENT_NUMBER = 4;	final static float MUTATION_RATE = (float)0.1;
	final static int NUMBER_GENERATIONS = 100;	final static int BEST_PARENT_NUMBER = 4;	final static float MUTATION_RATE = (float)0.05;
	/*
	*/

	/*
	final static int POPULATION_SIZE = 40;
	final static int MEMBER_SIZE = 20;

	final static int NUMBER_GENERATIONS = 50;	final static int BEST_PARENT_NUMBER = 4;	final static float MUTATION_RATE = (float)0.05;
	*/
	
	
	private static int [][] population = new int [POPULATION_SIZE][MEMBER_SIZE];
	private static int [][] newPopulation = new int [POPULATION_SIZE][MEMBER_SIZE];
	private static int newPopulationOffset = 0;
	private static Random randomGenerator = new Random(3);
	
	
	private static void initPopulation() {
		for (int memberNumber = 0; memberNumber< POPULATION_SIZE; memberNumber ++ ) {
			for (int geneNumber = 0; geneNumber< MEMBER_SIZE; geneNumber ++ ) {
				population[memberNumber][geneNumber] = randomGenerator.nextInt(ALPHABET_SIZE);
			}
		}
	}

	private static void printPopulation(int[][] printPopulation) {
		for (int memberNumber = 0; memberNumber< POPULATION_SIZE; memberNumber ++ ) {
			for (int geneNumber = 0; geneNumber< MEMBER_SIZE; geneNumber ++ ) {
				System.out.print(printPopulation[memberNumber][geneNumber] + " ");
			}
			int memberSum = getSum(printPopulation[memberNumber]);
			System.out.println(memberSum);
		}
	}
	
	private static int getSum (int member[]) {
		int sum = 0;
		for (int geneNumber = 0; geneNumber< MEMBER_SIZE; geneNumber ++ ) {
			sum += member[geneNumber];
		}
		
		return sum;
	}
	
	private static int[] getBestParents() {
		int[] bestParents = new int[BEST_PARENT_NUMBER];
		int[] bestParentValues = new int[BEST_PARENT_NUMBER];
		
		for (int memberNumber = 0; memberNumber< POPULATION_SIZE; memberNumber ++ ) {
			int memberSum = getSum(population[memberNumber]);
			if (memberSum > bestParentValues[BEST_PARENT_NUMBER-1]) {
				int newRanking = 0;
				while (memberSum < bestParentValues[newRanking]) newRanking ++;
				//copy the old values down
				for (int changeValue = BEST_PARENT_NUMBER-1; changeValue > newRanking; changeValue--) {
					bestParentValues[changeValue] = bestParentValues[changeValue-1];
					bestParents[changeValue] = bestParents[changeValue -1];
				}
				bestParentValues[newRanking] = memberSum;
				bestParents[newRanking] = memberNumber;
				
			}

		}
		
		return(bestParents);
	}
	
	private static int[] getParents(int[] bestParents) {
		int[] newParents = new int[2];

		int momNumber = randomGenerator.nextInt(BEST_PARENT_NUMBER);
		newParents[0] = bestParents[momNumber];
		int dadNumber = momNumber;
		while (dadNumber == momNumber) {
			dadNumber = randomGenerator.nextInt(BEST_PARENT_NUMBER);
		}
		newParents[1] = bestParents[dadNumber];
		
		//System.out.println(newParents[0] + " " + newParents[1]);
		return(newParents);
		
	}

	
	private static void crossOver(int mom, int dad) {
		int crossOverPoint = randomGenerator.nextInt (MEMBER_SIZE - 1);
		crossOverPoint++;  //get a crossover point between 1 and 1 before the end
				
		for (int geneNumber= 0; geneNumber < crossOverPoint; geneNumber++) {
			newPopulation[newPopulationOffset][geneNumber]= population[mom][geneNumber];
			newPopulation[newPopulationOffset+1][geneNumber]= population[dad][geneNumber];
		}
		for (int geneNumber= crossOverPoint; geneNumber < MEMBER_SIZE; geneNumber++) {
			newPopulation[newPopulationOffset][geneNumber]= population[dad][geneNumber];
			newPopulation[newPopulationOffset+1][geneNumber]= population[mom][geneNumber];
		}
		newPopulationOffset += 2;
	}
	
	
	private static void mutate () {
		for (int memberNumber = 0; memberNumber< POPULATION_SIZE; memberNumber ++ ) {
			for (int geneNumber = 0; geneNumber< MEMBER_SIZE; geneNumber ++ ) {
				float mutateChoice = randomGenerator.nextFloat();
				if (mutateChoice < MUTATION_RATE) {
					newPopulation[memberNumber][geneNumber]=randomGenerator.nextInt(ALPHABET_SIZE);
				}
			}
		}
		
	}
	
	private static void moveNewPopulation() {
		for (int memberNumber = 0; memberNumber< POPULATION_SIZE; memberNumber ++ ) {
			for (int geneNumber = 0; geneNumber< MEMBER_SIZE; geneNumber ++ ) {
				population[memberNumber][geneNumber] = newPopulation[memberNumber][geneNumber];
			}
		}
		newPopulationOffset = 0;
	}
	
	private static void getNewPopulation() {
		int[] bestParents = getBestParents();
		
		for (int crossOverAttempt = 0; crossOverAttempt < POPULATION_SIZE/2; crossOverAttempt ++) {
			int [] newParents = getParents(bestParents);
			int mom = newParents[0];
			int dad = newParents[1];
			crossOver(mom,dad);	
		}		
		mutate();
		moveNewPopulation();
	}
	
	public static void main(String[] args) {
		System.out.println("hw");
		initPopulation();
		printPopulation(population);
		for (int generation = 0; generation < NUMBER_GENERATIONS; generation ++ ) {
			getNewPopulation();
			System.out.println("---- " + generation);

			printPopulation(population);
		}
		System.out.println("gbcw");
	}

}
