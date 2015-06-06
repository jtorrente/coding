package es.jtorrente.exercises.simple;

/**
 * Exercise from Gayle Laakmann's book (page 73):
 *
 * "Given two strings, write a method to decide if one is a permutation of the other"
 */
public class StringPermutation {

    // Number of allowed characters

    private static int N_LETTERS = 'z' - 'a' +1;
    private static int N_CHARS = N_LETTERS*2 + 10;

    /** @return The index that a given char will have in the array, or -1 if it is not a valid
                character
            */
    private static int indexOf (char c) {
        if (c >='a' && c<='z'){
            return c-'a';
        }
        else if (c >='A' && c<='Z'){
            return N_LETTERS + c-'A';
        }
        else if (c >='0' && c<='9'){
            return N_LETTERS*2 + c-'0';
        }
        return -1;
    }

    /**@returns An array with {@link #N_CHARS} positions. For each given i,
       array[i] contains the number of occurrences of a valid character.
       @throws IllegalArgumentException if the given string contains an unsupported character
    */
    private static int[] countChars(String str){
        int[] array = new int [N_CHARS];
        for (int i=0; i<str.length(); i++){
            int posInArray = indexOf(str.charAt(i));
            if (posInArray == -1) {
                throw new IllegalArgumentException("Character "+str.charAt(i)+" at position "+i + " is not supported in String "+str);
            }
            array[posInArray]++;
        }
        return array;
    }

    /** @returns True if str1 is a permutation of str2 */
    public static boolean isPermutation(String str1, String str2){
        if (str1==null && str2!=null || str1!=null && str2==null){
            return false;
        }
        if (str1==null && str2==null){
            return true;
        }
        if (str1.length()!=str2.length()){
            return false;
        }
        int[] count1 = countChars(str1);
        int[] count2 = countChars(str2);
        for (int i=0; i<N_CHARS; i++){
            if (count1[i]!=count2[i]){
                return false;
            }
        }
        return true;
    }
}

