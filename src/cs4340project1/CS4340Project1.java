
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
    private boolean[] error;
    
    private int[][] trainingData;
    //private int[][] testingData;
    
    public CS4340Project1(int[][] train) { //, int[][] test) {
        this.trainingData = train;
        //this.testingData = test;
        this.w0 = 0;
        this.w1 = 0;
        this.error = new boolean[train.length];
        Arrays.fill(this.error, true);
    }
    
    public void stillErrors() {
        
    }
    public int discriminateFunction() {
        return this.w0 + this.w1 * this.x;
    }
    
    public int adjustW0() {
        return this.w0 + this.c * this.k * this.d;
    }
    
    public int adjustW1() {
        return this.w1 + this.c * this.d + this.x;
    }
    
    public void go() {
          while(true) {
              for(int i = 0; i < trainingData.length; i++) {
                  for(int j = 0; j < trainingData[i].length; j++) {
                      
                  }
              }
          } 
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] trainingData = new int[][] {{4,-1}, {-1,1}};
        CS4340Project1 pla = new CS4340Project1(trainingData);
        pla.go();
    }
    
}
