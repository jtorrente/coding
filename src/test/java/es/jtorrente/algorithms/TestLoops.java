package es.jtorrente.algorithms;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 05/09/2015.
 */
public class TestLoops {

    @Test
    public void simpleStartAtZero(){
        int[] values = {100,2000,30000};
        List<Integer> results = Loops.iterate(3, values, true);
        assertEquals(27, results.size());

        System.out.println();
        results = Loops.iterate(3, new int[]{1,2,3,4,5,6}, false);
        assertEquals(20, results.size());
    }
}
