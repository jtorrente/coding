package es.jtorrente.exercises.laackman;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 06/09/2015.
 */
public class SubsetsTest {

    private static int[][] combinatorialNumbers;

    @BeforeClass
    public static void init(){
        combinatorialNumbers = createPascalTriangle(6);
    }

    private static int[][] createPascalTriangle(int maxN){
        int[][] triangle = new int[maxN+1][];
        for (int n=0; n<=maxN; n++) {
            triangle[n] = new int[n+1];
            for (int k=0; k<=n; k++) {
                if (k==0 || k==n) {
                  triangle[n][k] = 1;
                } else {
                    triangle[n][k] = triangle[n-1][k-1] + triangle[n-1][k];
                }
            }
        }
        return triangle;
    }

    @Test
    public void test(){
        test(0);
        test(1);
        test(2);
        test(4);
    }

    public void test(int setSize){
        System.out.println("TESTING SIZE = "+setSize);
        System.out.println("*********************");
        HashSet<Integer> set = new HashSet<>(setSize);
        for (int i=1; i<=setSize; i++) {
            set.add(i);
        }
        List<HashSet<Integer>> subsets = Subsets.calculateAll(set);
        printSubsets(subsets);
        assertEquals(1 << setSize, subsets.size());
        int[] nSubsetsBySize = new int[setSize+1];
        for (HashSet<Integer> subset: subsets){
            nSubsetsBySize[subset.size()]++;
        }
        for (int i=0; i<nSubsetsBySize.length; i++){
            int expected = combinatorialNumbers[setSize][i];
            assertEquals(expected, nSubsetsBySize[i]);
        }
    }

    private void printSubsets(List<HashSet<Integer>> subsets) {
        for (HashSet<Integer> subset: subsets){
            System.out.print("{");
            subset.forEach(element -> {
                System.out.print(element);
                System.out.print("  ");
            });
            System.out.print("} ");
            System.out.println(subset.size());
        }
    }
}
