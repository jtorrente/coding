package es.jtorrente.exercises.leetcode;

import es.jtorrente.exercises.leetcode.Calculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 16/07/2015.
 */
public class TestCalculator {
    @Test
    public void test(){
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.calculate("1 + 1"));
        assertEquals(25, calculator.calculate("10 + 15"));
        //150+ 2-3-180+1
        // 150-180 = -30
        assertEquals(150, calculator.calculate("10 * 15"));
        assertEquals(152, calculator.calculate("10 * 15+ 2"));
        assertEquals(149, calculator.calculate("10 * 15+ 2-3"));
        assertEquals(99, calculator.calculate("10 * 15+ 2-3-50"));
        assertEquals(49, calculator.calculate("10 * 15+ 2-3-50*2"));
        assertEquals(149-300, calculator.calculate("10 * 15+ 2-3-50*2*3"));
        assertEquals(149-1800, calculator.calculate("10 * 15+ 2-3-50*2*3*6"));
        assertEquals(149-180, calculator.calculate("10 * 15+ 2-3-50*2*3*6/ 10"));
        assertEquals(150-180, calculator.calculate("10 * 15+ 2-3-50*2*3*6/ 10+1"));

        //1*2+3*4-5*6+7*8-9*10
        // 2+12-30+56-90 = -16+56-90 = -50
        assertEquals(-50, calculator.calculate("1*2+3*4-5*6+7*8-9*10"));

        //282-1*2*13-30-2*2*2/2-95/5*2+55+804+3024
        // 282-26-30-4-38+55+804+3024
        assertEquals(4067, calculator.calculate("282-1*2*13-30-2*2*2/2-95/5*2+55+804+3024"));
    }
}
