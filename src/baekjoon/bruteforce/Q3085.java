package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/***
 * 사탕게임
 * 브루트포스
 */
public class Q3085 {
    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());

        String[][] map = new String[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().split("");
            // System.out.println(Arrays.toString(map[i]));
        }

        solution(n,map);
    }


    private static void solution(int n, String[][] map) {
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = i+dx[k];
                    int ny = j+dy[k];

                    if( boundary(nx,ny,n) ) {
                        swap(map,i,j,nx,ny);
                        max = Math.max(max, calc(map,i,j,n));
                        swap(map,i,j,nx,ny);
                    }
                }
            }
        }

        System.out.println(max);
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    private static int calc(String[][] map, int i, int j, int n) {
        Queue<Point> row = new LinkedList<>();
        boolean[][] visit_row = new boolean[n][n];
        int count_row = 1;

        row.add(new Point(i,j));
        visit_row[i][j] = true;

        Queue<Point> col = new LinkedList<>();
        boolean[][] visit_col = new boolean[n][n];
        int count_col = 1;

        col.add(new Point(i,j));
        visit_col[i][j] = true;

        while( !col.isEmpty() || !row.isEmpty() ) {
            if( !row.isEmpty() ) {
                Point now_row = row.poll();

                for (int k = 2; k <= 3; k++) { // 왼, 오른
                    int nx = now_row.x + dx[k];
                    int ny = now_row.y + dy[k];

                    if( boundary(nx,ny,n) && !visit_row[nx][ny]
                            && map[now_row.x][now_row.y].equals(map[nx][ny]) ) {
                        visit_row[nx][ny] = true;
                        row.add(new Point(nx,ny));
                        count_row += 1;
                    }
                }
            }

            if( !col.isEmpty() ) {
                Point now_col = col.poll();

                for (int k = 0; k <= 1; k++) { // 위, 아래
                    int nx = now_col.x + dx[k];
                    int ny = now_col.y + dy[k];

                    if( boundary(nx,ny,n) && !visit_col[nx][ny]
                            && map[now_col.x][now_col.y].equals(map[nx][ny]) ) {
                        visit_col[nx][ny] = true;
                        col.add(new Point(nx,ny));
                        count_col += 1;
                    }
                }
            }
        }

        return Math.max(count_row, count_col);
    }

    private static void swap(String[][] map, int x, int y, int nx, int ny) {
        String tmp = map[x][y];
        map[x][y] = map[nx][ny];
        map[nx][ny] = tmp;
    }

    private static boolean boundary(int x, int y, int n) {
        return x>=0 && y>=0 && x<n && y<n;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
