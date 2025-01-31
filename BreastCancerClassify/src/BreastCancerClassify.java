import java.util.Arrays;

/**
 * Name: Farzad Hasan
 * Period: 1
 * Project: BreastCancerClassify
 * Date last updated: 9/21/2023
 * 
 * BreastCancerClassify contains the core implementation of the 
 * kNearestNeighbors algorithm to classify cell clumps as malignant
 * or benign. 
 * 
 * It is suggested that you work on the methods in the following order:
 * 	1) calculateDistance - once you finish this, you should see a
 * 	   graph of distances appear!
 * 	2) getAllDistances
 * 	3) findKClosestEntries
 * 	4) classify
 *  5) kNearestNeighbors (use your helpers correctly)
 *  6) getAccuracy
 */
public class BreastCancerClassify {
	
	//Class constants which are used throughout the code to increase adaptability.
	public static final Integer K = 5;
    public static final Integer BENIGN = 2;
    public static final Integer MALIGNANT = 4;
	
	/**
	 * calculateDistance computes the distance between the two data
	 * parameters. 
	 * integer variable dist is the sum of distances, taken by squaring the difference of each
	 * index.
	 * 
	 * code loops through an array from index 1 to any length - 1 to locate all 9 features.
	 */
    
	public static double calculateDistance(int[] first, int[] second)
	{	
		int dist = 0;
		//Just loops through the length of the list and uses distance formula to find distance.
		//The values are summed in the integer variable, dist.
		for (int i = 1; i < first.length -1; i++) {
			dist += Math.pow(first[i] - second[i], 2);
		}
		return Math.sqrt(dist);

	}
	
	/**
	 * getAllDistances creates an array of doubles with the distances
	 * to each training instance. The double[] returned should have the 
	 * same number of instances as trainData. 
	 * 
	 * The method loops through every row in the trainData data set and calls calculateDistance
	 * to find each distance. The values are stored in the array of doubles called allDistances.
	 */
	public static double[] getAllDistances(int[][] trainData, int[] testInstance)
	{
		//New array created which is the length of the trainData.
		double[] allDistances = new double[trainData.length];
		//Loops through the length of trainData to find all the distances from the testInstance to
		//the current point the loop is on. Each distance is stored in the allDistances array.
		for (int i = 0; i < trainData.length; i++) {
			allDistances[i] = calculateDistance(trainData[i], testInstance);
		}
		return allDistances;
	}
	
