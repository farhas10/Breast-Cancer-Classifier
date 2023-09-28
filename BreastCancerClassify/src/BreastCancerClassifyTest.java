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
	
	@Test
	public void testGetAllDistances2() {
		
	}
	
	
	
}
