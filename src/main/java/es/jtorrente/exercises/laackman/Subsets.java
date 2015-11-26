package es.jtorrente.exercises.laackman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Problem: generate all possible subsets from a set
 *
 * Created by jtorrente on 06/09/2015.
 */
public class Subsets {

    private static final List<HashSet<Integer>> EMPTY_RESULTS = new ArrayList<>();
    private static final HashSet<Integer> EMPTY_SET = new HashSet<>(0);

    /**
     * Cost: O(2^n) space and time
     */
    public static List<HashSet<Integer>> calculateAll (HashSet<Integer> set) {
        if (set==null) {
            return EMPTY_RESULTS;
        }

        int n = set.size();
        int nSubsets = 1<<n;
        List<HashSet<Integer>> results = new ArrayList<>(nSubsets);
        Integer[] arraySet = set.toArray(new Integer[0]);

        // Always add empty set to results
        results.add(EMPTY_SET);

        // Add subsets of cardinal 1..n
        for (int subsetSize=1; subsetSize<=n; subsetSize++) {
            int loopNumber = 0;
            int[] loopPointers = new int[subsetSize]; // Determines the number of inner loops
            while (loopNumber>=0) { // While exits when the upper-most loop is done
                if (loopPointers[loopNumber]<n) { // Check if current loop is within limits
                    if (loopNumber == subsetSize-1) { // If it is the inner-most loop, add result to the list
                        addResult(results, arraySet, loopPointers);
                        loopPointers[loopNumber]++; // Increment pointer to generate next iteration
                    } else { // Not the inner-most loop: initialize next level loop
                        loopNumber++;
                        loopPointers[loopNumber] = loopPointers[loopNumber-1]+1;
                    }
                } else { // Loop off limits: terminate current loop and go back to the parent loop
                    loopNumber--;
                    if (loopNumber>=0) {
                        loopPointers[loopNumber]++; // Increment parent's pointer
                    }
                }
            }
        }

        return results;
    }

    private static void addResult(List<HashSet<Integer>> results, Integer[] arraySet, int[] loopPointers) {
        HashSet<Integer> newResult = new HashSet<>(loopPointers.length);
        results.add(newResult);
        for (int i=0; i<loopPointers.length; i++) {
            newResult.add(arraySet[loopPointers[i]]);
        }
    }
}
