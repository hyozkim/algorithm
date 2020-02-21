package programmers.level2;

import java.util.Stack;

// Stack - 짝지어 제거하기
// Programmers Lv.2
// Greedy 문제 같기도 함(프로그래머스 '큰 수 만들기'와 유사)
public class RemovePairString {

    public static int solution(String s)  {
        Stack<Character> st = new Stack<>();

        for( char ch : s.toCharArray() ) {
            if( !st.isEmpty() && ch == st.peek() )
                st.pop();
            else
                st.push(ch);
        }

        return st.isEmpty() ? 1 : 0;
    }

    public static void main(String[] args) {
        String s = "baabaa";
        //String s = "cdcd";
        //String s = "aabba";

        //System.out.println(s.substring(0,1) + s.substring(3,s.length()));
        System.out.println(solution(s));
    }
}
