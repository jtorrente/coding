package es.jtorrente.datastructures.dictionaries;

/**
 * Created by jtorrente on 05/07/2015.
 */
public abstract class BaseSortedListD<K extends Comparable, T> extends BaseListD<K,T> {
    @Override
    public void insert(Entry<K, T> entry) {
        if (first==null){
            first=(EntryNode)entry;
            return;
        }

        EntryNode<K,T> current = first;
        EntryNode<K,T> parent = isDouble()?((EntryDNode)first).previous:null;
        while (current!=null){
            if (current.key.compareTo(entry.key)>=0){
                if (parent!=null) {
                    link(parent, entry);
                }
                link (entry, current);
                if (current==first){
                    first = (EntryNode<K, T>) entry;
                }
                return;
            }
            parent = current;
            current = current.next;
        }

        link (parent, entry);
        // If it is double, make it circular so max() can be computed in O(1)
        if (isDouble()){
            link (entry, first);
        }
    }

    private void link(Entry first, Entry second){
        EntryNode parent = (EntryNode)first;
        parent.next = (EntryNode) second;
        if (isDouble()){
            ((EntryDNode)second).previous = (EntryDNode) parent;
        }
    }

    @Override
    public Entry<K, T> successor(Entry<K, T> entry) {
        EntryNode<K,T> su= ((EntryNode) entry).next;
        if (su!=first){
            return su;
        }
        return null;
    }

    @Override
    public Entry<K, T> min() {
        return first;
    }
}
