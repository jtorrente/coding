package es.jtorrente.sort;

/**
 * Created by jtorrente on 11/07/2015.
 */
public class InsertionSort extends SortAlgorithm {
    @Override
    public void sort(int[] nums) {
        for (int i=1; i<nums.length; i++) {
            int j=i;
            while (j>=1 && nums[j]<nums[j-1]) {
                swap(nums, j, j-1);
                j--;
            }
        }
    }

    @Override
    public String name() {
        return "Insertion (O(n^2))";
    }
}
