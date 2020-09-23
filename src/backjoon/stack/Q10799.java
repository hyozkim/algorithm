package backjoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 쇠막대기 ( Q10799 )
// Backjoon - Stack
public class Q10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sticks = br.readLine();

        System.out.println(solution(sticks));
    }

    public static int solution(String s) {
        s = s.replace("()", "1");
        // System.out.println("after replace: " + s);

        int count = 0;
        Stack<Character> st = new Stack<>();
        for( char now : s.toCharArray() ) {
            if( now == '(' ) {
                st.push(now);
            } else if( now == '1' ) {
                count += st.size();
            } else if( now == ')' ) {
                count += 1;
                st.pop();
            }
        }

        return count;
    }
}
