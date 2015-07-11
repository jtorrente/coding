package es.jtorrente.es.jtorrente.sort;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * Created by jtorrente on 11/07/2015.
 */
public class MergeSortConcurrent extends SortAlgorithm {
    private int[] nums;
    ExecutorService executor;

    @Override
    public void sort(int[] nums) {
        executor = Executors.newFixedThreadPool(32);
        this.nums = nums;
        MergeSort mergeSort = new MergeSort(0, nums.length-1);
        try {
            executor.submit(mergeSort).get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public String name() {
        return "MergeSort Concur.";
    }

    private class MergeSort implements Runnable {
        int from;
        int to;

        private static final int SIZE_THRESHOLD = 5000;

        MergeSort(int from, int to){
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            int median = (from+to)/2;
            if (to-from+1<SIZE_THRESHOLD) {
                doMergeSort(from, to);
                return;
            }

            Future fut1 = executor.submit(new MergeSort(from, median));
            Future fut2 = executor.submit(new MergeSort(median+1, to));
            try {
                fut1.get();
                fut2.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return;
            }

            merge(from, to);
            //invokeAll(new MergeSort(from, median), new MergeSort(median+1, to));
        }

        private void doMergeSort(int from, int to){
            if (from>=to) {
                return;
            }

            int median = (from+to)/2;
            doMergeSort(from, median);
            doMergeSort(median+1, to);
            merge(from, to);
        }

        private void merge(int from, int to) {
            int median = (from+to)/2;
            int[] left = Arrays.copyOfRange(nums, from, median+1);
            int[] right = Arrays.copyOfRange(nums, median+1, to+1);
            int i1 = 0;
            int i2 = 0;
            int i = from;
            while (i1< left.length && i2<right.length) {
                if (left[i1]<right[i2]){
                    nums[i] = left[i1];
                    i1++;
                } else {
                    nums[i] = right[i2];
                    i2++;
                }
                i++;
            }

            int[] pending;
            int j;
            if (i1==left.length){
                pending = right;
                j = i2;
            } else {
                pending = left;
                j = i1;
            }

            while (j<pending.length) {
                nums[i] = pending[j];
                j++;
                i++;
            }
        }


    }
}
