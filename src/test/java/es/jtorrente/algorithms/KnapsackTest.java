package es.jtorrente.algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 12/09/2015.
 */
public class KnapsackTest {
    @Test
    public void testBounded(){
        assertEquals(10, Knapsack.knapsackBounded(new int[]{2, 3, 4, 5}, new int[]{3, 7, 2, 9}, 5));
        assertEquals(11, Knapsack.knapsackBounded(new int[]{2, 3, 4, 5}, new int[]{3, 7, 2, 11}, 5));
    }

    @Test
    public void testUnbounded(){
        assertEquals(10.6, Knapsack.knapsackUnbounded(new int[]{2, 3, 4, 5}, new int[]{3, 7, 2, 9}, 5), 0.001F);
    }
}
