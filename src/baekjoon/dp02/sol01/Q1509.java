package baekjoon.dp02.sol01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 팰린드롬 분할
 *
 * DP2 문제풀이 1
 */
public class Q1509 {
    static boolean[][] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        char[] charArray = input.toCharArray();
        int n = charArray.length;

        char[] a = new char[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = charArray[i-1];
        }

        d = new boolean[n+1][n+1];
        solve(a,n); // 문자열 팰린드럼? (boolean)

        // -----------------------------------------

        int[] dp = new int[n+1]; // i번째 문자열까지 팰린드럼 갯수
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = -1;
            for (int j = 1; j <= i; j++) {
                if( d[j][i] ) {
                    if( dp[i] == -1 || dp[i] > dp[j-1] + 1 ) {
                        dp[i] = dp[j-1] + 1; // 최소값
                    }
                }
            }
        }

        System.out.println(dp[n]);
    }

    public static void solve(char[] a, int n) {
        // 1. 1자리
        for (int i = 1; i <= n; i++)
            d[i][i] = true;

        // 2. 2자리
        for (int i = 1; i <= n-1; i++) {
            if( a[i] == a[i+1] ) {
                d[i][i + 1] = true;
            }
        }
        // 3. 3자리 이상
        for (int k = 3; k <= n; k++) {
            for (int i = 1; i <= n-k+1; i++) {
                int j = i+k-1;
                if( a[i] == a[j] && d[i+1][j-1] ) { // 문자열이 같고, 그 앞에 문자열 또한 같다면 (팰린드롬!)
                    d[i][j] = true;
                }
            }
        }
    }

}
