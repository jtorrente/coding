package es.jtorrente.exercises.simple;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 08/06/2015.
 */
public class TestCountSteps {

    public static final int LARGE_N=35;

    @Test
    public void test(){
        // Test function is correct
        assertEquals(7, CountSteps.countStepsNoCache(4));
        assertEquals(7, CountSteps.countSteps(4));
        // Compare running time
        long time = System.currentTimeMillis();
        int cacheResult = CountSteps.countSteps(LARGE_N);
        System.out.println("[TIME WITH CACHE] " + (System.currentTimeMillis() - time) + "ms");

        time = System.currentTimeMillis();
        int noCacheResult = CountSteps.countStepsNoCache(LARGE_N);
        System.out.println("[TIME WITH NO CACHE] "+(System.currentTimeMillis()-time)+"ms");

        assertEquals(cacheResult, noCacheResult);
    }
}
