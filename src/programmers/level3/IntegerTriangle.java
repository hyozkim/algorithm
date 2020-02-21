package programmers.level3;

import java.util.Arrays;

// DP - 정수 삼각형
// Programmers Lv.3
public class IntegerTriangle {
    public static int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            dp[i][i] = dp[i-1][i-1] + triangle[i][i];
            //System.out.println("[i] " + dp[i][0] + " " + dp[i][i]);
        }

        for(int i=2; i<n; i++) {
            for(int j=1; j<i; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }
        }

        /*
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if( answer < dp[n-1][i] )
                answer = dp[n-1][i];
        }

        return answer;
        */
        return Arrays.stream(dp[n-1]).max().getAsInt();
    }

    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

        System.out.println(solution(triangle));
    }
}
