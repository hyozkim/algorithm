package leetcode.easy;

import java.util.Stack;

// Valid Parentheses - Easy
public class leet_20 {
    public static boolean isValid(String s) {
        if( s.length() == 0 ) return true;
        if( s.length() == 1 ) return false;

        boolean valid = false;
        Stack<Character> st = new Stack<>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if( charArray[i] == '[' || charArray[i] == '{' || charArray[i] == '(' ) {
                st.add(charArray[i]);

            } else {
                if( st.size() < 1 ) {
                    valid = false;
                    break;
                }
                if( charArray[i] == ']' && st.peek() == '[' ) {
                    valid = true;
                    st.pop();
                } else if( charArray[i] == '}' && st.peek() == '{' ) {
                    valid = true;
                    st.pop();
                } else if( charArray[i] == ')' && st.peek() == '(' ) {
                    valid = true;
                    st.pop();
                } else {
                    valid = false;
                    break;
                }
            }
        }

        return st.size() > 0 ? false : valid;
    }

    public static void main(String[] args) {
        //String s = "()";
        //String s = "()[]{}";
        //String s = "(]";
        //String s = "([)]";
        //String s = "{[]}";
        //String s = "([]";
        String s = ")";

        System.out.println(isValid(s));
    }
}
