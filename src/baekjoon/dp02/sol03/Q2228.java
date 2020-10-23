package baekjoon.dp02.sol03;

import java.io.*;
import java.util.*;

/***
 * 구간 나누기
 * 다이나믹 프로그래밍
 *
 * DP2 문제풀이 3 동영상
 */
public class Q2228 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        int N = stoi(in[0]);
        int M = stoi(in[1]);

        int[] a = new int[N+1];
        int[] s = new int[N+1];
        for(int i=1; i<=N; i++) {
            a[i] = stoi(br.readLine());
            s[i] = s[i-1] + a[i]; // 아이디어 신박하네..
        }

        int[][] d = new int[N+1][M+1];
        boolean[][] v = new boolean[N+1][M+1];

        //System.out.println(N + " " + M);
        //System.out.println(Arrays.toString(a));
        System.out.println(go(a,s,d,v,N,M));
    }

    public static int min = -32786*101;
    public static int go(int[] a, int[] s, int[][] d, boolean[][] v, int n, int m) {
        if( m == 0 ) return 0;
        if( n <= 0 ) return min;
        if( v[n][m] ) return d[n][m];

        v[n][m] = true;
        d[n][m] = go(a, s, d, v,n-1, m); // 포함 x
        for(int i=1; i<=n; i++) {
            int temp = go(a, s, d, v,i-2, m-1) + s[n] - s[i-1]; // 포함 O
            d[n][m] = Math.max(d[n][m], temp);
        }

        return d[n][m];
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
