package es.jtorrente.exercises.simple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * From Gayle's book, page 110:
 * "Implement an algorithm to print all valid (i.e. properly opened and closed) combinations
 * of n-pairs of parentheses"
 * 
 * Example: n=3
 * ()()(), ()(()), (())(), (()()), ((()))
 * 
 * Created by jtorrente on 02/07/2015.
 */
public class Parentheses {

    public static void printAllParentheses(int n){
        HashSet<String> allParentheses = buildAllParentheses(n);
        for (String sol: allParentheses){
            System.out.println(sol);
        }
    }

    /**
     * This is the first solution I came up with. It is simple but is not very efficient and
     * does not generate all valid combinations.
     * @param n
     * @return
     */
    public static HashSet<String> buildAllParentheses(int n){
        // Base cases
        if (n == 0){
            return emptyList();
        }
        if (n == 1){
            return listWithOne();
        }
        
        /* Recursive case: Call buildAllParentheses(n-1) and
           for each solution add "()" + sol, sol + "()", "("+sol+")" 
         */
        HashSet<String> prevSols = buildAllParentheses(n-1);
        HashSet<String> currentSols = new HashSet<>();
        for (String prevSol: prevSols){
            currentSols.add("()"+prevSol);
            currentSols.add(prevSol+"()");
            currentSols.add("(" + prevSol + ")");
        }
        return currentSols;
    }

    private static HashSet<String> emptyList(){
        HashSet<String> emptyList = new HashSet<>();
        emptyList.add(new String(""));
        return emptyList;
    }

    private static HashSet<String> listWithOne(){
        HashSet<String> emptyList = new HashSet<>();
        emptyList.add(new String("()"));
        return emptyList;
    }

    public static void printAllParentheses2(int n){
        List<String> allParen = buildAllParentheses2(n);
        for (String s: allParen){
            System.out.println(s);
        }
    }

    /**
     * This second solution is much more efficient, both in time and memory, and generates all
     * valid combinations. It is better because it does not use recursion, so it saves memory,
     * and it does not need to check for repeated elements
     *
     * Time cost: O(n*2^n), because in each iteration (i) the available solutions,
     * which are arrays, are duplicated to make the concatenation of the next paren.
     * That operation takes cost O(i). This process is repeated for all possible previous solutions,
     * which are 2^n
     *
     * Memory cost: O(2^n), because in each step of the loop two new solutions may be
     * added, each requiring an array of size i+1
     *
     * @param n
     * @return
     */
    public static List<String> buildAllParentheses2(int n){
        List<Solution> previousSols = new ArrayList<>();
        previousSols.add(new Solution());
        List<Solution> newSols = new ArrayList<>();
        for (int i=0; i<n*2; i++){
            for (Solution prevSol: previousSols){
                if (prevSol.nLeftParen<n){
                    newSols.add(prevSol.leftParen());
                }
                if (prevSol.nLeftParen > prevSol.nRightParen){
                    newSols.add(prevSol.rightParen());
                }
            }
            previousSols = newSols;
            newSols = new ArrayList<>();
        }

        List<String> toReturn = new ArrayList<>();
        for (Solution sol: previousSols){
            toReturn.add(sol.s);
        }
        return toReturn;
    }

    public static void printAllParentheses3(int n){
        ArrayList<String> allCombinations = new ArrayList<>();
        buildAllParentheses3(n, allCombinations, "", 0, 0);
        for (String combination:allCombinations){
            System.out.println(combination);
        }
    }

    public static void buildAllParentheses3(int n, List<String> allCombinations, String prevSol, int leftParen, int rightParen){
        // Base case: no parens left
        if (prevSol.length() == 2*n){
            allCombinations.add(prevSol);
            return;
        }

        // Left parens still can be added
        if (leftParen<n){
            buildAllParentheses3(n, allCombinations, prevSol+"(", leftParen+1, rightParen);
        }

        // Right parens can be added
        if (leftParen>rightParen){
            buildAllParentheses3(n, allCombinations, prevSol+")", leftParen, rightParen+1);
        }
    }

    private static class Solution{
        String s;
        int nLeftParen;
        int nRightParen;

        Solution(){
            s = "";
            nLeftParen = nRightParen = 0;
        }

        Solution rightParen(){
            Solution newS = new Solution();
            newS.s = this.s+")";
            newS.nLeftParen = this.nLeftParen;
            newS.nRightParen = this.nRightParen +1;
            return newS;
        }

        Solution leftParen(){
            Solution newS = new Solution();
            newS.s = this.s+"(";
            newS.nLeftParen = this.nLeftParen+1;
            newS.nRightParen = this.nRightParen;
            return newS;
        }
    }

}
