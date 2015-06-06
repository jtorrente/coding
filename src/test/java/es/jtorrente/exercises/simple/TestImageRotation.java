package es.jtorrente.exercises.simple;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 06/06/2015.
 */
public class TestImageRotation {

    @Test
    public void test(){
        for (int n=0; n<4; n++){
            testMatrixNxN(n);
        }
    }

    public void testMatrixNxN(int n){
        int[][] inputImg =new int[n][n];
        int[][] outputImg =new int[n][n];

        for (int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                inputImg[i]=new int[n];
                outputImg[j]=new int[n];
            }
        }

        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                inputImg[i][j]=j+n*i;
                outputImg[j][n-i-1]=inputImg[i][j];
            }
        }

        ImageRotation.rotateImg(inputImg, 0, n);

        for (int i=0; i<n; i++){
            for (int j=0; j<n;j++) {
                assertEquals("N="+n+" i="+i+" j="+j, outputImg[i][j], inputImg[i][j]);
            }
        }
    }
}
