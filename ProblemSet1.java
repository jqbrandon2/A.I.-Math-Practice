import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/************************************
Name:           Brandon Evans
Problem Set:    Problem Set 1
Due Date:       6-20-2023
************************************/


public class ProblemSet1 {
	
	public static void main(String[] args) {
		
		String studentName = "Brandon Evans";
		
		/***************************************************************
		 * DO NOT CHANGE ANYTHING ELSE BELOW THIS LINE IN THIS METHOD
		 ***************************************************************/
		
		/*
		 * Note:  If you use an IDE, you can set the command-line arguments for testing purposes through
		 * the IDE's runtime configuration. Simply ask if you do not know how to do this.
		 */
		
		if ( args.length < 2) {
			System.out.println("Invalid syntax.  Usage:  java ProblemSet1 filename colnumber");
			return;
		}
		
		try {
			
			ProblemSet1 ps = new ProblemSet1();
			
			String filename = args[0];
			int col = Integer.parseInt(args[1]);
			
			System.out.printf("[ Input arguments ] %n");
			System.out.printf("   Student Name ..................... %s %n", studentName);
			System.out.printf("   Input file ....................... %s %n", filename);
			System.out.printf("   Input column ..................... %d %n", col);
			
			System.out.println();
			
			
			/***************************************************************
			 * Calling step 1
			 ***************************************************************/
			
			System.out.println("[ Results ]");
			System.out.print("Step 1) Reading the file .......................... ");
			double[][] X = ps.readFile(filename);
			System.out.println("[done]");
			 
			int numColumns = (X != null && X[0] != null ? X[0].length : 0);
			System.out.printf("      Total number of records ..................... %d %n", (X != null ? X.length : 0) ) ;
			System.out.printf("      Total number of columns ..................... %d %n", numColumns ) ;
			System.out.println();
			
			
			/***************************************************************
			 * Calling step 2
			 ***************************************************************/
			System.out.printf("Step 2) Obtaining average for column [%d] .......... ", col);
			double average = ps.getAverage(ps.getColumnVector(X, col));
			System.out.println("[done]");
			System.out.printf("      Requested column [%d] average ................ %.3f %n", col, average  ) ;
			System.out.println();
			for ( int i = 0; i < numColumns; i++ ) {
				average = ps.getAverage(ps.getColumnVector(X, i));
				System.out.printf("      Column [%d] average .......................... %.3f %n", i, average  ) ;	
			}
			System.out.println();
			
			
			
			/***************************************************************
			 * Calling step 3
			 ***************************************************************/
			
			System.out.printf("Step 3) Obtaining output for run1(x%d) ............. ", col);
			double run1Output = ps.run1(ps.getColumnVector(X, col));
			System.out.println("[done]");
			
			System.out.printf("      Output from run1(x%d) ........................ %.3f %n", col, run1Output  ) ;
			System.out.println();
			
			
			
			
			/***************************************************************
			 * Calling step 4
			 ***************************************************************/
			System.out.printf("Step 4) Obtaining output for run2(x%d) ............. ", col);
			double run2Output = ps.run2(X);
			System.out.println("[done]");
			
			System.out.printf("      Output from run2(x%d) ........................ %.3f %n", col, run2Output  ) ;

			
			System.out.println();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	
	/***********************************************************************
	 * CODE TO IMPLEMENT:  Implement the following methods
	 ***********************************************************************/
	
	
	/*
	 * Return a matrix for the file provided as a command-line argument
	 */
public double[][] readFile(String filename) throws IOException {
		
		int rows = 0;
		int cols = 0;
		
		
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			
			BufferedReader br1 = new BufferedReader(new FileReader(filename));
			String line1;
			
			while((line = br.readLine()) != null) {
				rows++;
				cols=line.split(",").length;
				
			}
			
		   double [][] matrix = new double[rows][cols];
			
			while((line1 = br1.readLine()) != null) {
					
				for(int i = 0; i<matrix.length; i++) {
					String [] num = line1.split(",");
					line1 = br1.readLine();
					for(int j = 0; j<num.length; j++) {
						
						matrix[i][j] = Integer.parseInt(num[j]);
						
						
					}
					
				}
				
			}
			
			System.out.println(Arrays.deepToString(matrix));
		
		return matrix;
		
	}
	
	/*
	 * Return an array that represents the k-th column vector
	 */
	public double[] getColumnVector(double[][] data, int k) {
		
		final int index = k;
	    final double[] result = new double[data.length];
	    for (int i = 0; i < data.length; i++) {
	        result[i] = data[i][index];
	    }
	    return result;
		
	}
	
	/*
	 * Return the average for the vector provided
	 */
	public double getAverage(double[] x) {
		double sum = 0;
		
		for(int i = 0; i<x.length;i++) {
			sum += x[i];
		}
		return sum/x.length;
	}
	
	
	/*
	 * Return the calculation as indicated in the problem set
	 */
	public double run1(double[] x) {
		double num = 0;
		for(int i = 0; i<x.length; i++) {
				
			num += Math.pow(x[i] - getAverage(x), 2);
				
				
			}
				
		return num;
			
	}
	
	/*
	 * Return the calculation as indicated in the problem set
	 */
	
	public double run2(double[][] x) {
		double num = 0;
		for(int j = 1; j<x.length; j++) {
			num += Math.sqrt(run1(x[j]));
		}
		return num;
	}
	
	

}
