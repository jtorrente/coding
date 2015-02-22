package es.jtorrente.exercises.simple;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by jtorrente on 22/02/15.
 */
public class TestOrderCustomerAges {

    private OrderCustomerAges testObject;

    private static int MAX_AGES=10;

    @Before
    public void setUp(){
        testObject = new OrderCustomerAges();
    }

    private void testImpl(int[] unsorted, int[]sorted){
        int[]result = testObject.sortArray(unsorted, MAX_AGES);
        assertArrayEquals(sorted,result);
    }

    @Test
    public void testGeneralCase(){
        testImpl(new int[]{5,3,8,3,1,9,3}, new int[]{1,3,3,3,5,8,9});
    }
}
