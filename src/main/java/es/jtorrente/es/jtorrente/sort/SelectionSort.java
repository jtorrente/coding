package es.jtorrente.es.jtorrente.sort;

/**
 * Cost: n^2
 * Created by jtorrente on 11/07/2015.
 */
public class SelectionSort extends SortAlgorithm{
    @Override
    public void sort(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            int minIndex = i;
            for (int j=i+1; j<nums.length; j++) {
                if (nums[j]<nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    @Override
    public String name() {
        return "Selection (O(n^2))";
    }

}
