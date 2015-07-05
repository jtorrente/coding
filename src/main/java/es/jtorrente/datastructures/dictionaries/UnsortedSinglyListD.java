package es.jtorrente.datastructures.dictionaries;

/**
 * Created by jtorrente on 05/07/2015.
 */
public class UnsortedSinglyListD<K extends Comparable, T> extends BaseUnsortedListD<K,T>{

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
    public boolean isDouble() {
        return false;
    }
}
