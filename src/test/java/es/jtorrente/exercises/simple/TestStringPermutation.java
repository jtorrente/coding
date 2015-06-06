package es.jtorrente.exercises.simple;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by jtorrente on 06/06/2015.
 */
public class TestStringPermutation {

    @Test
    public void test(){
        assertFalse(StringPermutation.isPermutation("a", null));
        assertFalse(StringPermutation.isPermutation(null, "a"));
        assertTrue(StringPermutation.isPermutation(null, null));
        assertTrue(StringPermutation.isPermutation("abcd", "dbca"));
        assertTrue(StringPermutation.isPermutation("dddAA123", "Ad1d2d3A"));
        assertFalse(StringPermutation.isPermutation("dddAA123", "ad1d2d3A"));
        assertFalse(StringPermutation.isPermutation("dddAA123", "Ad1d2d3"));
        try {
            StringPermutation.isPermutation("ñ","n");
            fail("String Permutation should have thrown an exception as strings had invalid characters");
        } catch (Exception e){

        }
    }

}
