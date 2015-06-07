package es.jtorrente.exercises.simple;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by jtorrente on 07/06/2015.
 */
public class TestPartitionLinkedLists {

    @Test
    public void test(){
        compare(PartitionLinkedLists.partitionList(build(), 3), build());
        compare(PartitionLinkedLists.partitionList(build(1), 3), build(1));
        compare(PartitionLinkedLists.partitionList(build(5), 3), build(5));
        compare(PartitionLinkedLists.partitionList(build(1,2,0,1,2), 3), build(1,2,0,1,2));
        compare(PartitionLinkedLists.partitionList(build(9,4,5,7,6), 3), build(9,4,5,7,6));
        compare(PartitionLinkedLists.partitionList(build(4,5,0,1,2), 3), build(0,1,2,4,5));
        compare(PartitionLinkedLists.partitionList(build(2,1,4,3,1,2,5,5,5,2,1,0), 3), build(2,1,1,2,2,1,0,4,3,5,5,5));

    }

    private PartitionLinkedLists.LinkedList build(Integer...numbers){
        PartitionLinkedLists.LinkedList list = null;
        PartitionLinkedLists.LinkedList current = null;
        for (Integer n: numbers){
            if (list == null){
                list = new PartitionLinkedLists.LinkedList();
                list.value = n;
                current = list;
            } else {
                current.next = new PartitionLinkedLists.LinkedList();
                current.next.value = n;
                current = current.next;
            }
        }
        return list;
    }

    private void compare(PartitionLinkedLists.LinkedList l1, PartitionLinkedLists.LinkedList l2){
        if (l1==null && l2==null){
            return;
        }
        if (l1==null || l2==null){
            fail(m(l1,l2,"One list is null, the other isn't"));
        }

        PartitionLinkedLists.LinkedList current1 = l1;
        PartitionLinkedLists.LinkedList current2 = l2;

        while (current1!=null && current2!=null){
            assertEquals (m(l1,l2, "Mistmatch in values: "+current1.value+"  "+ current2.value), current1.value, current2.value);
            current1 = current1.next;
            current2 = current2.next;
        }

        assertTrue(m(l1, l2, "One list is shorter"), (current1==null) == (current2==null));
    }

    private String m(PartitionLinkedLists.LinkedList l1, PartitionLinkedLists.LinkedList l2, String append){
        return "List1="+listToString(l1)+"  List2="+listToString(l2)+": "+append;
    }

    private String listToString(PartitionLinkedLists.LinkedList list){
        if (list==null){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        PartitionLinkedLists.LinkedList current = list;
        while (current!=null){
            if (current!=list) {
                builder.append(", ");
            }
            builder.append(current.value);
            current = current.next;
        }
        builder.append("]");
        return builder.toString();
    }
}
