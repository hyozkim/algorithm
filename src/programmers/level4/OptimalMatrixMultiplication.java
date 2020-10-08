package programmers.level4;

/***
 * 최적의 행렬 곱셈 - Lv.4
 * 프로그래머스 연습문제
 */
public class OptimalMatrixMultiplication {
    public static int solution(int[][] matrix_sizes) {
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

        // TODO Bottom-Top 방식 - for문
        for (int c = 1; c < n; c++) {
            for (int i = 0; i < n-c; i++) {
                for (int j = i; j < i+c; j++) {
                    dp[i][i+c] = Math.min(dp[i][i+c], dp[i][j] + dp[j+1][i+c] + matrix_sizes[i][0] * matrix_sizes[j][1] * matrix_sizes[i+c][1]);
                }
            }
        }

        return dp[0][n-1];
    }

    public static void main(String[] args) {
        // (5*3*6):15x30, (5*3*6):30x
        int[][] matrix_sizes = {{5, 3},{3, 10},{10, 6}};

        System.out.println(solution(matrix_sizes));

    }
}