	/*
	 * This is a separate helper method I made which intakes an array of indexes and checks
	 * whether the current index in the loop from the previous method (findKClosestEntries)
	 * has already been checked.
	 */
	private static boolean containsEntryValue(int searchNumber, int currentIndex, int [] foundIndexes) {
		//First loop which goes through the array of the existing indexes.
		for (int i = 0; i< foundIndexes.length; i++) {
			//l tracks the current iteration of the search (1-5).
			//v tracks the index being searched in findKClosestEntries.
			if ((searchNumber == 0) && (currentIndex == 0)) {
				return true;
			}
			//if the same index is found, returns false.
			if (foundIndexes[i]==currentIndex) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * findKClosestEntries finds and returns the indexes of the 
	 * k closest distances in allDistances. Return an array of size K, 
	 * that is filled with the indexes of the closest distances (not
	 * the distances themselves). 
	 * 
	 * Be careful! This method can be tricky.
	 * 
	 * This method finds the K number of minimum values in a set of distances.
	 * To do this, I first set a value as the "minimum" for the loop to begin comparing indexes
	 * too. After each time I find a minimum, I record the indicie of that value in a different
	 * array. I then call on a helper method who checks whether or not my index has already been
	 * compared too, and if it has, it skips that index. This process repeats K times as it must
	 * find K minimum values.
	 */
	public static int[] findKClosestEntries(double[] allDistances)
	{
		//New array kClosestIndexes is made to the length of the class constant.
		int[] kClosestIndexes = new int[K];
		for (int searchNumber = 0; searchNumber < K; searchNumber++) {
			//for the initial state of the minimum value, I set it to the maximum possible value
			//as I get an error when the minValue is initially 0 in the case where it is also the 
			//lowest number in the array.
			double minValue = Double.MAX_VALUE;
			for (int currentIndex = 0; currentIndex < allDistances.length; currentIndex++) {
				//This first checks whether the value at the current distance is less than the min
				//value. Then it calls on the helper method to also check if the index has already
				//been stored as a minimum index.
				if ((allDistances[currentIndex] < minValue) && containsEntryValue(searchNumber, currentIndex, kClosestIndexes)) {
					//Stores the index of the value IF it is a minimum.
					minValue = allDistances[currentIndex];
					kClosestIndexes[searchNumber] = currentIndex;
				}
			}
		}
		return kClosestIndexes;
	}
	
	/**
	 * classify makes a decision as to whether an instance of testing 
	 * data is BENIGN or MALIGNANT. The function makes this decision based
	 * on the K closest train data instances (whose indexes are stored in 
	 * kClosestIndexes). If more than half of the closest instances are 
	 * malignant, classify the growth as malignant. Otherwise classify
	 * as benign.
	 * 
	 * Return one of the global integer constants defined in this function. 
	 * 
	 * The classify method creates two variables to count each occurences of a benign or malign
	 * sample. It then returns the value with the higher number of occurences.
	 */
	public static int classify(int[][] trainData, int[] kClosestIndexes)
	{
		//two counters are put here to count the number of times malignant or benign is the
		//classification.
		int benign = 0;
		int malignant = 0;
		//checks every value in kClosestIndexes to see if it is benign or not.
		for (int index : kClosestIndexes) {
			//will increment by 1 if benign.
			if (trainData[index][trainData[index].length-1] == BENIGN) {
				benign++;
			}
			//will increment by 1 if malignant
			if (trainData[index][trainData[index].length-1] == MALIGNANT) {
				malignant++;
			}
		}
		//compares to check which one is more prevalent.
		if (benign > malignant) {
			return BENIGN;
		}
		else {
			return MALIGNANT;
		}
	}
	
	/**
	 * kNearestNeighbors classifies all the data instances in testData as 
	 * BENIGN or MALIGNANT using the helper functions you wrote and the kNN 
	 * algorithm.
	 * 
	 * For each instance of your test data, use your helper methods to find the
	 * K closest points, and classify your result based on that!
	 * @param trainData: all training instances
	 * @param testData: all testing instances
	 * @return: int array of classifications (BENIGN or MALIGNANT)
	 * 
	 * The code creates an array, myResults which stores the states of all the kNearestNeighbors.
	 * The method then calls on all previous methods to find the information as needed before.
	 */
	public static int[] kNearestNeighbors(int[][] trainData, int[][] testData){
		//New array made to store all the classifications that will be made.
		int[] myResults = new int [testData.length];
		for (int i = 0; i < testData.length; i++) {
			//Loops for the length of the array called testData
			//First stores all the distances from calling the getAllDistances method.
			double [] distances = getAllDistances(trainData, testData[i]);
			//Stores the indexes of the KClosestIndexes from findKClosestEntries.
			int [] KClosestIndexes = findKClosestEntries(distances);
			//Calls on classify to classify all the data points in trainData.
			int state = classify(trainData, KClosestIndexes);
			myResults[i]=state;
		}
		return myResults;
	}

	/**
	 * getAccuracy returns a String representing the classification accuracy.
	 *
	 * The retun String should be rounded to two decimal places followed by the % symbol.
	 * Examples:
	 * If 4 out of 5 outcomes were correctly predicted, the returned String should be: "80.00%"
	 * If 3 out of 9 outcomes were correctly predicted, the returned String should be: "33.33%"
	 * If 6 out of 9 outcomes were correctly predicted, the returned String should be: "66.67%"
	 * Look up Java's String Formatter to learn how to round a double to two-decimal places.
	 *
	 * This method should work for any data set, given that the classification outcome is always
	 * listed in the last column of the data set.
	 * @param: myResults: The predicted classifcations produced by your KNN model
	 * @param: testData: The original data that contains the true classifications for the test data
	 * 
	 * getAccuracy checks how many of the strings are correctly classified when compared to the 
	 * testData or the "answers."
	 * 
	 * The method works by checking the last index of each array in the testData to find whether
	 * it is benign or malignant. Then it compares and will increment the accuracy variable.
	 * 
	 */
	
	
	public static String getAccuracy(int[] myResults, int[][] testData) {
		//Value which counts the number of accurate classifications.
		double accurate = 0;
		for (int i = 0; i<testData.length; i++) {
			//Checks the testData against myResults to see if the classification is correct.
			//If so, accurate is incremented by 1. This loops for the length of the list.
			if (myResults[i] == testData[i][testData[i].length-1]) {
				accurate ++;
			}
		}
		
		//Properly formatted print statement.
		return String.format("%.2f", accurate/testData.length*100) + "%";
	}
	
	
	//DO NOT MODIFY THE MAIN METHOD
	public static void main(String[] args) {
		
		int[][] trainData = InputHandler.populateData("./datasets/train_data.csv");
		int[][] testData = InputHandler.populateData("./datasets/test_data.csv");
		
		//Display the distances between instances of the train data. 
		//Points in the upper left corner (both benign) or in the bottom
		//right (both malignant) should be darker. 
		Grapher.createGraph(trainData);

		int[] myResults = kNearestNeighbors(trainData, testData);

		System.out.println("Model Accuracy: " + getAccuracy(myResults, testData));
	}

}
