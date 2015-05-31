package es.jtorrente.exercises.simple;

import java.util.HashMap;

/**
 * Exercise from Gayle McDowell's book "Cracking the Coding Interview", 5th edition, p53.

 * Question:
 * "A ransom note can be formed by cutting words out of a magazine to form a new sentence.
 * How Would you figure out if a ransom note (represented as a String) can be formed from
 * a given magazine (String)?"
 *
 * Solution:
 * Count the number of occurrences of each of the words in the ransom note. A hash table can
 * be used for that. Then scan the magazine and remove the number of occurrences for each of
 * the words in the hash table. If when the process completes with no positive values in the
 * hash table, then the ransom note can be formed.
 */
public class RansomNoteWordCount {
    private HashMap<String, Integer> table = new HashMap<> ();

    // private initValue
    private void initValue(String word){
        if(!table.containsKey(word)){
            table.put(word, 0);
        }
    }

    // private occurrence
    private void occurrence(boolean add, String word){
        initValue(word);
        if (add){
            table.put(word, table.get(word)+1);
        }
        else {
            table.put(word, table.get(word)-1);
        }
    }

    // private getCount
    private int getCount(String word){
        initValue(word);
        return table.get(word);
    }

    private void scanRansom(String ransom){
        for (String word: ransom.split(" ")){
            occurrence(true, word);
        }
    }

    private void scanMagazine(String magazine){
        for (String word: magazine.split(" ")){
            if (table.containsKey(word)){
                occurrence(false, word);
                if (getCount(word)<=0){
                    table.remove(word);
                }
            }
        }
    }

    public boolean checkBuildPossible(String ransom, String magazine){
        scanRansom(ransom);
        scanMagazine(magazine);
        return table.size()==0;
    }

}
