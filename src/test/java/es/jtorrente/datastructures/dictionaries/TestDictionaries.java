package es.jtorrente.datastructures.dictionaries;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by jtorrente on 05/07/2015.
 */
public class TestDictionaries {

    @Test
    public void test(){
        testDictionary(UnsortedArrayD.class);
        testDictionary(SortedArrayD.class);
        testDictionary(UnsortedSinglyListD.class);
    }

    private void testDictionary(Class<? extends Dictionary> clazz){
        int []expectedOrder = {1,2,4,6};

        boolean l = !clazz.isAssignableFrom(BaseArrayD.class);
        Dictionary<Integer, String> dic = buildDictionary(clazz);
        assertNull(dic.min());
        assertNull(dic.max());
        dic.insert(e(5,l));
        dic.insert(e(4,l));
        dic.delete(e(5,l));
        dic.insert(e(2,l));
        dic.insert(e(6,l));
        dic.insert(e(1,l));
        assertNull(dic.search(5));
        assertNull(dic.search(-1));
        assertEquals("4", dic.search(4).value);

        int[]allElements = new int[4];
        Dictionary.Entry<Integer, String> current = dic.min();
        assertEquals(1, (int)current.key);
        int i=0;
        while(current!=null){
            allElements[i]=current.key;
            current = dic.successor(current);
            i++;
        }
        assertTrue(Arrays.equals(allElements, expectedOrder));

        int[]allElements2 = new int[4];
        current = dic.max();
        assertEquals(6, (int)current.key);
        i=3;
        while(current!=null){
            allElements2[i]=current.key;
            current = dic.predecessor(current);
            i--;
        }
        assertTrue(Arrays.equals(allElements2, expectedOrder));
    }

    private Dictionary<Integer, String> buildDictionary(Class<? extends Dictionary> clazz){
        try {
            Dictionary<Integer, String> dic = clazz.newInstance();
            return dic;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Dictionary.Entry<Integer, String> e(int n, boolean list){
        Dictionary.Entry<Integer,String> entry;
        if (list){
            entry = new UnsortedSinglyListD.EntryNode<>();
        } else {
            entry = new Dictionary.Entry<>();
        }
        entry.key = n;
        entry.value = ""+n;
        return entry;
    }
}
