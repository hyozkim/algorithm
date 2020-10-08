package programmers.level4;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 게임 맵 최단 거리 - Lv.4
 * 찾아라 프로그래밍 마에스터
 */
public class GameShortDistance {

    public static void main(String[] args)  {
        //int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}};

        System.out.println(solution(maps));
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        // print(maps);

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0, 1));

        boolean[][] visit = new boolean[n][m];
        visit[0][0] = true;

        while(!q.isEmpty()) {
            Point now = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = now.x + dx[k];
                int ny = now.y + dy[k];

                if (boundary(nx, ny, n, m) && !visit[nx][ny] && maps[nx][ny] == 1) {
                    visit[nx][ny] = true;
                    q.add(new Point(nx,ny,now.v+maps[nx][ny]));

                    dp[nx][ny] = (now.v + maps[nx][ny]);
                }
            }
        }

        // print(dp);
        return dp[n-1][m-1];
    }

    private static boolean boundary(int x, int y, int n, int m) {
        return x>=0 && y>=0 && x<n && y<m;
    }

    static class Point {
        int x;
        int y;
        int v;
        public Point(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }

    private static void print(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
