package baekjoon.dp01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 1+2+3 더하기 4
 * 다이나믹 프로그래밍(DP)
 */
public class Q15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = stoi(br.readLine());
        while( t-- > 0 ) {
            int n = stoi(br.readLine());

            int[] dp = new int[n+1];
            for (int i = 1; i <= n; i++) {
                dp[i] = 0;
            }

            dp[0] = 1;
            for (int k = 1; k <= 3; k++) {
                for (int i = k; i <= n; i++) {
                    dp[i] += dp[i-k];
                }
            }

            System.out.println(dp[n]);
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
