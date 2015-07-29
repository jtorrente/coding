package es.jtorrente.exercises.google1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by jtorrente on 29/07/2015.
 */
public class FlatIterator<T> implements Iterator<T> {

    private int current;
    private List<Iterator<T>> iteratorList;

    public FlatIterator(Iterator<Iterator<T>> iterators) {
        iteratorList = new ArrayList<>();
        current = 0;
        while (iterators.hasNext()) {
            iteratorList.add(iterators.next());
        }
    }

    @Override
    public boolean hasNext() {
        return updateCurrent();
    }

    @Override
    public T next() throws NoSuchElementException {
        boolean hasNext = updateCurrent();
        if (!hasNext) {
            throw new NoSuchElementException("There are no more elements");
        }

        // At this point, current is pointing to the first iterator that has something left
        Iterator<T> currentIt = iteratorList.get(current);
        current++;
        return currentIt.next();
    }

    /**
     * Finds the first iterator that has still elements to return and updates the variable current to point to that iterator. If no more iterators have elements left, returns false, otherwise returns true.
     */
    private boolean updateCurrent() {
        int n = iteratorList.size();
        for (int i=0; i<n; i++) {
            if (iteratorList.get((current+i)%n).hasNext()) {
                current = (current+i)%n;
                return true;
            }
        }
        return false;
    }
}

