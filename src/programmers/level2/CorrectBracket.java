package programmers.level2;

import java.util.Stack;

// ? - 올바른 괄호
// Programmers Lv.2
public class CorrectBracket {
    public static boolean solution(String s) {
        char[] charArray = s.toCharArray();

        Stack<Character> stack = new Stack<>();
        for( char ch : charArray ) {
            //System.out.println(ch);
            if( ch == '(' ) {
                stack.push(ch);
            }

            else if( ch == ')' ) {
                if( stack.size() == 0 ) return false;

                if( ch != ')' && stack.peek() == '(' )
                    return false;

                stack.pop();
            }
        }

        return stack.size() > 0 ? false : true;
    }

    /* stack 안 쓴 코드
    boolean solution(String s) {
        boolean answer = false;
        int count = 0;
        for(int i = 0; i<s.length();i++){
            if(s.charAt(i) == '('){
                count++;
            }
            if(s.charAt(i) == ')'){
                count--;
            }
            if(count < 0){
                break;
            }
        }
        if(count == 0){
            answer = true;
        }

        return answer;
    }
    */

    public static void main(String[] args) {
        //String s = "()()";
        //String s = "(())()";
        //String s = "())()(";
        String s = "(()(";

        System.out.println(solution(s));
    }
}
