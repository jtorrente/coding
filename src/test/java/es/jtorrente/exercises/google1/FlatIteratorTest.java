package es.jtorrente.exercises.google1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class FlatIteratorTest {
    private String getAllElements(FlatIterator<Integer> flatIterator) {
        StringBuffer stringBuffer = new StringBuffer();
        while (flatIterator.hasNext()) {
            stringBuffer.append(flatIterator.next());
        }
        return stringBuffer.toString();
    }

    @Test
    public void testGeneralCase() {
        BigIterator bigIterator = new BigIterator(3);
        FlatIterator<Integer> flatIterator = new FlatIterator<>(bigIterator);
        assertEquals(bigIterator.expected, getAllElements(flatIterator));
    }

    @Test
    public void testZeroIterators() {
        BigIterator bigIterator = new BigIterator(0);
        FlatIterator<Integer> flatIterator = new FlatIterator<>(bigIterator);
        assertEquals("", getAllElements(flatIterator));
    }

    @Test
    public void testBadCalls() {
        BigIterator bigIterator = new BigIterator(3, 0);
        FlatIterator<Integer> flatIterator = new FlatIterator<>(bigIterator);
        // Test an exception is thrown if I call next()
        try {
            assertFalse(flatIterator.hasNext());
            flatIterator.next();
            fail();
        } catch (NoSuchElementException e) {
            // Good!
        }
    }

    @Test
    public void testUnevenSizes() {
        BigIterator bigIterator = new BigIterator(BigIterator.buildIterator(0, 2), BigIterator.buildIterator(10, 0),
                BigIterator.buildIterator(20,1), BigIterator.buildIterator(30, 3));
        String expected = "0203013132";
        assertEquals(expected, getAllElements(new FlatIterator<>(bigIterator)));
    }

    /*
     * Internal class that implements Iterator<Iterator<T>>
     */
    private static class BigIterator implements Iterator<Iterator<Integer>> {
        private String expected;

        private List<Iterator<Integer>> list = new ArrayList<>();
        int current = 0;

        public BigIterator(Iterator<Integer>... iterators) {
            for (Iterator<Integer> it: iterators) {
                list.add(it);
            }
        }

        public BigIterator(int nIterators) {
            this(nIterators, 3);
        }

        public BigIterator(int nIterators, int elementsPerIterator) {
            StringBuffer expectedBuffer = new StringBuffer();
            for (int i = 0; i < nIterators; i++) {
                list.add(buildIterator(10 * i, elementsPerIterator));
            }
            for (int i = 0; i < elementsPerIterator; i++) {
                for (int j = 0; j < nIterators; j++) {
                    expectedBuffer.append(Integer.toString(j * 10 + i));
                }
            }
            expected = expectedBuffer.toString();
        }

        public static Iterator<Integer> buildIterator(int start, int nElements) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < nElements; i++) {
                list.add(start + i);
            }
            return list.iterator();
        }

        @Override
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Iterator<Integer> next() throws NoSuchElementException{
            if (!hasNext()) {
                throw new NoSuchElementException("Fail");
            }
            Iterator<Integer> toReturn = list.get(current);
            current++;
            return toReturn;
        }
    }
}

