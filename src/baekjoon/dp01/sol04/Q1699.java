package baekjoon.dp01.sol04;

import java.util.Scanner;

/***
 * DP1 문제풀이 4
 *
 * 제곱수의 합 - 2020 네이버 문제 3번과 유사
 */
public class Q1699 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // Bottom-Top 방식
        int[] d = new int[n+1];
        for (int i = 1; i <= n; i++) {
            d[i] = i;
            for (int j = 1; j*j <= i; j++) {
                d[i] = Math.min(d[i], d[i-j*j]+1); //
            }
        }
        System.out.println(d[n]);
    }
}
