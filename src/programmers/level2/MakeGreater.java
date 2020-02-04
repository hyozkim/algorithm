package programmers.level2;

import java.util.Stack;

// Greedy - 큰 수 만들기
// Programmers Lv.2
public class MakeGreater {
    public static String solution(String number, int k) {
        char[] result = new char[number.length()-k];

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            while( !st.isEmpty() && st.peek() < c && k-- > 0 ) {
                st.pop();
            }
            st.push(c);
        }

        for (int i = 0; i < result.length; i++) {
            result[i] = st.get(i);
        }

        return new String(result);
    }

    public static void main(String[] args) {
        String number = "1924"; int k = 2;
        //String number = "1231234"; int k = 3;
        //String number = "4177252841"; int k = 4;

        System.out.println(solution(number,k));
    }

}
