package es.jtorrente.sort;

/**
 * Created by jtorrente on 11/07/2015.
 */
public abstract class SortAlgorithm {

    public abstract void sort(int[]nums);

    public abstract String name();

    protected void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
