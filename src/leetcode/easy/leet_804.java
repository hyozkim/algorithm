package leetcode.easy;

import java.util.HashSet;
import java.util.Set;

public class leet_804 {
    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
    }

    static String[] alphabets = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        for(String word:words) {
            StringBuilder sb = new StringBuilder();
            for( char ch : word.toCharArray() ) {
                sb.append(""+alphabets[ch-'a']);
            }
            set.add(sb.toString());
        }

        return set.size();
    }
}
