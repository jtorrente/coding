package es.jtorrente.datastructures.dictionaries;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by jtorrente on 05/07/2015.
 */
public abstract class BaseListD<K extends Comparable, T> implements Dictionary<K,T> {

    protected EntryNode<K,T> first = null;

    public static class EntryNode<K,T> extends Entry<K,T>{
        EntryNode<K,T> next;
    }

    public static class EntryDNode<K,T> extends EntryNode<K,T>{
        EntryDNode<K,T> previous;
    }

    @Override
    public Entry<K, T> search(K key) {
        if (first==null){
            return null;
        }

        EntryNode<K,T> current = first;
        if (current.key.compareTo(key)==0){
            return current;
        }
        current = current.next;
        while (current!=null && current!=first){
            if (current.key.compareTo(key)==0){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public void delete(K key) {
        throw new NotImplementedException();
    }

    public abstract boolean isDouble();

}
