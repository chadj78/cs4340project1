
package cs4340project1;

import java.util.Arrays;


public class CS4340Project1 {

    // Decision boundry variable
    private int D;
    // Constants used in weight adjustment
    private int k;
    private int c;
    // The true class variable
    private int d;
    // Sample variables
    private int x1;
    private int x2;
    // Weight variables
    private int w0;
    private int w1;
    private int w2;
    // Counter variable to keep track of weight updates
    private int weightUpdates;
    // Counter variable to keep track of iterations
    private int counter;
    // Array of Boolean arrays to track errors
    // Starts out with all elements set to true
    // When all elements are false, breaks the while loop
    private boolean[] error;
    // Array of int arrays to hold traing data
    // The first two elements of each array are for x1, x2
    // The 3rd elements are for the d values
    private int[][] trainingData;
    // Array of int arrays to hold test data
    // The first two elements are for x1, x2
    // The 3rd elements are for the expected d values 
    private int[][] testData;
 
    
    // Constructor that takes a training and a test array
    // Also initializes weights, constants, and error array
    public CS4340Project1(int[][] train, int[][] test) {
        this.trainingData = train;
        this.testData = test;
        this.counter = 0;
        this.weightUpdates = 0;
        this.w0 = 0;
        this.w1 = 0;
        this.w2 = 0;
        this.c = 1;
        this.k = 1;
        this.error = new boolean[train.length];
        Arrays.fill(this.error, true);
    }
    
    // Method that loops through the error array
    // Returns true if an error is encountered, any one or more elements is true 
    // Returns false if no errors, all elements have to be false 
    public boolean stillErrors() {
        boolean result = false;
        for(int i = 0; i < this.error.length; i++) {
            if(error[i] == true) {
                result = true;
                break;
            }    
        }
        return result;
    }
    
    // Function to calculate D, using weights and x values
    // Returns 1 if 0 or greater, -1 if less than zero
    public int discriminateFunction() {
        if((this.w0 + (this.w1 * this.x1) + (this.w2 * this.x2)) >= 0 ) {
            return 1;
        }
        else {
            return -1;  
        }
    }
    
    // Adjusts the w0 weight variable using constants c,k
    public int adjustW0() {
        return this.w0 + (this.c * this.k * this.d);
    }
    
     // Adjusts the w1 weight variable using constant c
    public int adjustW1() {
        return this.w1 + (this.c * this.d * this.x1);
    }
    
    // Adjusts the w1 weight variable using constant c
    public int adjustW2() {
        return this.w2 + (this.c * this.d * this.x2);
    }
    
    // Rates the test point given to -1 or 1
    public int evalTestData(int x1, int x2) {
         this.x1 = x1;
         this.x2 = x2;
         return this.discriminateFunction();
    }
    
    // Method that has while control loop with for loop that goes through all training points
    // While loop terminates if no errors in error array for all training data
    // or if iterations exceed 10000
    // If D != d, weights are updated and the error array element is set to true
    // If D == d, no weight change and the error array element is set to false
    public void go() {
          while(stillErrors() || this.counter > 10000) {
              for(int i = 0; i < trainingData.length; i++) {
                  System.out.println("Iteration: " + ++this.counter);
                  this.x1 = this.trainingData[i][0];
                  System.out.println("x1: " + this.x1);
                  this.x2 = this.trainingData[i][1];
                  System.out.println("x2: " + this.x2);
                  this.d = this.trainingData[i][2];
                  System.out.println("d: " + this.d);
                  this.D = this.discriminateFunction();
                  System.out.println("D: " + this.D);
                  System.out.println("old w0: " + this.w0);
                  System.out.println("old w1: " + this.w1);
                  System.out.println("old w2: " + this.w2);
                  if(this.D != this.d) {
                      this.weightUpdates++;
                      this.error[i] = true;
                      this.w0 = this.adjustW0();
                      System.out.println("new w0: " + this.w0);
                      this.w1 = this.adjustW1();
                      System.out.println("new w1: " + this.w1);
                      this.w2 = this.adjustW2();
                      System.out.println("new w2: " + this.w2);
                  }
                  else {
                      this.error[i] = false;
                  }
              }
          }
          
          System.out.println("");
          System.out.println("");
          System.out.println("Final results of training data: ");
          
          // If training data is not linearly seperable
          if(this.counter > 10000) {
              System.out.println("Trainging points are not linear seperable, in excess of 10k iterations.");
          }
          // If training data is linearly seperatable, print line equation and then run test points against line equation
          else {
              System.out.println("Training points given are linearly seperatable, equation is: D = " + this.w0 + " + " + this.w1 + "(x1) + " + this.w2 + "(x2)");
              System.out.println("Number of iterations made on training set: " + this.counter);
              System.out.println("Number of weight updates: " + this.weightUpdates);
              System.out.println("Value of weight0: " + this.w0);
              System.out.println("Value of weight1: " + this.w1);
              System.out.println("Value of weight2: " + this.w2);
              System.out.println();
              System.out.println();
              System.out.println("Results of test data:");
              for(int i = 0; i < this.testData.length; i++) {
                  System.out.println("test point " + (i + 1) + ": (" + this.testData[i][0] + "," + this.testData[i][1] +")");
                  System.out.println("Expected classifaction value: " + this.testData[i][2]);
                  System.out.println("Actual classifaction value from decision boundry: " + this.evalTestData(this.testData[i][0], this.testData[i][1]));
                  System.out.println();
              }
          }
    }
    
    public static void main(String[] args) {
        int[][] trainingData = new int[][]{{2,10,1}, {3,8,1}, {5,2,1}, {7,3,1}, {8,8,1}, {0,4,-1}, {2,5,-1}, {-1,-1,-1}, {4,1,-1}, {-2,-2,-1}};
        int[][] testData = new int[][]{{5,9,1}, {5,1,-1}, {3,6,-1}, {1,10,-1}, {-3,7,-1}};
        CS4340Project1 pla = new CS4340Project1(trainingData, testData);
        pla.go();
    } 
}
