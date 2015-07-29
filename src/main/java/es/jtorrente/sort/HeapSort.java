package es.jtorrente.sort;

import es.jtorrente.datastructures.Heap;

/**
 * Created by jtorrente on 11/07/2015.
 */
public class HeapSort extends SortAlgorithm{
    @Override
    public void sort(int[] nums) {
        Heap heap = new Heap(Heap.Type.MIN, nums);
        for (int num: nums) {
            heap.add(num);
        }

        for (int i=0; i<nums.length; i++) {
           nums[i] = heap.removeMin();
        }
    }

    @Override
    public String name() {
        return "HeapSort (O(nlogn))";
    }
}
