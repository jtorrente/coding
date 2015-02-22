package es.jtorrente.exercises.simple;

/**
 * Problem from Poundstons' book, page 41.
 *
 * How to get any exact change with the smallest number of coins possible.
 * Coins available: 1c, 5c, 10c, 25, 50c
 *
 * Created by jtorrente on 22/02/15.
 */
public class ExactChange {

    /**
     * @param changeToGive  The total amount to return, from 1c to 99c
     * @param availableCoins   5 lengthed array containing the number of coins available for each particular type (1c,5c,10c,25c,25c)
     * @param coinValues    5 lengthed array containing the value of each coin
     * @return  5-lengthed array with the number of coins of each type used to return the change
     */
    public int[] exactChange(int changeToGive, int[] availableCoins, int []coinValues){
        if (availableCoins==null || availableCoins.length!=5){
            throw new IllegalArgumentException("availableCoins cannot be null, and must contain 5 integers exactly");
        }

        int []coinsToGive = new int[availableCoins.length];
        int totalCoins = 0;
        for (int i=0; i<availableCoins.length; i++){
            if (availableCoins[i]<0){
                throw new IllegalArgumentException("availableCoins at position "+i+ " have a negative count of coins");
            }
            coinsToGive[i]=0;
            totalCoins+=availableCoins[i];
        }

        int currentCoin = availableCoins.length-1;
        int currentValue;

        while (totalCoins>0 && currentCoin>=0){
            currentValue = coinValues[currentCoin];
            if (changeToGive<currentValue || availableCoins[currentCoin]==0){
                currentCoin--;
            } else {
                changeToGive-=currentValue;
                coinsToGive[currentCoin]++;
                totalCoins--;
                availableCoins[currentCoin]--;
            }
        }

        if (changeToGive>0){
            return null;
        }

        return coinsToGive;
    }

}
