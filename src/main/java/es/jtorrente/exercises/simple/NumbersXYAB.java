package es.jtorrente.exercises.simple;

/**
 * Problem from http://www.careercup.com/question?id=5135296679116800
 *
 * You are given four integers 'a', 'b', 'y' and 'x', where 'x' can only be either zero or one. Your task is as follows:

 If 'x' is zero assign value 'a' to the variable 'y', if 'x' is one assign value 'b' to the variable 'y'.

 You are not allowed to use any conditional operator (including ternary operator).
 Follow up: Solve the problem without utilizing arithmetic operators '+ - * /'.
 *
 * Created by jtorrente on 07/07/2015.
 */
public class NumbersXYAB {

    public static int assignYArithmetic(int a, int b, int x){
        int y = (1-x)*a+x*b;
        return y;
    }

    public static int assignYBitwise(int a, int b, int x){
        /*
         * The interesting thing here is to obtain a proper
         * mask from x. Since x is a signed integer, ~x doesn't
         * work:
         * ~0 = ~000...0 = 111...1 = -1 (OK)
         * ~1 = ~000...1 = 111...0 = -2 (Not OK, should be 0)
         *
         * A way around this is to shift x 31 bits left, so
         * 000...0 becomes 000...0 but 000...1 becomes 100...0
         * (that is, Integer.MIN_VALUE). Then, shift 31 bits
         * right, which won't undo the whole thing but instead
         * copy bit at pos 31 all the way to pos 0, as >> preserves
         * the sign of the integer. After that, the binary complement
         * can be applied:
         *
         * 000...0  --(<<31)--> 000...0 --(>>31)--> 000...0 --(~)--> 111...1
         * 000...1  --(<<31)--> 100...0 --(>>31)--> 111...1 --(~)--> 000...0
         */
        int m = x<<31;
        m = m>>31;
        m = ~m;
        System.out.println("X="+x+" M="+m);
        int y = m&a | ~m&b ;
        return y;
    }

    public static int assignYArray(int a, int b, int x){
        int[] fun = {a,b};
        int y = fun[x];
        return y;
    }

}
