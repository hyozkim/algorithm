package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/***
 * 파이프 옮기기 1
 * BFS 완전탐색 시간초과, DP 통과
 */
public class Q17070 {
    static class Pipe {
        int x;
        int y;
        int type; // 0: 가로, 1: 세로, 2: 대각선
        public Pipe(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());

        int[][] map = new int[n][n];
        int[][][] dp = new int[n][n][3];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();
            // System.out.println(Arrays.toString(map[i]));
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        //solution(n, map); // BFS
        System.out.println(solve(n, new Pipe(0,1,0), map, dp)); // DP
    }

    // 가로, 세로, 대각선
    static int[] dx = {0,1,1};
    static int[] dy = {1,0,1};

    /***
     * DP
     */
    private static int solve(int n, Pipe pipe, int[][] map, int[][][] dp) {
        int x = pipe.x;
        int y = pipe.y;
        int type = pipe.type;

        if( !possible(map,x,y,n,type) )
            return 0;
        if( x == n-1 && y == n-1 )
            return 1;
        if( dp[x][y][type] != -1 )
            return dp[x][y][type];

        dp[x][y][type] = 0;
        for (int k = 0; k < 3; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if( type == 0 && k == 1 ) {
                continue;
            } else if( type == 1 && k == 0) {
                continue;
            }

            dp[x][y][type] += solve(n, new Pipe(nx,ny,k), map, dp);
        }

        return dp[x][y][type];
    }

    /***
     * BFS 완전탐색
     */
    private static void solution(int n, int[][] map) {
        Queue<Pipe> q = new LinkedList<>();
        q.add(new Pipe(0,1,0)); // 가로

        int[][] dp = new int[n][n];

        while( !q.isEmpty() ) {
            Pipe pipe = q.poll();

            for (int k = 0; k < 3; k++) {
                int nx = pipe.x + dx[k];
                int ny = pipe.y + dy[k];

                if( pipe.type == 0 && k == 1 ) {
                    continue;
                } else if( pipe.type == 1 && k == 0) {
                    continue;
                }

                if( possible(map, nx, ny, n, k) ) {
                    q.add(new Pipe(nx, ny, k));
                    dp[nx][ny] += 1;
                }
            }
        }

        System.out.println(dp[n-1][n-1]);
    }

    private static boolean possible(int[][] map, int nx, int ny, int n, int k) {
        if( !boundary(nx,ny,n) ) {
            return false;
        }

        if( k == 2 ) {
            if( map[nx][ny] == 1 || map[nx-1][ny] == 1 || map[nx][ny-1] == 1 ) {
                return false;
            }
        } else {
            if( map[nx][ny] == 1 ) {
                return false;
            }
        }

        return true;
    }

    private static boolean boundary(int x, int y, int n) {
        return x>=0 && y>=0 && x<n && y<n;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
