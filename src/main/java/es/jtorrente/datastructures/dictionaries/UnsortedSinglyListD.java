package es.jtorrente.datastructures.dictionaries;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by jtorrente on 05/07/2015.
 */
public class UnsortedSinglyListD<K extends Comparable, T> implements Dictionary<K,T>{

    protected EntryNode<K,T> first = null;
    protected EntryNode<K,T> min = null;
    protected EntryNode<K,T> max = null;

    protected void updateMinMax(EntryNode<K,T> entry){
        if (min == null|| min.key.compareTo(entry.key)>0){
            min = entry;
        }
        if (max == null|| max.key.compareTo(entry.key)<0){
            max = entry;
        }
    }

    protected void updateMinMax(){
        min = max = null;
        EntryNode<K,T> current = first;
        while(current!=null){
            if (min==null || min.key.compareTo(current.key)>0){
                min = current;
            }
            if (max==null || max.key.compareTo(current.key)<0){
                max = current;
            }
            current = current.next;
        }
    }

    public static class EntryNode<K,T> extends Entry<K,T>{
        EntryNode<K,T> next;
    }

    @Override
    public Entry<K, T> search(K key) {
        EntryNode<K,T> current = first;
        while (current!=null){
            if (current.key.compareTo(key)==0){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public void insert(Entry<K, T> entry) {
        EntryNode<K,T> newNode;
        if (!(entry instanceof EntryNode)){
            newNode = new EntryNode<>();
            newNode.key = entry.key;
            newNode.value = entry.value;
        } else {
            newNode = (EntryNode<K,T>)entry;
        }
        newNode.next = first;
        first = newNode;
        updateMinMax(newNode);
    }

    @Override
    public void delete(Entry<K, T> entry) {
        EntryNode<K,T> current = first;
        EntryNode<K,T> parent = null;
        while(current!=null){
            if (current.next.key.compareTo(entry.key)==0){
                parent = current;
                break;
            }
            current = current.next;
        }
        if (parent!=null) {
            parent.next = ((EntryNode<K,T>)entry).next;
            updateMinMax();
        }
    }

    @Override
    public void delete(K key) {
        throw new NotImplementedException();
    }

    @Override
    public Entry<K, T> successor(Entry<K, T> entry) {
        EntryNode<K,T> current = first;
        EntryNode<K,T> successor = null;
        while(current!=null){
            if (current.key.compareTo(entry.key)>0 && (successor==null || current.key.compareTo(successor.key)<0)){
                successor = current;
            }
            current = current.next;
        }
        return successor;
    }

    @Override
    public Entry<K, T> predecessor(Entry<K, T> entry) {
        EntryNode<K,T> current = first;
        EntryNode<K,T> predecessor = null;
        while(current!=null){
            if (current.key.compareTo(entry.key)<0 && (predecessor==null || current.key.compareTo(predecessor.key)>0)){
                predecessor = current;
            }
            current = current.next;
        }
        return predecessor;
    }

    @Override
    public Entry<K, T> min() {
        return min;
    }

    @Override
    public Entry<K, T> max() {
        return max;
    }
}
