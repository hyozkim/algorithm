package baekjoon.dp;

import java.util.Scanner;

// 이동하기( Q11048 ) -: [프로그래머스] '가장 큰 정사각형 찾기' 문제 유사
// Backjoon - Dp
public class Q11048 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); int m = sc.nextInt();
        int[][] map = new int[n+1][m+1];
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int[][][] dp = new int[n+1][m+1][3];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j][0] = Math.max(dp[i][j-1][1], Math.max(dp[i-1][j-1][2], dp[i-1][j][0])) + map[i][j];
                dp[i][j][1] = Math.max(dp[i-1][j][0], Math.max(dp[i-1][j-1][2], dp[i][j-1][1])) + map[i][j];
                dp[i][j][2] = Math.max(dp[i-1][j][0], Math.max(dp[i-1][j-1][2], dp[i][j-1][1])) + map[i][j];
            }
        }

        // System.out.println(dp[n][m][0] + " " + dp[n][m][1] + " " + dp[n][m][2]);
        System.out.println(Math.max(Math.max(dp[n][m][0],dp[n][m][1]),dp[n][m][2]));
    }
}
