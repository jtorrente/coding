package es.jtorrente.exercises.leetcode;

import es.jtorrente.exercises.leetcode.HammingWeight;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 17/07/2015.
 */
public class TestHammingWeight {

    @Test
    public void test(){
        assertEquals(32, HammingWeight.hammingWeight(-1));
    }
}
