package programmers.level3;

// DP - 등굣길
// Programmers Lv.3
public class WayToSchool {
    public static int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        dp[1][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                boolean onWater = false;
                for (int k = 0; k < puddles.length; k++) { // 물 웅덩이 검사
                    if( j == puddles[k][0] && i == puddles[k][1] ) {
                        onWater = true;
                    }
                }
                if( onWater ) dp[i][j] = 0;
                else    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000007;
            }
        }

        return dp[n][m] ;
    }

    /* 물 웅덩이(puddles) 위치를 처음에 -1로 초기화
     * 처음 풀었던 방법처럼 하면 매번 for문이 안에서 돌게 됨 -> 더 느림
    public static int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        dp[1][0] = 1;

        for( int[] v : puddles) {
            dp[v[1]][v[0]] = -1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if( dp[i][j] == -1 ) dp[i][j] = 0;
                else    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000007;
            }
        }

        return dp[n][m] ;
    }
     */

    public static void main(String[] args) {
        int m = 4; int n = 3; int[][] puddles = {{2,2},{2,4}};

        System.out.println(solution(m,n,puddles));
    }
}
