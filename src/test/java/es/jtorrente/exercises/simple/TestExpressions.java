package es.jtorrente.exercises.simple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 29/06/2015.
 */
public class TestExpressions {

    @Test
    public void test(){
        List<Class<?extends Expressions.Calculator>> implementations = new ArrayList<>();
        implementations.add(Expressions.CalculatorTwoStacks.class);
        implementations.add(Expressions.CalculatorOneStack.class);
        implementations.add(Expressions.CalculatorTree.class);

        for (Class<?extends Expressions.Calculator> clazz:implementations){
            try {
                System.out.println("***** TESTING = " + clazz.getName() + " ******");
                Expressions.Calculator calculator = clazz.newInstance();
                assertEquals(190, calculator.calculate("3*2*3 + 2*4*3 + 2*2*4 + 5*3*2*5 +2+3+1-5-6-7-8+2"));
                assertEquals(61, calculator.calculate("3 + 4*3*5 - 2"));
                assertEquals(5, calculator.calculate("2+3"));
                assertEquals(9, calculator.calculate("2+3+5-1"));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
