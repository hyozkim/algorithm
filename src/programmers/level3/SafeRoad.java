package programmers.level3;

/***
 * 보행자 천국 - Lv.3
 * 카카오코드 예선
 */
public class SafeRoad {
    static final int MOD = 20170805;
    public static int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m+1][n+1][2];
        if( cityMap[0][0] == 2 ) {
            dp[0][1][0] = 1;
        } else {
            dp[0][1][0] = 1;
            dp[0][1][1] = 1;
        }

        // dp[m][n][0] : 오른쪽 방향
        // dp[m][n][1] : 아래쪽 방향
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if( cityMap[i-1][j-1] == 1 ) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                } else if( cityMap[i-1][j-1] == 2 ) {
                    dp[i][j][0] = dp[i][j-1][0];
                    dp[i][j][1] = dp[i-1][j][1];

                } else {
                    dp[i][j][0] = (dp[i-1][j][1] + dp[i][j-1][0]) % MOD;
                    dp[i][j][1] = (dp[i-1][j][1] + dp[i][j-1][0]) % MOD;
                }
            }
        }

        return dp[m][n][1];
    }

    public static void main(String[] args) {
        //int m = 3; int n = 3; int[][] cityMap = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int m = 3; int n = 6; int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};

        System.out.println(solution(m,n,cityMap));
    }

}
