import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/** 
 * CST 3170 Machine Learning Coursework
 * This system implements multiple machine learning algorithms to classify handwritten digits:
 * 1. K-Nearest Neighbors (KNN) with Euclidean Distance 
 * 2. Multi-Layer Perceptron (MLP) Neural Network
 * 3. Generic algorithm 
 */


public class CourseWorkDigits {
	
		// Dataset configuration
		private static final String DATASET1_PATH = "src/dataSet1.csv";
		private static final String DATASET2_PATH = "src/dataSet2.csv";
		private static final int TOTAL_FEATURES = 65;
		private static final int INPUT_FEATURES = TOTAL_FEATURES -1;
		private static final int NUM_CLASSES = 10;
		
		// Neural network configuration
		private static final int HIDDEN_LAYER_SIZE = 32;
		private static final double LEARNING_RATE = 0.01;
		private static final int MAX_EPOCHS = 100;
		
		
		// Genetic algorithm configuration
		private static final int POPULATION_SIZE = 10;
		private static final int GA_GENERATIONS = 30;
		private static final double MUTATION_RATE = 0.1;
		
		private static int[] population = new int[POPULATION_SIZE];
		private static double[] fitness = new double[POPULATION_SIZE];
		private static int bestk = 1;
		private static double bestFitness = 0.0;
		
		
		private static int[][] data;
		private static Random random = new Random(42);
		
		/**
		 * Reads CSV file and converts it to a 2D integer array
		 * @param fileName
		 */
		
		public static void loadDataset(String fileName){
			ArrayList<int[]> dataList = new ArrayList<>();
			try {
				File fileHandle = new File(fileName);
				Scanner scanner = new Scanner(fileHandle);

				while(scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String [] values = line.split(","); 
					int[] row = new int[values.length]; 

					for(int col =0; col <values.length; col ++) { 
						row[col] = Integer.parseInt(values[col]); 
					}
					dataList.add(row); 
				}
				
				 data = new int[dataList.size()][TOTAL_FEATURES]; 
				for(int rowMatrix=0; rowMatrix< dataList.size(); rowMatrix++) {
					data[rowMatrix] = dataList.get(rowMatrix);
				}
				scanner.close();
			}
			catch(FileNotFoundException error){
				System.out.println("ERROR: File Not Found! " + error);
			}
		}
		
		/**
		 * Calculates Euclidean distance between two feature vectors
		 * Formula: sqrt(sum(xi - yi)^2))
		 * @param testInstance
		 * @param trainInstance
		 * @return Euclidean Distance
		 */
		public static double euclideanDistance(int [] testInstance, int [] trainInstance) {
			double sumSquareDifference=0;
			for(int feature = 0; feature < INPUT_FEATURES ;feature++) {
				double diff = testInstance[feature] - trainInstance[feature];
				sumSquareDifference = sumSquareDifference + (diff * diff);
			}
			return Math.sqrt(sumSquareDifference);
		}
		
		/**
		 * K-Nearest Neighbors Classification Algorithm
		 * Steps:
		 * 1. Calculate distances to all training points
		 * 2. Sort the k points using Bubble Sort
		 * 3. for each remaining training points:
		 * 	- Calculate distance to test point
		 * 	- If closer than the furthest of out k neighbors, replace it
		 * 	- Re/sort to maintain order using Insertion Sort
		 * 4. Count votes form k nearest neighbors
		 * 5. predict the class with most votes
		 * @param trainData training dataset
		 * @param testData test dataset
		 * @param k Number of neighbors to consider
		 * @param showConfusionMatrix whether to display confusion matrix 
		 * @return Classification accuracy as percentage
		 */
		public static double knnClassification(int[][] trainData, int[][] testData , int k, boolean showConfusionMatrix) {
			int totalTests = 0;
			int correctPredictions = 0;
			int [][] confusionMatrix = new int[NUM_CLASSES][NUM_CLASSES];
			
		
			for(int testIndex=0; testIndex < testData.length; testIndex++) {
				DistanceLabel [] kNearest = new DistanceLabel[k];
				
				// Calculate distances to all training points
				for(int trainIndex=0; trainIndex < k; trainIndex++) {
					double dist = euclideanDistance(testData[testIndex], trainData[trainIndex]);
					int label = trainData[trainIndex][INPUT_FEATURES];
					kNearest[trainIndex] = new DistanceLabel(dist, label);
				}
					
				// Sort initial k neighbors using bubble Sort
				bubbleSort(kNearest);
				
				// check remaining training points
				// Only keep them if they are closer than the furthest of our k neighbors
				for(int trainIndex = k; trainIndex < trainData.length; trainIndex ++) {
					double dist = euclideanDistance(testData[testIndex], trainData[trainIndex]);
					
					//if this point is close then the furthest neighbor
					if(dist < kNearest[k-1].distance) {
						int label = trainData[trainIndex][INPUT_FEATURES];
						kNearest[k-1]= new DistanceLabel(dist, label);
						
						// Re-Sort to maintain order
						insertionSortLast(kNearest);
					}
				}
				
				// Count votes from k nearest neighbors
				int[] classVotes =  new int[NUM_CLASSES];
				for( int neighbor =0 ; neighbor < k; neighbor ++) {
					int neighborLabel = kNearest[neighbor].label;
					classVotes[neighborLabel]++;
				}
				
				// Find class with most votes
				int predictedClass = 0;
				int maxVotes = classVotes[0];
				for(int classIndex = 0; classIndex < NUM_CLASSES; classIndex++) {
					if(classVotes[classIndex] > maxVotes) {
						maxVotes = classVotes[classIndex];
						predictedClass = classIndex;
					}
				}
				
				int actualClass = testData[testIndex][INPUT_FEATURES];
				confusionMatrix[actualClass][predictedClass]++;
				
				if(predictedClass == actualClass) {
					correctPredictions++;
				}
				totalTests++;
		}
			if(showConfusionMatrix) {
				printConfusionMatrix(confusionMatrix);
			}
			
			return (double) correctPredictions / totalTests * 100.0;
	}
			/**
			 * Genetic Algorithm for optimizing K value in KNN
			 */
		static class GeneticAlgorith{
			private int[]population;
			private double[]fitness;
		}
		
			
		/**
		 * Bubble Sort algorithm
		 * This algorithm will repeatedly steps through the array,
		 * compares adjacent elements and swap them if they are in wrong order.
		 * the pass through the array is repeated until the array is sorted
		 *@param array of DistanceLabel objects to sort by distance.
		 */
		private static void bubbleSort(DistanceLabel[] array) {
			int n = array.length;
			for(int i =0 ; i < n -1 ; i++) {
				for(int j = 0; j < n -1 ; j++) {
					if(array[j].distance > array[j +1].distance) {
						// swap elements
						DistanceLabel temp = array[j];
						array[j] = array[j+1];
						array[j+1] = temp;
					}
				}
			}
		}
		
