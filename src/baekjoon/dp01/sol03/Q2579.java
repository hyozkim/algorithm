package baekjoon.dp01.sol03;

import java.util.Scanner;

/***
 * 계단 오르기
 * DP 1 문제풀이 3
 */
public class Q2579 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        int[][] d = new int[n+1][2];
        d[1][0] = a[1];
        for (int i = 2; i <= n; i++) {
            d[i][0] = Math.max(d[i-2][0], d[i-2][1]) + a[i]; // d[i][0] : 1칸 연속, 반드시 전전칸에서 올라와야 함
            d[i][1] = d[i-1][0] + a[i]; // d[i][1] : 2칸 연속, 반드시 전칸에서 올라와야함
        }

        System.out.println(Math.max(d[n][0], d[n][1]));
    }
}
