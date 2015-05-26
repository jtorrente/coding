package es.jtorrente.exercises.simple;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Question: Implement an iterator of iterators
 * @param <T>
 */
public class BigIterator<T> implements Iterator<T>{

    // Basic data structure
    private Iterator<Iterator<T>> iterators;

    private Iterator<T> currentIterator;

    public BigIterator (Iterator<Iterator<T>> iterators){
        this.iterators = iterators;
        this.currentIterator = iterators.hasNext()?iterators.next():null;
    }

    @Override
    public boolean hasNext(){
        // Update the pointer to the current iterator in hasNext(), as this method is expected to be called before next()
        while (currentIterator!=null && !currentIterator.hasNext()){
            if (iterators.hasNext()){
                currentIterator = iterators.next();
            } else {
                currentIterator = null;
            }
        }
        return currentIterator!=null;
    }

    @Override
    public T next(){
        if (currentIterator!=null){
            return currentIterator.next();
        }
        throw new NoSuchElementException("There are no more elements to iterate through");
    }

}
