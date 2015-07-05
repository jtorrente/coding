package es.jtorrente.datastructures.dictionaries;

import java.util.Arrays;

/**
 * Created by jtorrente on 05/07/2015.
 */
public abstract class BaseArrayD<K extends Comparable,T> implements Dictionary<K,T> {

    protected static int DEFAULT_CAPACITY=20;

    protected Entry[] elements = new Entry[DEFAULT_CAPACITY];
    protected int size = 0;

    protected void resize(){
        if (size==elements.length){
            elements = Arrays.copyOf(elements, size*2);
        }
    }

    protected boolean checkRange(int i){
        return i>=0 && i<size;
    }

    public int indexOf(Entry<K, T> entry){
        for (int i=0; i<size;i++){
            if (elements[i].key.equals(entry.key)){
                return i;
            }
        }
        return -1;
    }

    public int indexOf(K key){
        for (int i=0; i<size;i++){
            if (elements[i].key.equals(key)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void delete(Entry<K, T> entry) {
        int index=indexOf(entry);
        delete(index);
    }

    @Override
    public void delete(K key) {
        int index=indexOf(key);
        delete(index);
    }

    public abstract Entry<K, T> successor(int i);
    public abstract Entry<K, T> predecessor(int i);

    public abstract void delete(int index);
}
