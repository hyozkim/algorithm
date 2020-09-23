package baekjoon.dp;

import java.util.Scanner;

// 스티커 ( Q9465 )
// Backjoon - Dp
public class Q9465 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            long[][] a = new long[n+1][2];
            for (int i = 1; i <= n; i++) {
                a[i][0] = sc.nextLong();
            }
            for (int i = 1; i <= n; i++) {
                a[i][1] = sc.nextLong();
            }

            long[][] dp = new long[n+1][3];
            // [0] -> 안찢음
            // [1] -> 윗변이 안찢김
            // [2] -> 아랫변이 안찢김
            for (int i = 1; i <= n; i++) {
                dp[i][0] = Math.max(Math.max(dp[i-1][0],dp[i-1][1]),dp[i-1][2]);
                dp[i][1] = Math.max(dp[i-1][0],dp[i-1][2]) + a[i][0];
                dp[i][2] = Math.max(dp[i-1][0],dp[i-1][1]) + a[i][1];
            }

            System.out.println(Math.max(Math.max(dp[n][0],dp[n][1]),dp[n][2]));
        }
    }
}
