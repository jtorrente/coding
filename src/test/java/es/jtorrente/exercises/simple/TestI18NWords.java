package es.jtorrente.exercises.simple;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jtorrente on 26/05/2015.
 */
public class TestI18NWords {

    @Test
    public void test(){
        List<String> words = I18NWords.i18nWords("teststr", true);
        assertEquals(16, words.size());

        assertTrue(words.contains("teststr"));
        assertTrue(words.contains("t1ststr"));
        assertTrue(words.contains("t2tstr"));
        assertTrue(words.contains("t3str"));
        assertTrue(words.contains("t4tr"));
        assertTrue(words.contains("t5r"));

        assertTrue(words.contains("te1tstr"));
        assertTrue(words.contains("te2str"));
        assertTrue(words.contains("te3tr"));
        assertTrue(words.contains("te4r"));

        assertTrue(words.contains("tes1str"));
        assertTrue(words.contains("tes2tr"));
        assertTrue(words.contains("tes3r"));

        assertTrue(words.contains("test1tr"));
        assertTrue(words.contains("test2r"));

        assertTrue(words.contains("tests1r"));
    }

    @Test
    public void testCaching(){
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<1000; i++){
            builder.append("a");
        }
        String str = builder.toString();
        long initTime = System.nanoTime();
        I18NWords.i18nWords(str, false);
        System.out.println("Not caching = "+((System.nanoTime()-initTime)/1000000000F)+" sec");
        initTime = System.nanoTime();
        I18NWords.i18nWords(str, true);
        System.out.println("Caching = "+((System.nanoTime()-initTime)/1000000000F)+" sec");
    }
}
