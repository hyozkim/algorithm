package baekjoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 스카이라인 쉬운거
 */
public class Q1863 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String stringOfNumber = br.readLine();

        int N = stoi(stringOfNumber);
        int[] arr = new int[50002];
        for (int i = 0; i < N; i++) {
            String[] points = br.readLine().split(" ");
            int _x = stoi(points[0]);
            int _y = stoi(points[1]);

            arr[i] = _y;
            // System.out.println(_x + " " + _y);
        }

        int count = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i <= N; i++) {
            while( !st.empty() && st.peek() > arr[i] ) {
                st.pop();
                count ++;
            }
            if( !st.empty() && st.peek() == arr[i] )
                continue;

            // System.out.println(arr[i]);
            st.push(arr[i]);
        }

        System.out.println(count);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
