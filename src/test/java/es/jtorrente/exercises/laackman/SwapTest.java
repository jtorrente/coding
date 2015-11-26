package es.jtorrente.exercises.laackman;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 06/09/2015.
 */
public class SwapTest {

    @Test
    public void test(){
        int x = 1;
        int y = 2;
        int[] numbers = {x,y};
        Swap.swapTwoNumbers(numbers);
        assertEquals(y, numbers[0]);
        assertEquals(x, numbers[1]);
    }
}
