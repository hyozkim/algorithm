package backjoon.dp;

import java.util.Scanner;

// 포도주 시식 ( Q2156 )
// Backjoon - Dp
public class Q2156 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        // 1. 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
        // 2. 연속으로 놓여 있는 3잔을 모두 마실 수는 없다.

        int [][] dp = new int[n+1][3];
        dp[1][1] = a[1];
        // d[i][0] = 0개 연속, i-1에 마시든 안마시든 상관 없음.(포도주 마시지 않음)
        // d[i][1] = 1개 연속, i-1에 마시지 않았어야함.(포도주 마심)
        // d[i][2] = 2개 연속, i-1에 무조건 마셨어야함.(포도주 마심)
        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(Math.max(dp[i-1][1], dp[i-1][2]),dp[i-1][0]);
            dp[i][1] = dp[i-1][0]+ a[i];
            dp[i][2] = dp[i-1][1] + a[i];
        }

        System.out.println(Math.max(Math.max(dp[n][1], dp[n][2]),dp[n][0]));
    }
}
