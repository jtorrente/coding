package es.jtorrente.exercises.simple;

/**
 * Created by jtorrente on 08/06/2015.
 */
public class BitManipulation {

    public static void main(String[]args){
        int anInteger = 32;
        int anotherInteger = ~anInteger+1;
        System.out.println(anInteger);
        System.out.println(anotherInteger);
    }
}
