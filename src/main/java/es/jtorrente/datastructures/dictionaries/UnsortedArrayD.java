package es.jtorrente.datastructures.dictionaries;

/**
 * Created by jtorrente on 05/07/2015.
 */
public class UnsortedArrayD<K extends Comparable, T> extends BaseArrayD<K,T>{
    private Entry<K,T> min=null;
    private Entry<K,T> max=null;

    private void updateMinMax(Entry<K, T> entry){
        if (min==null || min.key.compareTo(entry.key)>0){
            min = entry;
        }
        if (max==null || max.key.compareTo(entry.key)<0){
            max = entry;
        }
    }

    private void updateMinMax(){
        min = max = null;
        for (int i=0; i<size; i++){
            if (min==null || min.key.compareTo(elements[i].key)>0){
                min = elements[i];
            }
            if (max==null || max.key.compareTo(elements[i].key)<0){
                max = elements[i];
            }
        }
    }

    @Override
    public Entry<K, T> search(K key) {
        int index=indexOf(key);
        if (index>=0){
            return elements[index];
        }
        return null;
    }

    @Override
    public void insert(Entry<K, T> entry) {
        resize();
        elements[size++]=entry;
        updateMinMax(entry);
    }

    @Override
    public void delete(int index) {
        if (checkRange(index)){
            Entry<K,T> elemDeleted = elements[index];
            elements[index]=elements[--size];
            if (elemDeleted == min || elemDeleted == max) {
                updateMinMax();
            }
        }
    }

    @Override
    public Entry<K, T> successor(int i) {
        if (!checkRange(i)){
            return null;
        }
        return successor(elements[i]);
    }

    @Override
    public Entry<K, T> successor(Entry<K, T> entry) {
        Entry<K,T> successor = null;
        for (int i=0; i<size; i++){
            Comparable<K> elem = (Comparable<K>) elements[i].key;
            if (elem.compareTo(entry.key)>0 && (successor == null || elem.compareTo(successor.key)<0)){
                successor = elements[i];
            }
        }
        return successor;
    }

    @Override
    public Entry<K, T> predecessor(int i) {
        if (!checkRange(i)){
            return null;
        }
        return predecessor(elements[i]);
    }

    @Override
    public Entry<K, T> predecessor(Entry<K, T> entry) {
        Entry<K,T> predecessor = null;
        for (int i=0; i<size; i++){
            Comparable<K> elem = (Comparable<K>) elements[i].key;
            if (elem.compareTo(entry.key)<0 && (predecessor == null || elem.compareTo(predecessor.key)>0)){
                predecessor = elements[i];
            }
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
