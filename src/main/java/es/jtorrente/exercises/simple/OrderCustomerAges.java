package es.jtorrente.exercises.simple;

/**
 * Solution to problem on pages 49-50 of the 'Cracking the Coding Interview' book.
 *
 * Problem: Order a list of integers. Each integer is the age of a customer. There are around a million of customers.
 *
 * Created by jtorrente on 22/02/15.
 */
public class OrderCustomerAges {

    private static final int MAX_AGE = 130;

    public int[] sortArray(int[]customerAges){
        return sortArray(customerAges, MAX_AGE);
    }

    public int[] sortArray(int[] customerAges, int maxAge){
        int[] ageCount = new int [maxAge+1];

        for (int i=0; i<customerAges.length; i++){
            ageCount[customerAges[i]]++;
        }

        int[] sortedCustomerAges = new int[customerAges.length];
        int currentPos = 0;
        for (int i=0; i<ageCount.length; i++){
            for (int j=0; j<ageCount[i]; j++){
                sortedCustomerAges[j+currentPos]=i;
            }
            currentPos+=ageCount[i];
        }
        return sortedCustomerAges;
    }

}
