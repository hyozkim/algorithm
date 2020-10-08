package baekjoon.bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 괄호 추가하기
 */
public class Q16637 {
    static int n ;
    static char[] expression ;
    static int max_ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        expression = br.readLine().toCharArray();
        //  0 1 2 3
        // 3 8 7 9 2
        //  + * - *

        dfs(0,0);

        System.out.println(max_ans);
    }

    private static void dfs(int index, int sum) {
        if( index >= n ) {
            max_ans = Math.max(max_ans, sum);
            return;
        }
        char op = (index == 0) ? '+' : expression[index-1];

        if( index+2 < n ) {
            // 괄호 묶는다
            int bracket = calc(expression[index]-'0', expression[index+2]-'0', expression[index+1]);
            dfs(index+4, calc(sum, bracket, op));
        }
        // 괄호 안묶는다
        dfs(index+2, calc(sum, expression[index]-'0', op));
    }

    private static int calc(int num1, int num2, char op) {
        int ret = 0;

        if( op == '-' ) {
            ret = num1 - num2;
        } else if( op == '*' ) {
            ret = num1 * num2;
        } else if( op == '+' ) {
            ret = num1 + num2;
        }

        return ret;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
