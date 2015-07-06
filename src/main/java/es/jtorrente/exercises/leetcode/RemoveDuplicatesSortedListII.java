package es.jtorrente.exercises.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jtorrente on 06/07/2015.
 */
public class RemoveDuplicatesSortedListII {

    /**
     * Solution that counts occurrences of each number, then creates a new list using only non duplicate values
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return null;
        }
        if (head.next==null){
            return head;
        }

        HashMap<Integer, Integer> nOccurrences = new HashMap<>();
        ArrayList<Integer> keys = new ArrayList<>();
        ListNode current = head;
        while(current!=null){
            if(!nOccurrences.containsKey(current.val)){
                nOccurrences.put(current.val,1);
                keys.add(current.val);
            } else {
                nOccurrences.put(current.val, nOccurrences.get(current.val)+1);
            }
            current = current.next;
        }

        ListNode first = null;
        ListNode last = null;
        for (int val:keys){
            if (nOccurrences.get(val)>1){
                continue;
            }
            if (first==null){
                first = last = new ListNode(val);
            } else {
                last.next = new ListNode(val);
                last = last.next;
            }
        }
        return first;
    }

    /**
    This solution just uses pointers. It is more efficient, both in time and memory, but the code is
     harder to understand and maintain.
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null){
            return null;
        }
        if (head.next==null){
            return head;
        }

        ListNode listFirst = null;
        ListNode listLast = null;
        ListNode p = head;
        ListNode c = head.next;
        // 1 2 2

        // p n
        // c n

        // f 1
        // l 1
        do {
            // P has to be added to the list
            if (p.val!=c.val){
                if (listFirst == null ){
                    listFirst = listLast = p;
                } else {
                    listLast.next = p;
                    listLast = p;
                }
                p = c;
                c = c.next;
            } else {
                do {
                    p = c;
                    c = c.next;
                } while (c!=null && p.val == c.val);
                if (c==null){
                    p = null;
                } else {
                    p = c;
                    c = c.next;
                }
            }
        } while (c!=null);

        if (p!=null){
            if (listFirst==null){
                listFirst = listLast = p;
            } else {
                listLast.next = p;
                listLast = p;
            }
        }

        if (listLast!=null){
            listLast.next = null;
        }
        return listFirst;
    }

    /**
     * This solution is slightly worse than deleteDuplicates2 in time efficiency, but the code is cleaner
     * and easier to understand
     * @param head
     * @return
     */
    public ListNode deleteDuplicates3(ListNode head) {
        if(head == null){
            return null;
        }
        if (head.next==null){
            return head;
        }

        Pointers ps = new Pointers(head);
        // 1 2 2

        // p n
        // c n

        // f 1
        // l 1
        do {
            // P has to be added to the list
            if (ps.pAndCDifferent()){
                ps.appendPToList();
                ps.iterate();
            } else {
                do {
                    ps.iterate();
                } while (ps.c!=null && ps.pAndCEquals());
                if (ps.c==null){
                    ps.p = null;
                } else {
                    ps.iterate();
                }
            }
        } while (ps.c!=null);

        if (ps.p!=null){
            ps.appendPToList();
        }

        if (ps.listLast!=null){
            ps.listLast.next = null;
        }
        return ps.listFirst;
    }

    private static class Pointers{
        ListNode listFirst = null;
        ListNode listLast = null;
        ListNode p;
        ListNode c;

        Pointers(ListNode head){
            init(head);
        }

        void init (ListNode head){
            p = head;
            c = head.next;
        }

        void appendPToList(){
            appendToList(p);
        }

        void appendToList(ListNode newNode){
            if (listFirst == null){
                listFirst = listLast = newNode;
            } else {
                listLast.next = newNode;
                listLast = newNode;
            }
        }

        void iterate(){
            p = c;
            c = c.next;
        }

        boolean pAndCEquals(){
            return p.val == c.val;
        }

        boolean pAndCDifferent(){
            return p.val!=c.val;
        }
    }
}
