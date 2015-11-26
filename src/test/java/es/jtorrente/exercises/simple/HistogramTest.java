package es.jtorrente.exercises.simple;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 07/09/2015.
 */
public class HistogramTest {

    @Test
    public void test(){
        assertEquals(10, Histogram.maxRectangle(new int[]{2,1,5,6,2,3}));
        assertEquals(10, Histogram.maxRectangleStack(new int[]{2,1,5,6,2,3}));
    }
}
