package es.jtorrente.exercises.simple;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 07/07/2015.
 */
public class NumbersXYABTest {

    @Test
    public void testAssignYArithmetic(){
        assertEquals(3, NumbersXYAB.assignYArithmetic(3,4,0));
        assertEquals(4, NumbersXYAB.assignYArithmetic(3,4,1));
    }

    @Test
    public void testAssignYBitwise(){
        assertEquals(3, NumbersXYAB.assignYBitwise(3, 4, 0));
        assertEquals(4, NumbersXYAB.assignYBitwise(3, 4, 1));
    }

    @Test
    public void testAssignYArray(){
        assertEquals(3, NumbersXYAB.assignYArray(3, 4, 0));
        assertEquals(4, NumbersXYAB.assignYArray(3, 4, 1));
    }
}
