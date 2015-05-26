package es.jtorrente.exercises.simple;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Question:
 * i18n (where 18 stands for the number of letters between the first i and the last n in the word “internationalization,”) Wiki it.

 Generate all such possible i18n strings for any given string. for eg.
 "careercup"=>"c7p","ca6p","c6up","car5p","ca5up","care4p","car4up","caree3p","care3up"..till the count is 0 which means its the complete string again.

 Link: http://www.careercup.com/question?id=5733696185303040

 * Created by jtorrente on 26/05/2015.
 */
public class I18NWords {
    public static List<String> i18nWords(String s, boolean caching){
        // Object to return
        List<String> i18nWords = new ArrayList<>();
        i18nWords.add(s);

        // Use this cache to avoid unnecessary calls to substring().
        // Test shows this improves performance: around 25%
        Map<Integer, String> cache = new HashMap<>();

        String prefix = "";
        for (int i=0; i<s.length()-2; i++){
            prefix+=s.charAt(i);
            for (int j=i+1; j<s.length()-1; j++){
                if (caching&&!cache.containsKey(j+1)){
                    cache.put(j + 1, s.substring(j + 1, s.length()));
                }
                i18nWords.add(prefix+(j-i)+(caching?cache.get(j + 1):s.substring(j + 1, s.length())));
            }
        }

        return i18nWords;
    }
}