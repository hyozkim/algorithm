package baekjoon.dp02.sol02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * 행렬 곱셈 순서
 * 다이나믹 프로그래밍(DP)
 *
 * DP2 문제풀이 2 동영상
 */
public class Q11049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = stoi(br.readLine());
        int[][] a = new int[n][2];
        int[][] d = new int[n][n]; //  [i] ~ [j] 행렬 곱셈 연산 수의 최솟값
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            a[i][0] = stoi(input[0]); a[i][1] = stoi(input[1]);
            Arrays.fill(d[i], -1);
            // System.out.println(Arrays.toString(a[i]));
        }

        System.out.println(top_bottom(d, a, 0, n-1)); // TODO Top-Bottom
        // System.out.println(bottom_top(a)); // TODO Bottom-Top
    }

    // Bottom-Top 방식
    private static int bottom_top(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if( i == j )
                    dp[i][j] = 0;
                else
                    dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int c = 1; c < n; c++) {
            for (int i = 0; i < n-c; i++) {
                for (int j = i; j < i+c; j++) {
                    dp[i][i+c] = Math.min(dp[i][i+c], dp[i][j] + dp[j+1][i+c] + matrix_sizes[i][0] * matrix_sizes[j][1] * matrix_sizes[i+c][1]);
                }
            }
        }

        return dp[0][n-1];
    }

    // Top-Bottom 방식
    private static int top_bottom(int[][] d, int[][] a, int i, int j) {
        if (d[i][j] > 0)
            return d[i][j]; // memorization;
        if (i == j)
            return 0;

        int ret = -1;
        for (int k = i; k < j; k++) {
            int tmp = top_bottom(d, a, i, k) + top_bottom(d, a, k + 1, j) + (a[i][0] * a[k][1] * a[j][1]);
            if (ret == -1 || ret > tmp) {
                ret = tmp;
            }
        }

        d[i][j] = ret;
        return d[i][j];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
