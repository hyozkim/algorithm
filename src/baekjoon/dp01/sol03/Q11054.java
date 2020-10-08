package baekjoon.dp01.sol03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * 가장 긴 바이토닉 부분 수열
 * DP 1 문제풀이 3
 */
public class Q11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = stoi(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();

        int[] d1 = new int[n];
        for (int i = 0; i < n; i++) {
            d1[i] = 1;
            for (int j = 0; j < i; j++) {
                if( a[j] < a[i] ) {
                    d1[i] = Math.max(d1[i], d1[j]+1);
                }
            }
        }

        int[] d2 = new int[n];
        for (int i = n-1; i >= 0; i--) {
            d2[i] = 1;
            for (int j = i+1; j < n; j++) {
                if( a[j] < a[i] ) {
                    d2[i] = Math.max(d2[i], d2[j]+1);
                }
            }
        }

        int max = d1[0]+d2[0]-1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, d1[i] + d2[i] -1);
        }

        System.out.println(max);
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}

