package es.jtorrente.exercises.leetcode;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Problem: https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 For example,
 Given [100, 4, 200, 1, 3, 2],
 The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

 Your algorithm should run in O(n) complexity.
 *
 * Created by jtorrente on 06/07/2015.
 */
public class ConsecutiveSequence {

    /**
     * Solution with heap. Cost is O(nlogn) but the system seems to accept the solution
     * @param nums
     * @return
     */
    public int longestConsecutiveHeap(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<nums.length; i++){
            pq.offer(nums[i]);
        }
        int maxLength = 1;
        int currentLength = 1;
        int previous = pq.poll();
        while (!pq.isEmpty()){
            int c = pq.poll();
            if (previous+1==c){
                currentLength++;
            }
            else if (previous!=c){
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
            previous = c;
        }
        maxLength = Math.max(maxLength, currentLength);
        return maxLength;
    }


    /**
     * The first implementation I came up with, which was reasonably close to the optimum solution.
     * It just uses a HashMap to store the length of the max seq of consecutive elements for each
     * element that appeared in the array. That is, for example:
     *
     * 100->1
     * 200->1
     * 4->2
     * 3->2
     * 1->1
     *
     * Means that 1,3,4,100 and 200 has been found. The longest sequence is determined by 3,4, and that's why
     * these elements have a 2 associated.
     *
     * When a new element E is processed, E-1 and E+1 are looked up in the hashmap. The total length is updated
     * according to the value of these elements (+1). The only problem is that then I updated all elements in the
     * sequence, when only begin and end need to be updated. This caused the algorithm to be O(n^2) instead of O(n)
     *
     * @param nums
     * @return
     */
    public int longestConsecutiveHashMapInefficient(int[] nums) {
        Counter c = new Counter();
        for (int i=0; i<nums.length; i++){
            c.logElem(nums[i]);
        }
        return c.max;
    }

    private static class Counter{
        HashMap<Integer, Integer> seqLengths = new HashMap<>();
        int max = 0;
        void logElem(int key){
            // Repeated numbers do not provide additional information
            if (seqLengths.containsKey(key)){
                return;
            }
            int lc = getLeftCount(key);
            int rc = getRightCount(key);
            int newSeqLength = lc+rc+1;
            max = Math.max(max, newSeqLength);
            if (lc>0){
                update(key, newSeqLength, true);
            }
            if (rc>0){
                update(key, newSeqLength, false);
            }
            seqLengths.put(key, newSeqLength);
        }

        void update(int key, int newLength, boolean left){
            int k = left?key-1:key+1;
            if (seqLengths.containsKey(k)){
                seqLengths.put(k, newLength);
                update(k,newLength,left);
            }
        }

        int getLeftCount(int key){
            if (seqLengths.containsKey(key-1)){
                return seqLengths.get(key-1);
            }
            return 0;
        }
        int getRightCount(int key){
            if (seqLengths.containsKey(key+1)){
                return seqLengths.get(key+1);
            }
            return 0;
        }
    }

    /**
     * This solution is very close to {@link #longestConsecutiveHashMapInefficient(int[])}, using a HashMap with the
     * same purpose. The main difference is that only left and right ends of sequence are updated.
     * @param nums
     * @return
     */
    public int longestConsecutiveBest(int[] nums) {
        HashMap<Integer,Integer> sequences = new HashMap<>();
        int max = 0;
        for (int i=0; i<nums.length; i++){
            int c = nums[i];
            if (sequences.containsKey(c)){
                continue;
            }
            sequences.put(c,1);
            int b = c;
            int e = c;
            if (sequences.containsKey(c-1)){
                b = c-sequences.get(c-1);
            }
            if (sequences.containsKey(c+1)){
                e = c+sequences.get(c+1);
            }
            int l = e-b+1;
            sequences.put(b,l);
            sequences.put(e,l);
            max = l>max?l:max;
        }
        return max;
    }
}
