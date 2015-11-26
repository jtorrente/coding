package es.jtorrente.exercises.leetcode;

import java.util.HashMap;

/**
 * Created by jtorrente on 09/09/2015.
 */
public class LongestPalindrome {
    public static String shortestPalindrome(String s) {
        int l = s.length();
        char[] r = new char[l];
        for (int i=0; i<l; r[l-i-1] = s.charAt(i), i++);

        HashMap<Character, Integer> index = new HashMap<>();
        int max = 0;
        for (int i=0; i<l; i++){
            if (s.charAt(i)!=r[i] && index.containsKey(r[i])){
                max = Math.max(max, i-index.get(r[i]));
            }
            index.put(r[i], i);
        }
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<max; i++){
            builder.append(r[i]);
        }
        builder.append(s);
        return builder.toString();
    }

    public static String shortestPalindromeLarge(String s) {
        String prefix = "";
        String p = s;
        int j = s.length()-1;
        while (!isPalindrome(p)) {
            prefix += s.charAt(j);
            p = prefix+s;
            j--;
        }
        return p;
    }

    private static boolean isPalindrome(String s) {
        int l = s.length();
        for (int i=0; i<l/2; i++) {
            if (s.charAt(i)!=s.charAt(l-i-1)){
                return false;
            }
        }
        return true;
    }
}
