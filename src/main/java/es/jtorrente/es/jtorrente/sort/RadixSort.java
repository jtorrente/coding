package es.jtorrente.es.jtorrente.sort;

/**
 * Created by jtorrente on 11/07/2015.
 */
public class RadixSort extends SortAlgorithm {

    private int maxValue;

    public RadixSort(int max) {
        maxValue = max;
    }
    @Override
    public void sort(int[] nums) {
        int[] array = new int[maxValue+1];
        for (int i=0; i<array.length; i++) {
            array[i] = 0;
        }
        for (int i=0; i<nums.length; i++) {
            array[nums[i]]++;
        }
        int i=0;
        int j=0;
        while (i<array.length && j<nums.length){
            if (array[i]>=1){
                nums[j] = i;
                array[i]--;
                j++;
            }
            if (array[i]==0){
                i++;
            }
        }
    }

    @Override
    public String name() {
        return "RadixSort (O(n))";
    }
}
