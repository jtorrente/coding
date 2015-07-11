package es.jtorrente.datastructures;

import java.util.Arrays;

/**
 * Created by jtorrente on 11/07/2015.
 */
public class Heap {

    private static final int DEFAULT_INITIAL_SIZE = 20;

    public enum Type {
        MIN, MAX;
    }

    private Type type;
    private int size;
    private int[] nums;

    public Heap(Type type, int[] nums){
        this.type = type;
        this.size = 0;
        this.nums = nums;
    }

    public Heap(Type type){
        this(type, new int[DEFAULT_INITIAL_SIZE]);
    }

    public Heap(){
        this(Type.MIN);
    }

    public Heap(int[] array){
        this(Type.MIN, array);
    }

    public void add(int number){
        nums[size++] = number;
        checkResizeArray();

        int i = size-1;
        int p = getParentIndex(i);

        while (p!=-1 && dominates(p, i)!=-1) {
            swap(i, p);
            i = p;
            p = getParentIndex(i);
        }
    }

    public int size() {
        return size;
    }

    public int getMin(){
        if (size==0) {
            return Integer.MAX_VALUE;
        }
        return nums[0];
    }

    public int removeMin(){
        int min = getMin();
        if (size==0){
            return min;
        }
        nums[0] = nums[--size];

        int i=0;
        int left = getLeftChildIndex(i);
        int right = left+1;

        while (i<(size-1)/2F && (dominates(i, left)!=-1 || dominates(i, right)!=-1)) {
            if (dominator(i, left, right)==nums[left]){
                swap(i, left);
                i = left;
            } else {
                swap(i, right);
                i = right;
            }
            left = getLeftChildIndex(i);
            right = left+1;
        }
        return min;
    }

    private int dominator (int i, int j, int k){
        if (type== Type.MIN){
            return Math.min(Math.min(nums[i], nums[j]), nums[k]);
        }
        return Math.max(Math.max(nums[i], nums[j]), nums[k]);
    }

    private void swap(int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int dominates(int i, int j){
        if (i<0 || j<0 || i>=size || j>=size) {
            return Integer.MIN_VALUE;
        }
        if (type==Type.MIN) {
            return nums[i]<nums[j]?-1:(nums[i]==nums[j]?0:1);
        } else {
            return nums[i]>nums[j]?-1:(nums[i]==nums[j]?0:1);
        }
    }

    private void checkResizeArray(){
        if (size == nums.length) {
            nums = Arrays.copyOf(nums, size*2);
        }
    }

    private int getParentIndex(int index){
        if (index==0) {
            return -1;
        }
        return (index-1)/2;
    }

    private int getLeftChildIndex(int index){
        return index<=(size-1)/2F?index*2+1:-1;
    }
}
