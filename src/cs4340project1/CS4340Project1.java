
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
    
    private double dB;
    // Counter variable to keep track of weight updates
    private int weightUpdates;
    // Counter variable to keep track of iterations
    private int counter;
    // Boolean array to track errors
    private boolean[] error;
    // Array to hold traing data
    private int[][] trainingData;
 
    // Constructor that takes a training and a test array
    // Also initializes weights, constants, and error array
    public CS4340Project1(int[][] train) {
        this.trainingData = train;
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
    // Returns true if an error is encountered
    // Returns false if no error is encountered
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
    
    public int discriminateFunction() {
        if((this.w0 + (this.w1 * this.x1) + (this.w2 * this.x2)) >= 0 ) {
            return 1;
        }
        else {
            return -1;  
        }
    }
    
    public int adjustW0() {
        return this.w0 + (this.c * this.k * this.d);
    }
    
    public int adjustW1() {
        return this.w1 + (this.c * this.d * this.x1);
    }
    
    public int adjustW2() {
        return this.w2 + (this.c * this.d * this.x2);
    }
    
    public int evalTestData(int x1, int x2) {
         this.x1 = x1;
         this.x2 = x2;
         return this.discriminateFunction();
    }
    
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
          System.out.println("Final results: ");
          System.out.println("Number of iterations made on training set: " + this.counter);
          System.out.println("Number of weight updates: " + this.weightUpdates);
          System.out.println("Value of weight0: " + this.w0);
          System.out.println("Value of weight1: " + this.w1);
          System.out.println("Value of weight2: " + this.w2);
          //this.dB = (double) -this.w0 / (double) this.w1;
          //System.out.println("Line is x = " + this.dB);
    }
    
    public static void main(String[] args) {
        int[][] trainingData = new int[][]{{2,10,1}, {3,8,1}, {5,2,1}, {7,3,1}, {8,8,1}, {0,4,-1}, {2,5,-1}, {-1,-1,-1}, {4,1,-1}, {-2,-2,-1}};
        //int[][] trainingData = new int[][]{{2,10,1}, {3,8,1}, {5,2,1}, {7,3,1}, {8,8,1}, {50,25,-1}, {65,30,-1}, {35,40,-1}, {24,13,-1}, {49,17,-1}};
        int[][] testData = new int[][]{};
        CS4340Project1 pla = new CS4340Project1(trainingData);
        pla.go();
        System.out.println(pla.evalTestData(5, 9));
    }
    
}
