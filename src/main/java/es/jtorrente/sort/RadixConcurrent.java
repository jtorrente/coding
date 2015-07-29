package es.jtorrente.sort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jtorrente on 12/07/2015.
 */
public class RadixConcurrent extends SortAlgorithm{

    int[] array;
    int[] nums;

    boolean firstHalfAvailable = false;
    boolean secondHalfAvailable = false;
    synchronized void setFirstHalfAvailable(){
        firstHalfAvailable = true;
    }
    synchronized void setSecondHalfAvailable(){
        secondHalfAvailable = true;
    }

    synchronized boolean isFirstHalfAvailable(){
        return firstHalfAvailable;
    }

    synchronized boolean isSecondHalfAvailable(){
        return secondHalfAvailable;
    }

    void incArray(int i){
        array[i]++;
    }

    public RadixConcurrent(int n){
        array = new int[n+1];
    }

    @Override
    public void sort(int[] nums) {
        this.nums = nums;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new RadixRunnable(0, (nums.length-1)/2));
        executorService.submit(new RadixRunnable(1+(nums.length-1)/2, nums.length-1));
        while (!isFirstHalfAvailable() || !isSecondHalfAvailable()){
            //try {
                //Thread.sleep(1);
            //} //catch (InterruptedException e) {
              //  e.printStackTrace();
           // }
        }

        int j = 0;
        for (int i=0; i<nums.length; i++) {
            while (array[j]==0){
                j++;
            }
            nums[i]=j;
            array[j]--;
        }
    }

    @Override
    public String name() {
        return "Radix Concurrent";
    }

    private class RadixRunnable implements Runnable {
        int from;
        int to;

        public RadixRunnable(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            for (int i=from; i<=to; i++) {
                incArray(nums[i]);
            }
            if (from==0){
                setFirstHalfAvailable();
            } else{
                setSecondHalfAvailable();
            }
        }
    }
}
