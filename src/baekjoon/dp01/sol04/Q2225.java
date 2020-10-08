package baekjoon.dp01.sol04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * DP 1 문제풀이 4
 *
 * 합분해
 */
public class Q2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int N = stoi(inputs[0]); int K = stoi(inputs[1]);

        System.out.println(solution(N,K));
    }

    public static long solution(int N, int K) {
        long[][] dp = new long[K+1][N+1];
        dp[0][0] = 1L; // 0과 N 값은 default로 1개

        // Bottom-Top 방식
        for (int i = 1; i <= K; i++) { // 사용하는 정수 - 갯수(count)
            for (int j = 0; j <= N; j++) { // 합해서 되는 결과값(sum)
                for (int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i-1][j-k];
                    dp[i][j] %= 1000000000;
                }
            }
        }

        return dp[K][N] % 1000000000;
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
