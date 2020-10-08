package baekjoon.dp01;

import java.util.Scanner;

/***
 * 평범한 배낭
 *
 * 다이나밍 프로그래밍(DP)
 */
public class Q12865 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] d = new int[n+1][k+1];
        int[] w = new int[n+1];
        int[] v = new int[n+1];
        for (int i = 1; i <= n; i++) {
            int weight = sc.nextInt();
            int value = sc.nextInt();

            w[i] = weight;
            v[i] = value;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                d[i][j] = d[i-1][j]; // 이전 물건 값의 합으로 설정
                if( j-w[i] >= 0 ) { // 무게에서 자신의 무게를 빼고, 남은 무게가 있을때
                    d[i][j] = Math.max(d[i-1][j], d[i-1][j-w[i]] + v[i]); // 이전 + 현재 가치
                }
            }
        }

        System.out.println(d[n][k]);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