		/** 
		 * Insertion Sort for the last element
		 * Is efficient as the array is already sorted.
		 * Used to maintain sorted order when we replace the furthest neighbor with a close one
		 * @param array Sorted array 
		 */
		private static void insertionSortLast(DistanceLabel[] array) {
			DistanceLabel key = array[array.length -1];
			int i = array.length -2;
			
			// move elements greater then key one position ahead
			while(i >= 0 && array[i].distance > key.distance) {
				array[i +1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
		
		/**
		 * Class to store distance and corresponding class label
		 */
		static class DistanceLabel{
			double distance;
			int label;
			
			DistanceLabel(double distance, int label){
				this.distance = distance;
				this.label = label;
			}
		}
		public static void printConfusionMatrix(int[][] matrix) {
			System.out.println("Confusion Matrix (Actual - Predicted)");
			System.out.println("-----------------------------------");
			
			System.out.printf("%8s", " ");
			for(int feature = 0; feature < NUM_CLASSES ; feature++) {
				System.out.printf(" %6d ", feature);
			}
			System.out.println();
			
			for(int actual =0; actual < NUM_CLASSES; actual ++) {
				System.out.printf("Actual %d | ", actual);
				for(int predicted = 0 ; predicted < NUM_CLASSES; predicted++) {
					System.out.printf("%8d",matrix[actual][predicted]);
				}
				System.out.println();
			}
			System.out.println("---------------------------------------------------------------");
			System.out.println("Rows = True Labels,  Columns = Predicted Labels");
		}
		
		public static void printData(int [][] data, int rows, int cols) {
			for(int row =0; row < rows; row++) {
				for(int col =0; col < cols; col++) {
					System.out.print(data[row][col]+ " ");
				}
				System.out.println();
			  }
		}
		
		
		
		public static void main(String [] args) {
			
			System.out.println("=================================================================");
			System.out.println("CST 3170 Machine Learning Coursework - MNIST Digit Classification");
			System.out.println("=================================================================");
			
			// Load datasets
			System.out.println("loading datasets ");
			loadDataset(DATASET1_PATH);
			int[][] dataset1 = data;
			loadDataset(DATASET2_PATH);
			int[][] dataset2 = data;
			System.out.println("Dataset1: " +  dataset1.length);
			System.out.println("Dataset2: "+ dataset2.length);
			
			// KNN WITH HYPERPARAMETER
			System.out.println("=================================================================");
			System.out.println("Algorithm 1: K-Nearest Neighbors (KNN)");
			System.out.println("Using bubble sort and Insertion Sort");
			System.out.println("=================================================================");
			System.out.println("Hyperparameter ANalysis (K values):");
			System.out.println("-----------------------------------");
			System.out.println(" K |  Fold1   |  Fold2   |  Avg Acc ");
			System.out.println("-----------------------------------");

			int bestk = 1;
			double bestAccuracy = 0.0;
			
			for(int k=1; k<= 15 ; k++) {
				double acc1 = knnClassification(dataset1, dataset2, k, false);
				double acc2 = knnClassification(dataset2, dataset1, k, false);
				double avgAccuracy = (acc1 + acc2) / 2.0;
				
				System.out.printf("%2d | %.2f%% | %.2f%% | %.2f%%\n", 
                        k, acc1, acc2, avgAccuracy);
				System.out.println("-----------------------------------");
				
				if(avgAccuracy > bestAccuracy	) {
					bestAccuracy = avgAccuracy;
					bestk = k;
				}
			}
			
			System.out.println("Best K value: " + bestk + " with accuracy: " + String.format("%.2f%%", bestAccuracy));
			System.out.println();
			System.out.println("-----------------------------------");
	        System.out.println("2-Fold Cross Validation (K=" + bestk + ")");
	        System.out.println("-----------------------------------");
	        double knnAcc1 = knnClassification(dataset1, dataset2, bestk, true);
	        double knnAcc2 = knnClassification(dataset2, dataset1, bestk, false);
	        double knnAvg = (knnAcc1 + knnAcc2) / 2.0;
	        System.out.printf("Fold 1 Accuracy: %.2f%%", knnAcc1);
	        System.out.println();
	        System.out.printf("Fold 2 Accuracy: %.2f%%", knnAcc2);
	        System.out.println();
	        System.out.printf("Average Accuracy: %.2f%%", knnAvg);
			
			
		
			
		}
}
