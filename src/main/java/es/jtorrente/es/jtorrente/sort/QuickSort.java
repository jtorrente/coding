package es.jtorrente.es.jtorrente.sort;

import java.util.Random;

/**
 * Created by jtorrente on 11/07/2015.
 */
public class QuickSort extends SortAlgorithm{

    private Random random = new Random();
    @Override
    public void sort(int[] nums) {
        sort(nums, 0, nums.length-1);
    }

    private void sort(int[] nums, int from, int to) {
        if (from>=to) {
            return;
        }
        int pivot = random.nextInt(to-from+1)+from;
        swap(nums, to, pivot);
        pivot = to;

        int firstHigh = from;
        for (int i=from; i<to; i++) {
            if (nums[i] < nums[pivot]) {
                swap(nums, i, firstHigh);
                firstHigh++;
            }
        }
        swap(nums, firstHigh, pivot);
        pivot = firstHigh;

        sort(nums, from, pivot-1);
        sort(nums, pivot+1, to);
    }

    @Override
    public String name() {
        return "QuickSort (O(nlgn))";
    }
}
