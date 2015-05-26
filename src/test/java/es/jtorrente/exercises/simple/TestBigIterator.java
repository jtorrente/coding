package es.jtorrente.exercises.simple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 26/05/2015.
 */
public class TestBigIterator {

    @Test
    public void test(){
        ArrayList<Iterator<Integer>> arrayList = new ArrayList<Iterator<Integer>>();
        addIteratorToArray(arrayList, 0,1);
        addIteratorToArray(arrayList);
        addIteratorToArray(arrayList, 2);

        BigIterator<Integer> bigIterator = new BigIterator<Integer>(arrayList.iterator());

        String sequence = "";
        while(bigIterator.hasNext()){
            sequence+=bigIterator.next();
        }
        assertEquals("012", sequence);
    }

    private void addIteratorToArray(ArrayList<Iterator<Integer>> arrayList, Integer... numbers){
        ArrayList<Integer> a1 = new ArrayList<Integer>();
        for (Integer number:numbers){
            a1.add(number);
        }
        arrayList.add(a1.iterator());
    }
}
