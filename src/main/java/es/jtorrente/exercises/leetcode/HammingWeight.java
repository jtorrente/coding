package es.jtorrente.exercises.leetcode;

/**
 * Created by jtorrente on 17/07/2015.
 */
public class HammingWeight {

    public static int hammingWeight(int n) {
        int ones = 0;
        while (n!=0) {
            if (n%2==1 || n%2==-1) {
                ones++;
            }
            n >>>= 1;
        }
        return ones;
    }
}
