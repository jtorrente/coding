package es.jtorrente.exercises.simple;

/**
 * From Gayle Laakmann's book, page 73:
 * Given an image represented by an NxN matrix, write a method that
 * rotates the image by 90 degrees
 * Created by jtorrente on 06/06/2015.
 */
public class ImageRotation{
    // Assumption: pixels are given as integers (32bits)
    public static void rotateImg(int[][] img, int i0, int n){
        // Base case: submatrix of size 0 or 1. Output = Input
        if (n-2*i0<2){
            return;
        }

		/* Rotate the same point 4 times, until it becomes the original.
		    Then, move forward (repeat this along the length of the
            current submatrix-1*/
        for (int k=0; k<n-2*i0-1;k++){
            int i=i0+k;
            int j=i0;
            int previousValue = img[i][j];
            int startI = i, startJ=j;
            do{
                int newI = j;
                int newJ = n-i-1;
                int tmp = img[newI][newJ];
                img[newI][newJ] = previousValue;
                previousValue = tmp;
                i=newI;j=newJ;
            } while (i!=startI || j!=startJ);
        }

        // Recursive call
        rotateImg(img,i0+1,n);
    }
}
