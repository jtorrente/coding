package es.jtorrente.exercises.simple;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jtorrente on 22/02/15.
 */
public class TestExactChange {

    private ExactChange testObject;

    @Before
    public void setup(){
        testObject = new ExactChange();
    }

    @Test
    public void test(){
        for (int i=1; i<=99;i++){
            testImpl(i);
        }
    }

    public void testImpl(int changeToGive){
        int [] availableCoins = new int[]{4,2,1,1,1};
        int [] coinValues = new int[]{1,5,10,25,50};
        int[] coinsGiven = testObject.exactChange(changeToGive, availableCoins, coinValues);
        assertNotNull(coinsGiven);
        int actualChangeGiven = 0;
        for (int i=0; i<coinsGiven.length; i++){
            actualChangeGiven+=coinsGiven[i]*coinValues[i];
        }
        assertEquals(actualChangeGiven, changeToGive);
    }
}
