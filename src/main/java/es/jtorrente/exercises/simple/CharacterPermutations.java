package es.jtorrente.exercises.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jtorrente on 24/02/15.
 */
public class CharacterPermutations {

    public static void permutations (List<String> characters, List<String> words, int currentCharacterIndex){
        String currentCharacter = characters.get(currentCharacterIndex);
        List<String> newWords = new ArrayList<String>();
        if (words.size()==0){
            words.add(currentCharacter);
        } else {
            for (String w: words){
                for (int i=0; i<=w.length(); i++){
                    String newW = w.substring(0, i)+currentCharacter+(i<w.length()?w.substring(i, w.length()):"");
                    newWords.add(newW);
                }
            }

            for (String w: newWords){
                words.add(w);
            }
        }

        if (currentCharacterIndex<characters.size()-1){
            permutations(characters, words, currentCharacterIndex+1);
        }
    }

    public static void main (String[]args){
        List<String> characters = new ArrayList<String>();
        characters.add("S");
        characters.add("E");
        characters.add("I");
        characters.add("C");
        characters.add("A");
        characters.add("M");
        characters.add("T");

        List<String> words = new ArrayList<String>();

        permutations(characters, words, 0);

        for (String w: words){
            System.out.println(w);
        }
    }
}
