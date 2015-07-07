package es.jtorrente.other;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Solving out a few doubts about Java operators
 *
 * Created by jtorrente on 31/05/2015.
 */
public class TestOperators {

    /**
     * ++ and -- operators in Java.
     * i++: returns current value of i, then increments 1
     * --i: increments current value of i, then returns i (updated).
     *
     * Makes sense :)
     */
    @Test
    public void testIncrementDecrementOperators(){
        int i =0;
        assertEquals(0, i++);
        assertEquals(2, ++i);
        assertEquals(1, --i);
        assertEquals(1, i--);
    }

    @Test
    public void testBitwiseOperators(){
        int a = 0b111000;
        int b = 0b000111;
        int c = 0b110011;
        System.out.println(Integer.MIN_VALUE>>31);
        System.out.println(~0);
        System.out.println(~1);
        assertEquals(56, a);
        assertEquals(7, b);
        // Bitwise or operation
        assertEquals(0b111111, a|b);
        assertEquals(63, a|b);
        // Bitwise and operation
        assertEquals(0, a&b);
        // Bitwise xor operation
        assertEquals(0b001011, a^c);
        assertEquals(11, a^c);
        assertEquals(0b110100, b^c);
        assertEquals(52, b^c);
        // Not operation
        assertEquals(0, 0x00000000);
        assertEquals(Integer.MAX_VALUE, 0x7fffffff);
        assertEquals(Integer.MIN_VALUE, 0x80000000);
        assertEquals(Integer.MIN_VALUE+1, 0x80000001);
        assertEquals(-1, 0xffffffff);
        assertEquals(~a^0b11111111111111111111111111000000, b);
        // Binary left shift, right shift operators
        assertEquals(0b1110000, a<<1);
        assertEquals(0b11100000, a<<2);
        assertEquals(a*4, a<<2);
        assertEquals(a/4, a>>2);
    }
}
