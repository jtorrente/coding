package es.jtorrente.datastructures.dictionaries;

/**
 * Created by jtorrente on 05/07/2015.
 */
public class SortedDoubleListD<K extends Comparable, T> extends BaseSortedListD<K,T> {
    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    public void delete(Entry<K, T> entry) {
        EntryDNode prev = ((EntryDNode)entry).previous;
        EntryDNode nxt = (EntryDNode)(((EntryDNode)entry).next);
        if (prev!=null){
            prev.next = nxt;
        }
        if (entry==first){
            first = ((EntryDNode) entry).next;
        }
        if (nxt!=null){
            nxt.previous = prev;
        }
    }

    @Override
    public Entry<K, T> predecessor(Entry<K, T> entry) {
         if (entry==first){
             return null;
         }

        EntryDNode node = ((EntryDNode)entry);
        return node.previous;
    }

    @Override
    public Entry<K, T> max() {
        if (first == null){
            return null;
        }
        return ((EntryDNode)first).previous;
    }
}
