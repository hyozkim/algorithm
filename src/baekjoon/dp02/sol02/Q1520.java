package baekjoon.dp02.sol02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 내리막길
 * 다이나믹 프로그래밍(DP)
 *
 * DP2 문제풀이 2 동영상
 */
public class Q1520 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int n, m;
    static int[][] map ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = stoi(input[0]); m = stoi(input[1]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = stoi(row[j]);
            }
        }

        //printMap();
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(go(dp,0,0));
    }

    private static int go(int[][] dp, int x, int y) {
        if( x == n-1 && y == m-1 )
            return 1;

        if( dp[x][y] >= 0 )
            return dp[x][y]; // memorization

        dp[x][y] = 0;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if( boundary(nx,ny) && map[nx][ny] < map[x][y] ) {
                dp[x][y] += go(dp,nx,ny);
            }
        }
        // print(dp);

        return dp[x][y];
    }

    private static void printMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void print(int[][] dp) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean boundary(int x, int y) {
        return x>=0 && y>=0 && x<n && y<m;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
