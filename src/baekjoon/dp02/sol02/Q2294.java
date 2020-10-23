package baekjoon.dp02.sol02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 동전 2
 * 다이나믹 프로그래밍
 *
 * DP2 문제풀이 2 동영상
 */
public class Q2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = stoi(input[0]); int k = stoi(input[1]);

        int[] a = new int[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = stoi(br.readLine());
        }

        int[] d = new int[k+1];
        for (int i = 1; i <= k; i++) {
            d[i] = 100001;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = a[i]; j <= k; j++) {
                d[j] = Math.min(d[j], d[j-a[i]]+1);
            }
        }

        if( d[k] == 100001 )
            System.out.println("-1");
        else
            System.out.println(d[k]);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
