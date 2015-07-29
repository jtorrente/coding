package es.jtorrente.sort;

import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertTrue;

/**
 * Created by jtorrente on 11/07/2015.
 */
public class SortTest {

    private static int N = 100000;
    //private static int N = 10;
    private static final SortAlgorithm[] algorithms = {new MergeSortConcurrent(), new RadixConcurrent(100), new InsertionSort(), new HeapSort(), new SelectionSort(), new RadixSort(100), new QuickSort(), new MergeSort()};

    private static class AlgorithmRunningTime {
        String algorithmName;
        long runningTime;

        public AlgorithmRunningTime(String name, long time) {
            algorithmName = name;
            this.runningTime = time;
        }
    }

    private static class RunningTimeComparator implements Comparator<AlgorithmRunningTime> {

        @Override
        public int compare(AlgorithmRunningTime o1, AlgorithmRunningTime o2) {
            return Long.compare(o1.runningTime, o2.runningTime);
        }
    }

    @Test
    public void test(){
        List<AlgorithmRunningTime> runningTimes = new ArrayList<>();

        int[] original = generateArray(N);
        int[] expected = Arrays.copyOf(original, N);
        long time = System.nanoTime();
        Arrays.sort(expected);
        runningTimes.add(new AlgorithmRunningTime("Arrays.sort()   ", System.nanoTime()-time));


        for (SortAlgorithm algorithm: algorithms) {
            int[] source = Arrays.copyOf(original, N);
            time = System.nanoTime();
            algorithm.sort(source);
            runningTimes.add(new AlgorithmRunningTime(algorithm.name(), System.nanoTime() - time));
            //printArray(source);
            assertTrue(algorithm.name(), Arrays.equals(expected, source));
        }

        runningTimes.sort(new RunningTimeComparator());
        for (AlgorithmRunningTime runningTime: runningTimes) {
            System.out.println(runningTime.algorithmName+"\t\t"+runningTime.runningTime);
        }
    }

    private void printArray(int[] array){
        StringBuffer buffer = new StringBuffer();
        for (int num:array){
            buffer.append(num);
            buffer.append("  ");
        }
        System.out.println(buffer.toString());
    }

    private int[] generateArray(int nValues) {
        int[] array = new int[nValues];
        Random r = new Random();
        for (int i=0; i<nValues; i++) {
            array[i] = r.nextInt(100);
        }
        return array;
    }
}
