package es.jtorrente.exercises.simple;

/**
 * From Gayle Laakmann's book, page 77.
 * Problem: "Write code to partition a linked list around a value x,
 * such that all nodes less than x come before all nodes greater than
 * or equals to x
 *
 * Approach: Keep two lists, one for the smaller elements, another for
 * the large ones. Traverse the list, removing all elements and adding
 * them to the list that corresponds. Finally, append both lists
 */
public class PartitionLinkedLists{
    // Assumption: There's a LinkedList class available.
    // LinkedList has a next attribute to point to the next node
    // LinkedList has a 'value' integer attribute
    // Attributes are public (to simplify code)
	/* Missing doc here */
    public static LinkedList partitionList(LinkedList list, int x){
        // Pointers needed:
        // 1) lastSmall: points to the last element smaller than x
        LinkedList lastSmall = null;
        // 2) firstLarge: points to the first element greater than x (should go immediately after last Small)
        LinkedList firstLarge = null;
        LinkedList lastLarge = null;
        // 3) current: to iterate
        LinkedList current = list;

        // Traverse the list
        while (current!=null){
            // First, delete current from list
            LinkedList nextCurrent = current.next;
            if (current.value<x) {
                // Insert in list of small values
                if (lastSmall == null) {
                    lastSmall = current;
                    list = lastSmall;
                } else {
                    lastSmall.next = current;
                    lastSmall = current;
                }
            } else {
                // Insert in list of large values
                if (firstLarge == null){
                    firstLarge = lastLarge = current;
                } else {
                    lastLarge.next = current;
                    lastLarge = current;
                }
            }
            current.next = null;
            current = nextCurrent;
        }

        // Connect lastSmall and firstLarge
        if (lastSmall!=null){
            lastSmall.next = firstLarge;
        } else {
            list = firstLarge;
        }

        if (lastLarge!=null){
            lastLarge.next = null;
        }

        return list;
    }

    // Wouldn't implement this like that in real life
    public static class LinkedList {
        public int value;
        public LinkedList next;
    }
}
