
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
        ArrayList <int[] >data = new ArrayList<int[]>();
    	try {
    	BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
    	//Reads the line of text
        String vals= br.readLine();
        //will loop until the next line is empty.
        while (vals != null) {
        	//Array stores the string and splits it on the ",".
        	String[] dataraw = vals.split(",");
        	int [] arrayOfVals = new int[dataraw.length];
        	//Loop made to parse the array of strings for integers.
        	for (int v = 0; v< dataraw.length; v++) {
        		int value = Integer.parseInt(dataraw[v]);
        		arrayOfVals[v]=value;
        }
        	//Added to the array list
        	data.add(arrayOfVals);
        	//readline called again to start the cycle again.
        	vals=br.readLine();
   
        //this is a loop to transfer the array list items into a 2D array object.
        dataset = new int [data.size()][data.get(0).length];
        for (int i = 0; i < data.size(); i++) {
        	dataset[i]=data.get(i);
        	}
        }
        //closing br to prevent leaks.
        br.close();
    	}
    	
    	//exception block in case the file is not found or such.
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	//returns the data in a way that the program can read.
    	return dataset;
    }

}
