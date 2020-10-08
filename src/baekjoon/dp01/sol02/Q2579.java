package baekjoon.dp01.sol02;

import java.util.Scanner;

// 계단 오르기 ( Q2579 )
// Backjoon - Dp
public class Q2579 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        // 1. 한번에 한 계단, 두 계단씩
        // 2. 연속된 세 개의 계단을 모두 밟아선 안됨
        // 3. 마지막 도착 계단은 반드시 밟아야 한다
        // 점수 최댓값?

        /* 2차원 다이나믹 */
        int dp[][] = new int[n+1][3];
        dp[1][1] = a[1];
        // D[i][0] = 0개 연속 -> X

        // D[i][1] = 1개 연속, i-1번째 계단은 밟으면 안됨
        // Max(D[i-2][1], D[i-2][2]) + A[i]

        // D[i][2] = 2개 연속, i번째 계단은 밟아야 하고, 반드시 1개 연속해서 올라온 계단이어야 함
        // D[i-1][1] + A[i]
        for (int i = 2; i <= n; i++) {
            dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + a[i];
            dp[i][2] = dp[i-1][1] + a[i];
        }
        System.out.println(Math.max(dp[n][1], dp[n][2]));

        /* 1차원 다이나믹
        int []dp = new int[n+1];
        dp[1] = a[1];
        dp[2] = a[1]+a[2];
        // 1개 연속 -> dp[i-2] + a[i]
        // 2개 연속 -> dp[i-3] + a[i-1] + a[i]
        for (int i = 3; i <= n ; i++) {
            dp[i] = Math.max(dp[i-2] + a[i], dp[i-3] + a[i-1] + a[i]);
        }
        System.out.println(dp[n]);
        */
    }
}
