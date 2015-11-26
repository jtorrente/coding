package es.jtorrente.exercises.laackman;

/**
 * Created by jtorrente on 06/09/2015.
 */
public class Swap {

    public static void swapTwoNumbers(int[] numbers) {
        if (numbers == null || numbers.length !=2) {
            throw new IllegalArgumentException("Array must be not null and of length 2");
        }

        numbers[0] += numbers[1]; // x=x+y
        numbers[1] = numbers[0] - numbers[1]; // y=(x+y)-y=x
        numbers[0] -= numbers[1]; // x=x+y-x = y
    }
}
