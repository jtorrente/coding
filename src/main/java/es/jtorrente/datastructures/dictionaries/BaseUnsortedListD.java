package es.jtorrente.datastructures.dictionaries;

/**
 * Created by jtorrente on 05/07/2015.
 */
public abstract class BaseUnsortedListD<K extends Comparable, T> extends BaseListD<K,T>{
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

    @Override
    public void insert(Entry<K, T> entry) {
        EntryNode<K,T> newNode;
        if (!(entry instanceof EntryNode)){
            newNode = isDouble()?new EntryDNode<>():new EntryNode<>();
            newNode.key = entry.key;
            newNode.value = entry.value;
        } else {
            newNode = isDouble()?(EntryDNode<K,T>)entry:(EntryNode<K,T>)entry;
        }
        newNode.next = first;
        if (isDouble()&&first!=null){
            ((EntryDNode)first).previous = (EntryDNode) newNode;
        }
        first = newNode;
        updateMinMax(newNode);
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
