package es.jtorrente.datastructures.dictionaries;

/**
 * Created by jtorrente on 05/07/2015.
 */
public class SortedSinglyListD<K extends Comparable, T> extends BaseSortedListD<K,T> {
    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public void delete(Entry<K, T> entry) {
        if (entry == first){
            first = first.next;
            return;
        }

        EntryNode<K,T> current = first;
        while (current!=null){
            if (current.next == entry){
                current.next = ((EntryNode)entry).next;
                break;
            }
            current = current.next;
        }
    }

    @Override
    public Entry<K, T> predecessor(Entry<K, T> entry) {
        if (entry == first){
            return null;
        }

        EntryNode<K,T> current = first;
        while (current!=null){
            if (current.next == entry){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public Entry<K, T> max() {
        EntryNode<K,T> current = first;
        EntryNode<K,T> max = null;
        while (current!=null){
            if (max==null || max.key.compareTo(current.key)<0){
                max = current;
            }
            current = current.next;
        }
        return max;
    }
}
