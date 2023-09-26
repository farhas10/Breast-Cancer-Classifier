
/**
 * Name: Farzad Hasan
 * Period: 1
 * Project: BreastCancerClassify
 * Date last updated: 9/21/2023
 *
 * This class handles reading and writing test data from a file.
 *
 */
 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * InputHandler processes all input files and also prints the accuracy of results.
 */
public class InputHandler
{
     /**
     * Returns a two dimensional int array corresponding to a csv file (defined by filename) of
     * ints.
     * 
     * The method takes in a .csv file, finds the length, and goes through each line.
     * While going through each line, the string is split, integer parsed, and stored in an int 
     * array. All of these arrays are then stored in a 2D array.
     */
    
	public static int[][] populateData(String filename)
    {
        int [][] dataset = null;
    	try {
    	BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        int numOfLines = 0;
        while (br.readLine() != null) {
        	numOfLines++;
        }
        br.close();
        dataset = new int [numOfLines][11];
        BufferedReader br2 = new BufferedReader(new FileReader(new File(filename)));
        for (int i = 0; i < numOfLines; i++) {
        	String data = br2.readLine();
        	String[] dataraw =data.split(",");
        	
        	int [] arrayOfVals = new int[dataraw.length];
        	for (int v = 0; v< dataraw.length; v++) {
        		int value = Integer.parseInt(dataraw[v]);
        		arrayOfVals[v]=value;
        		
        	}
        	dataset[i]=arrayOfVals;

        }
        br2.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return dataset;
    }

}
