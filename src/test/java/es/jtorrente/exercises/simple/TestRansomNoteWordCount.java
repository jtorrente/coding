package es.jtorrente.exercises.simple;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jtorrente on 31/05/2015.
 */
public class TestRansomNoteWordCount {

    @Test
    public void test(){
        RansomNoteWordCount wordCount = new RansomNoteWordCount();
        assertTrue(wordCount.checkBuildPossible("a b c b a", "c c b b a a"));
        wordCount = new RansomNoteWordCount();
        assertTrue(wordCount.checkBuildPossible("", ""));
        wordCount = new RansomNoteWordCount();
        assertTrue(wordCount.checkBuildPossible("a b c b a", "a a b b c"));
        wordCount = new RansomNoteWordCount();
        assertFalse(wordCount.checkBuildPossible("a b c b a", "a b b c"));
        wordCount = new RansomNoteWordCount();
        assertFalse(wordCount.checkBuildPossible("a b c b a", "a a b b"));
        wordCount = new RansomNoteWordCount();
        assertTrue(wordCount.checkBuildPossible("a b c b a", "a a b b c d d e e"));
    }
}
