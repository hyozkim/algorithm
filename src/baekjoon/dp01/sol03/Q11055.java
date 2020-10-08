package baekjoon.dp01.sol03;

import java.io.*;
import java.util.*;

/***
 * 가장 큰 증가하는 부분 수열
 * DP 1 문제풀이 3
 */
public class Q11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = stoi(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();

        int max = 0;
        int[] dp = new int[n];
        for (int i = 0; i < a.length; i++) {
            dp[i] = a[i];
            for (int j = 0; j < i; j++) {
                if( a[j] < a[i] ) {
                    dp[i] = Math.max(dp[i], dp[j] + a[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
