
package cs4340project1;

import java.util.Arrays;

/**
 *
 * @author caj333
 */
public class CS4340Project1 {

    private int D;
    private int k;
    private int c;
    private int d;
    private int x;
    private int w0;
    private int w1;
    private int counter;
    private boolean[] error;
    
    private int[][] trainingData;
    //private int[][] testingData;
    
    public CS4340Project1(int[][] train) { //, int[][] test) {
        this.trainingData = train;
        //this.testingData = test;
        this.counter = 1;
        this.w0 = 0;
        this.w1 = 0;
        this.c = 1;
        this.k = 1;
        this.error = new boolean[train.length];
        Arrays.fill(this.error, true);
    }
    
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
        if((this.w0 + this.w1 * this.x) >= 0 ) {
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
        return this.w1 + (this.c * this.d * this.x);
    }
    
    public void go() {
          while(stillErrors()) {
              for(int i = 0; i < trainingData.length; i++) {
                  System.out.println("Iteration: " + this.counter++);
                      this.x = this.trainingData[i][0];
                      System.out.println("x: " + this.x);
                      this.d = this.trainingData[i][1];
                       System.out.println("d: " + this.d);
                      this.D = this.discriminateFunction();
                       System.out.println("D: " + this.D);
                        System.out.println("old w0: " + this.w0);
                         System.out.println("old w1: " + this.w1);
                      if(this.D != this.d) {
                          this.error[i] = true;
                          this.w0 = this.adjustW0();
                           System.out.println("new w0: " + this.w0);
                          this.w1 = this.adjustW1();
                           System.out.println("new w1: " + this.w1);
                      }
                      else {
                          this.error[i] = false;
                      }
              }
          } 
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] trainingData = new int[][] {{-4,-1}, {-1,1}};
        CS4340Project1 pla = new CS4340Project1(trainingData);
        pla.go();
    }
    
}
