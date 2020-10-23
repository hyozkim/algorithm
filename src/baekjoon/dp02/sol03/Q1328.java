package baekjoon.dp02.sol03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * 고층 빌딩
 * 다이나믹 프로그래밍
 *
 * DP2 문제풀이 3 동영상
 */
public class Q1328 {
    static int MOD = 1000000007;
    static int N,L,R;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();

        N = a[0]; L = a[1]; R = a[2];

        long [][][] d = new long[N+1][L+1][R+1];
        d[1][1][1] = 1L;
        for(int i=2; i<=N; i++) {
            for (int j = 1; j <= L; j++) {
                for (int k = 1; k <= R; k++) {
                    // d[i-1][j-1][k] 가장 왼쪽에 빌딩 추가
                    // d[i-1][j][k-1] 가장 오른쪽에 빌딩 추가
                    // d[i-1][j][k] * (i-2) 중간에 빌딩 추가
                    d[i][j][k] = d[i-1][j-1][k] + d[i-1][j][k-1] + d[i-1][j][k] * (i-2);
                    d[i][j][k] %= MOD;
                }
            }
        }

        System.out.println(d[N][L][R]);
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
