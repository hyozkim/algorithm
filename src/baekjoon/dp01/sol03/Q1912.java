package baekjoon.dp01.sol03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * 연속합
 * DP 1 문제풀이 3
 */
public class Q1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = stoi(br.readLine());
        int[] a = new int[n];
        a = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();

        int[] d = new int[n];
        d[0] = a[0];
        for (int i = 1; i < n; i++) {
            d[i] = Math.max(a[i], d[i-1]+a[i]);
        }

        System.out.println(Arrays.stream(d).max().getAsInt());
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
