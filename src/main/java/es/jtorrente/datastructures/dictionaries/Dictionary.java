package es.jtorrente.datastructures.dictionaries;

/**
 * Created by jtorrente on 05/07/2015.
 */
public interface Dictionary<K extends Comparable,T> {

    class Entry<K,T>{
        K key;
        T value;
    }

    Entry<K,T> search(K key);
    void insert(Entry<K,T> entry);
    void delete(Entry<K,T> entry);
    void delete(K key);
    Entry<K,T> successor(Entry<K,T> entry);
    Entry<K,T> predecessor(Entry<K,T> entry);
    Entry<K,T> min();
    Entry<K,T> max();
}
