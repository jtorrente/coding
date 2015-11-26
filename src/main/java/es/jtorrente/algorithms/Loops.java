package es.jtorrente.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jtorrente on 05/09/2015.
 */
public class Loops {

    public static List<Integer> iterate(int levels, int[]values, boolean initAtZero) {
        List<Integer> results = new ArrayList<>();

        int i = 0;
        int[] pointers = new int[levels];
        pointers[0] = 0;

        while (i>=0) {
            if (pointers[i]<values.length) {
                if (i==pointers.length-1){
                    // Execute code
                    addResult(pointers, values, results);
                    pointers[i]++;
                } else {
                    // Initialize next level loop
                    i++;
                    pointers[i] = initAtZero?0:pointers[i-1]+1;
                }
            } else {
                i--;
                if (i>=0) {
                    pointers[i]++;
                }
            }
        }

        return results;
    }

    private static void addResult(int[] pointers, int[] values, List<Integer> results) {
        int count = 0;
        for (int i=0; i<pointers.length; i++) {
            System.out.print(values[pointers[i]]);
            System.out.print(" ");
            count += values[pointers[i]];
        }
        System.out.println();
        results.add(count);
    }
}
