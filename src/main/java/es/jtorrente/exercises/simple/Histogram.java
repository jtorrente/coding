package es.jtorrente.exercises.simple;

import java.util.Stack;

/**
 * Problem: Calculate max rectangle in an histogram, represented as an array of its bars' heights.
 * Each bar has width 1
 * Created by jtorrente on 07/09/2015.
 */
public class Histogram {

    public static int maxRectangle(int[] bars) {
        if (bars == null || bars.length == 0) {
            return 0;
        }

        int n = bars.length;
        int maxH = bars[0];
        for (int i=1; i<n; maxH = Math.max(maxH, bars[i]), i++);

        int maxArea = 0;
        for (int h=1; h<=maxH; h++) {
            int w = 0;
            int area = 0;
            for (int i=0; i<n; w = bars[i]>=h?w+1:0, area = Math.max(area, w*h), i++);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static int maxRectangleStack(int[] bars) {
        if (bars == null || bars.length == 0) {
            return 0;
        }

        int n = bars.length;
        Stack<Integer> barIndeces = new Stack<>();  // At any moment, elements s_i in stack are of increasing height
                                                    // bars[s0]>=bars[s1]>=...>=bars[sk] (sk is at peek)
        int maxArea = 0;
        int currentIndex = 0;
        while (currentIndex<n) {
            if (barIndeces.empty() || bars[currentIndex] >= bars[barIndeces.peek()]) {
                barIndeces.push(currentIndex);
                currentIndex++;
            } else {
                maxArea = Math.max(maxArea, calculateMaxAreaStack(bars, currentIndex, barIndeces));
            }
        }

        // Remaining rectangles to calculate
        while (!barIndeces.empty()) {
            maxArea = Math.max(maxArea, calculateMaxAreaStack(bars, currentIndex, barIndeces));
        }

        return maxArea;
    }

    private static int calculateMaxAreaStack(int[] bars, int currentIndex, Stack<Integer> barIndeces) {
        int height = bars[barIndeces.pop()];
        int width = barIndeces.empty()?currentIndex:currentIndex-barIndeces.peek()-1;
        return height*width;
    }

}
