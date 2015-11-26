package es.jtorrente.algorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by jtorrente on 12/09/2015.
 */
public class Knapsack {

    /**
     * Dynamic programming, pseudo polynomial time algorithm for cases where items are indivisible.
     */
    public static int knapsackBounded(int[] weights, int[] benefits, int maxWeight){
        int n = weights.length;
        int[][] score = new int[n+1][maxWeight+1];
        // Iterate through elements
        for (int i=0; i<n; i++){
            int wi = weights[i];
            int bi = benefits[i];
            // Iterate through weights
            for (int w=1; w<=maxWeight; w++) {
                int sameWeight = score[i][w];
                int prevWeight = w-wi>=0?score[i][w-wi]+bi:0;
                score[i+1][w] = Math.max(sameWeight, prevWeight);
            }
        }
        return score[n][maxWeight];
    }

    public static float knapsackUnbounded(int[] weights, int[] benefits, int maxWeight) {
        class Item{
            int weight;
            int benefit;
            Item(int w, int b){
                this.weight = w;
                this.benefit = b;
            }
        }
        Item[] items = new Item[benefits.length];
        for (int i=0; i<benefits.length; i++){
            items[i] = new Item(weights[i], benefits[i]);
        }
        Arrays.sort(items, (o1, o2) -> {
            float bpw1 = (1.0F*o1.benefit)/o1.weight;
            float bpw2 = (1.0F*o2.benefit)/o2.weight;
            return bpw1<bpw2?1:(bpw1==bpw2?0:-1);
        });

        float maxBenefit = 0;
        int remainingWeight = maxWeight;
        for (int i=0; i<items.length && remainingWeight>0; i++){
            Item item = items[i];
            float weight = Math.min(item.weight, remainingWeight);
            remainingWeight -= weight;
            maxBenefit += item.benefit*(weight/item.weight);
        }
        return maxBenefit;
    }


}
