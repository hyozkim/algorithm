package baekjoon.dp02.sol02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * 파일합치기
 * 다이나믹 프로그래밍(DP)
 *
 * DP2 문제풀이 2 동영상
 */
public class Q11066 {

    static int[][] dp;
    static int[] s ;
    static int[] a;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = stoi(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = stoi(br.readLine());
            String[] nums = br.readLine().split(" ");

            a = new int[n+1];
            dp = new int[n+1][n+1];
            for (int j = 1; j <= n; j++) {
                Arrays.fill(dp[j],-1);
                a[j] = stoi(nums[j-1]);
            }

            System.out.println(go(1,n));
        }
    }

    private static int go(int i, int j) {
        if( i == j )
            return 0;

        if( dp[i][j] != -1 )
            return dp[i][j]; // memorization

        int ret = -1;
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += a[k];
        }
        // System.out.println("sum: " + sum);

        for (int k = i; k < j; k++) {
            int tmp = go(i,k) + go(k+1,j) + sum;
            if( ret == -1 || ret > tmp ) { // i~j번 파일을 합치는 최소 비용
                ret = tmp;
            }
        }
        dp[i][j] = ret;

        return ret;
    }
    
    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
