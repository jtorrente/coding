package es.jtorrente.exercises.simple;

/**
 * Problem from Gay Laakmann's book, page 316.
 *
 * Problem: "A child is running up a staircase with n steps,
 * and can hop either 1 step, 2 steps or 3 steps at a time.
 *
 * Implement a method to count how many possible ways the
 * child can run up the stairs."
 *
 * Solution: A recursive algorithm involving 3 recursive calls
 * for (n-1), (n-2) and (n-3) is obvious, but extremely
 * inefficient (O(3^n)). The algorithm can easily be optimized
 * by using a cache, turning into O(n).
 *
 * Created by jtorrente on 08/06/2015.
 */
public class CountSteps {

    public static int countStepsNoCache(int n){
        if (n==0){
            return 1;
        }
        int s1=countStepsNoCache(n-1);
        int s2=n>=2?countStepsNoCache(n-2):0;
        int s3=n>=3?countStepsNoCache(n-3):0;
        return s1+s2+s3;
    }

    public static int countSteps(int n){
        int[] prevValues = new int[n];
        for (int i=0; i<n; i++){
            prevValues[i]=-1;
        }
        return countSteps(n, prevValues);
    }

    public static int countSteps(int n, int[] prevValues){
        if (n==0){
            return 1;
        }
        if (prevValues[n-1]!=-1){
            return prevValues[n-1];
        }
        int s1=countSteps(n-1, prevValues);
        int s2=n>=2?countSteps(n-2, prevValues):0;
        int s3=n>=3?countSteps(n-3, prevValues):0;
        prevValues[n-1] = s1+s2+s3;
        return prevValues[n-1];
    }
}
