package es.jtorrente.es.jtorrente.exercises.leetcode;

import es.jtorrente.exercises.leetcode.TrapRainingWater;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 06/07/2015.
 */
public class TestTrapRainingWater {

    @Test
    public void test(){
        // The same input as in the example online
        int[] input = {0,1,0,2,1,0,1,3,2,1,2,1};
        assertEquals(6, new TrapRainingWater().trap(input));
        assertEquals(6, new TrapRainingWater().trapRecursive(input));
    }
}
