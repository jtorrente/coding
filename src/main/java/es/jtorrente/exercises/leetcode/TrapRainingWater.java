package es.jtorrente.exercises.leetcode;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

 For example,
 Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

 Link to problem: https://leetcode.com/problems/trapping-rain-water/
 *
 * Created by jtorrente on 06/07/2015.
 */
public class TrapRainingWater {

    /**
     * First solution submitted for this problem. It is simple and works. O(n) both in time and memory.
     */
    public int trap(int[] height) {
        if (height==null || height.length<3){
            return 0;
        }
        int []maxs = new int[height.length-2];
        int max = height[0];
        // 0 1 1 2 2 2 2 3 3 3
        for (int i=1; i<height.length-1;i++){
            maxs[i-1]=max;
            max = Math.max(max,height[i]);
        }

        max=height[height.length-1];
        int sum=0;
        for (int i=height.length-2; i>0; i--){
            int m = Math.min(maxs[i-1], max)-height[i];
            if(m>0){
                sum+=m;
            }
            max = Math.max(max, height[i]);
        }
        return sum;
    }

    /**
     * Just for fun, another solution for the problem, using recursion. It is also O(n) both in memory and time, but
     * is worse as it needs to create n-2 recursive calls. In leetcode it produces stack overflow
     */
    public int trapRecursive(int[]height){
        if (height == null || height.length < 3){
            return 0;
        }
        return trapRecursive(height, height[0], 1).val;
    }

    public RightMaxAndValue trapRecursive(int[] height, int leftMax, int index) {
        if (index == height.length-2){
            return new RightMaxAndValue(leftMax, height, index);
        }

        RightMaxAndValue prev = trapRecursive(height, Math.max(leftMax, height[index]), index+1);
        prev.addIndex(leftMax, height, index);
        return prev;
    }

    private int value(int leftMax, int rightMax, int bar){
        int val = Math.min(leftMax, rightMax)-bar;
        return val>0?val:0;
    }

    private class RightMaxAndValue{
        int rightMax;
        int val;

        RightMaxAndValue(int leftMax, int[]height, int index){
            this.rightMax = Math.max(height[index], height[index+1]);
            this.val = value(leftMax, rightMax, height[index]);
        }

        void addIndex(int leftMax, int[]height, int index){
            this.val+=value(leftMax, rightMax, height[index]);
            this.rightMax = Math.max(height[index], rightMax);
        }
    }
}
