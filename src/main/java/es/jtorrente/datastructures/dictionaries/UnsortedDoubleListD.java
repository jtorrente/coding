package es.jtorrente.datastructures.dictionaries;

/**
 * Created by jtorrente on 05/07/2015.
 */
public class UnsortedDoubleListD<K extends Comparable, T> extends BaseUnsortedListD<K,T> {
    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    public void delete(Entry<K, T> entry) {
        EntryDNode<K,T> entryDNode = (EntryDNode<K,T>)entry;
        if (entryDNode.previous!=null) {
            entryDNode.previous.next = entryDNode.next;
        } else {
            first = entryDNode.next;
        }
        if (entryDNode.next!=null) {
            ((EntryDNode) (entryDNode.next)).previous = entryDNode.previous;
        }
        updateMinMax();
    }
}
