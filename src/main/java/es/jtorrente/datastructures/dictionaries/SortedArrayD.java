package es.jtorrente.datastructures.dictionaries;

/**
 * Created by jtorrente on 05/07/2015.
 */
public class SortedArrayD<K extends Comparable, T> extends BaseArrayD<K,T>{
    public int binarySearch(K key, int from, int to) {
        if (from>to){
            return -1;
        }

        int median = (from+to)/2;
        int comparison = key.compareTo(elements[median].key);
        if (comparison<0){
            return binarySearch(key, from, median-1);
        } else if (comparison>0){
            return binarySearch(key, median+1, to);
        }
        return median;
    }

    @Override
    public Entry<K, T> search(K key) {
        int index= binarySearch(key, 0, size-1);
        if (index>=0){
            return elements[index];
        }
        return null;
    }

    @Override
    public void insert(Entry<K, T> entry) {
        resize();
        if (size == 0){
            elements[0]=entry;
        } else {
            Entry<K, T> current = elements[0];
            int index = 0;
            while (index<size && current.key.compareTo(entry.key)<0){
                current = elements[++index];
            }
            for (int i=size-1; i>=index; i--){
                elements[i+1]=elements[i];
            }
            elements[index]=entry;
        }
        size++;
    }

    @Override
    public void delete(int index) {
        if (index>=0){
            shiftOnePositionLeft(index);
        }
    }

    private void shiftOnePositionLeft(int from){
        for (int i=from; i<size-1;i++){
            elements[i]=elements[i+1];
        }
        elements[--size] = null;
    }

    @Override
    public Entry<K, T> successor(int i) {
        if (!checkRange(i) || i==size-1){
            return null;
        }
        return elements[i+1];
    }

    @Override
    public Entry<K, T> successor(Entry<K, T> entry) {
        return successor(binarySearch(entry.key, 0, size-1));
    }

    @Override
    public Entry<K, T> predecessor(int i) {
        if (!checkRange(i) || i==0){
            return null;
        }
        return elements[i-1];
    }

    @Override
    public Entry<K, T> predecessor(Entry<K, T> entry) {
        return predecessor(binarySearch(entry.key, 0, size - 1));
    }

    @Override
    public Entry<K, T> min() {
        return size==0?null:elements[0];
    }

    @Override
    public Entry<K, T> max() {
        return size==0?null:elements[size-1];
    }
}
