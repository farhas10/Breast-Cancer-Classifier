import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

/**
 * Name: Farzad Hasan
 * Period: 1
 * Project: BreastCancerClassify
 * Date last updated: 9/21/2023
 * 
 * Use this class for your personal jUnit test suite.
 *
 */


public class BreastCancerClassifyTest {
 
	//Test to make sure that if all values are 0, the distance is also 0.
	@Test
	public void testCalculateDistance1() {
		int [] first = {0,0,0,0};
		int [] second = {0,0,0,0};
		double value = BreastCancerClassify.calculateDistance(first, second);
		assertEquals(0.0, value, 0.0);
	}
	
	//Test to make sure the correct distance is calculated when features are present.
	@Test 
	public void testCalculateDistance2() {
		int [] first = {1,3,4,1,2,2,4};
		int [] second = {0,2,3,2,1,4,5};
		double value = BreastCancerClassify.calculateDistance(first, second);
		assertEquals(Math.sqrt(8), value, 0.1);
	}
	
	//Test to make sure a data set calculates a distance of 0 if there are no extra features.
	@Test
	public void testCalculateDistance3() {
		int [] first = {1};
		int [] second = {2};
		double value = BreastCancerClassify.calculateDistance(first, second);
		assertEquals(0, value, 0);	
	}
	
	//Test to see what happens if all the values are 0.
	@Test
	public void testGetAllDistances1() {
		int [][] trainData = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
		};
		int [] testData = {0,0,0,0};
		double [] distances = BreastCancerClassify.getAllDistances(trainData, testData);
		double [] result = {0,0,0,0};
		assertArrayEquals(result, distances,0.0001);
	}
	
	//Test case to see if the distances are correctly being compiled.
	@Test
	public void testGetAllDistances2() {
		int [][] trainData = {
				{118831,2,1,2},
				{109384,3,9,4},
				{481983,4,8,4},
				{983882,5,7,4}
		};
		int [] testData = {1,2,4,3};
		double [] distances = BreastCancerClassify.getAllDistances(trainData, testData);
		double [] result = {3,Math.sqrt(26),Math.sqrt(20),Math.sqrt(18)};
		assertArrayEquals(result,distances,0.00001);
	}
	
	//Tests to see if the correct output is returned assuming no data is in the database to train
	//on.
	@Test
	public void testGetAllDistances3() {
		int [][] trainData = {
				{}
		};
		int [] testData = {5,4,2,2,4};
		double [] distances = BreastCancerClassify.getAllDistances(trainData, testData);
		double [] result = {0};
		assertArrayEquals(result, distances,0.0001);
	}
	
	//Checks to see if findKClosestEntries is properly working when given an array to sort.
	@Test
	public void testFindKClosestEntries1() {
		double [] allDistances = {0,2,3,4,9,6,7};
		int [] indexes = BreastCancerClassify.findKClosestEntries(allDistances);
		int [] result = {0,1,2,3,5};
		assertArrayEquals(result, indexes);
		
	}
	
	//Checks to see that no distances except 0 are returned when given an empty data set.
	@Test
	public void testFindKClosestEntries2() {
		double [] allDistances = {};
		int [] indexes = BreastCancerClassify.findKClosestEntries(allDistances);
		int [] result = {0,0,0,0,0};
		assertArrayEquals(result, indexes);
	}
	
	//Checks to see if distances are properly returned if an array is too short or below the value
	//of the K constant.
	@Test
	public void testFindKClosestEntries3() {
		double [] allDistances = {9,2,3};
		int [] indexes = BreastCancerClassify.findKClosestEntries(allDistances);
		int [] result = {1,2,0,0,0};
		assertArrayEquals(result, indexes);
	}
	
	//Checks to see if the search algorithm appropriately will handle repeated values.
	@Test
	public void testFindKClosestEntries4() {
		double [] allDistances = {5,2,3,4,5,2,9,7,8};
		int [] indexes = BreastCancerClassify.findKClosestEntries(allDistances);
		int [] result = {1,5,2,3,4};
		assertArrayEquals(result, indexes);
	}
	
	//Tests if the test data is being properly classified.
	@Test
	public void testClassify1() {
		int [][] dataPoints = {
				{190843, 3, 2},
				{827381, 4, 4},
				{377187, 5, 2},
				{746810, 2, 2},
				{847291, 1, 4}
		};
		int [] comparisonPoint = {2,1,4,3,0};
		int result = BreastCancerClassify.classify(dataPoints, comparisonPoint);
		assertEquals(2,result);
	}
	
	//Tests if the test data is being properly classified even when there are an equal number of
	//benign or malignant. It will just go with malignant.
	@Test
	public void testClassify2() {
		int [][] dataPoints = {
				{190843, 3, 2},
				{827381, 4, 4},
				{377187, 5, 2},
				{746810, 2, 2},
				{847291, 1, 4},
				{846571, 1, 4},
		};
		int [] comparisonPoint = {2,1,4,3,0,5};
		int result = BreastCancerClassify.classify(dataPoints, comparisonPoint);
		assertEquals(4,result);
		}
	
	//Checks if kNearestNeighbors still works when given the minimum amount of data.
	@Test
	public void testKNearestNeighbors() {
		int [][] data = {
				{2847938,4,4},
				{3783891,2,4},
				{8378791,9,2},
				{8239133,1,2},
				{3546261,0,2}
		};
		int [][] toBeClassified = {
				{3781738,0,2},
				{7488172,1,2}
		};
		int [] results = BreastCancerClassify.kNearestNeighbors(data, toBeClassified);
		int [] expected = {2,2};
		assertArrayEquals(expected, results);
	}
	
	//Tests the getAccuracy method with a regular data set.
	@Test
	public void testGetAccuracy1() {
		int [][] data = {
				{2847938,4,4},
				{3783891,2,4},
				{8378791,9,2},
				{8239133,1,2}
		};
		int [] classes = {2,4,2,4};
		String percent = BreastCancerClassify.getAccuracy(classes, data);
		String answer = "50.00%";
		assertEquals(percent, answer);
	}
	
	//Tests the getAccuracy method if nothing is given to it (empty arrays).
	@Test
	public void testGetAccuracy2() {
		int [][] data = {};
		int [] classes = {};
		String percent = BreastCancerClassify.getAccuracy(classes, data);
		String answer = "NaN%";
		assertEquals(percent, answer);
	}
	
}
