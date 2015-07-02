package es.jtorrente.exercises.simple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 02/07/2015.
 */
public class TestParentheses {

    @Test
    public void test(){
        for (int n=0; n<=6; n++) {
            HashSet<String> allParen = Parentheses.buildAllParentheses(n);
            System.out.println("*********** PRINTING ALL PARENTHESES WITH N = " + n + " ***************");
            System.out.println(" ----- METHOD 1 -----");
            Parentheses.printAllParentheses(n);
            checkAllDifferent("Method1", allParen);
            List<String> allParen2 = Parentheses.buildAllParentheses2(n);
            System.out.println(" ----- METHOD 2 -----");
            Parentheses.printAllParentheses2(n);
            checkAllDifferent("Method2", allParen2);
            assertEquals("The number of parentheses generated does not match", allParen.size(), allParen2.size());
        }
    }

    @Test
    public void testMemory(){
        for (int n=20; n>=0; n--) {
            Parentheses.printAllParentheses2(n);
        }
    }

    @Test
    public void compareSolutions2And3(){
        int totalN = 10;
        long[] timesMethod2 = new long[totalN+1];
        long[] timesMethod3 = new long[totalN+1];
        for (int n=0; n<=totalN; n++) {
            System.out.println("*********** PRINTING ALL PARENTHESES WITH N = " + n + " ***************");
            System.out.println(" ----- METHOD 2 -----");
            long initialTime =  System.nanoTime();
            List<String> allParen2 = Parentheses.buildAllParentheses2(n);
            Parentheses.printAllParentheses2(n);
            timesMethod2[n]=System.nanoTime()-initialTime;

            System.out.println(" ----- METHOD 3 -----");
            initialTime = System.nanoTime();
            List<String> allParen3 = new ArrayList<>();
            Parentheses.buildAllParentheses3(n, allParen3, "", 0, 0);
            Parentheses.printAllParentheses3(n);
            timesMethod3[n]=System.nanoTime()-initialTime;
            assertEquals("The number of parentheses generated does not match", allParen2.size(), allParen3.size());
        }

        System.out.println(" ****************** TIMES **************");
        System.out.println("N\t  METHOD 2\t  METHOD 3");
        for (int i=0; i<=totalN; i++){
            System.out.println(i+"\t"+timesMethod2[i]+"\t"+timesMethod3[i]);
        }
    }

    private void checkAllDifferent(String methodTested, Collection<String> solutions){
        HashSet<String> differentSolutions = new HashSet<>();
        for (String sol: solutions){
            differentSolutions.add(sol);
        }

        assertEquals(methodTested, solutions.size(), differentSolutions.size());
    }
}
