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
 *  5) kNearestNeighbors (use your helpers correctly!)
 *  6) getAccuracy
 */
public class BreastCancerClassify {
	
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
		double[] allDistances = new double[trainData.length];
		for (int i = 0; i < trainData.length; i++) {
			allDistances[i] = calculateDistance(trainData[i], testInstance);
		}
		return allDistances;
	}
	
	private static boolean containsEntryValue(int l, int v, int [] indexes) {
		for (int i = 0; i< indexes.length; i++) {
			if ((l == 0) && (v == 0)) {
				return true;
			}
			if (indexes[i]==v) {
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
	 */
	public static int[] findKClosestEntries(double[] allDistances)
	{
		int[] kClosestIndexes = new int[K];
		for (int i = 0; i < K; i++) {
			double minIndex = Double.MAX_VALUE;
			for (int v = 0; v < allDistances.length; v++) {
				if ((allDistances[v] < minIndex) && containsEntryValue(i, v, kClosestIndexes)) {
					minIndex = allDistances[v];
					kClosestIndexes[i] = v;
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
	 */
	public static int classify(int[][] trainData, int[] kClosestIndexes)
	{
		int benign = 0;
		int malignant = 0;
		for (int index : kClosestIndexes) {
			if (trainData[index][trainData[index].length-1] == BENIGN) {
				benign++;
			}
			if (trainData[index][trainData[index].length-1] == MALIGNANT) {
				malignant++;
			}
		}
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
	 */
	public static int[] kNearestNeighbors(int[][] trainData, int[][] testData){
		int[] myResults = new int [testData.length];
		for (int i = 0; i < testData.length; i++) {
			double [] distances = getAllDistances(trainData, testData[i]);
			int [] KClosestIndexes = findKClosestEntries(distances);
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
	 */
	public static String getAccuracy(int[] myResults, int[][] testData) {
		double accurate = 0;
		for (int i = 0; i<testData.length; i++) {
			if (myResults[i] == testData[i][testData[i].length-1]) {
				accurate ++;
			}
		}
		return accurate/testData.length + "%";
	}
	
	
	//DO NOT MODIFY THE MAIN METHOD
	public static void main(String[] args) {
		
		int[][] trainData = InputHandler.populateData("./datasets/train_data.csv");
		int[][] testData = InputHandler.populateData("./datasets/test_data.csv");
		
		//Display the distances between instances of the train data. 
		//Points in the upper left corner (both benign) or in the bottom
		//right (both malignant) should be darker. 
		//Grapher.createGraph(trainData);

		int[] myResults = kNearestNeighbors(trainData, testData);

		System.out.println("Model Accuracy: " + getAccuracy(myResults, testData));
	}

}
