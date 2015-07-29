package es.jtorrente.sort;

import java.util.Arrays;

/**
 * Created by jtorrente on 11/07/2015.
 */
public class MergeSort extends SortAlgorithm{
    @Override
    public void sort(int[] nums) {
        mergeSort(nums, 0, nums.length-1);
    }

    private void mergeSort(int[] nums, int from, int to) {
        if (from>=to) {
            return;
        }

        int median = (from+to)/2;
        mergeSort(nums, from, median);
        mergeSort(nums, median+1, to);

        int[] leftArray = Arrays.copyOfRange(nums, from, median+1);
        int[] rightArray = Arrays.copyOfRange(nums, median+1, to+1);

        int i1=0;
        int i2=0;
        int i = from;
        while (i1<leftArray.length && i2<rightArray.length) {
            if (leftArray[i1]<rightArray[i2]) {
                nums[i] = leftArray[i1];
                i1++;
            } else {
                nums[i] = rightArray[i2];
                i2++;
            }
            i++;
        }

        int[] arrayRemaining;
        int j;
        if (i1==leftArray.length) {
            arrayRemaining = rightArray;
            j = i2;
        } else {
            arrayRemaining = leftArray;
            j = i1;
        }

        while (j<arrayRemaining.length) {
            nums[i]=arrayRemaining[j];
            i++;
            j++;
        }
    }

    @Override
    public String name() {
        return "MergeSort (O(nlgn))";
    }
}
