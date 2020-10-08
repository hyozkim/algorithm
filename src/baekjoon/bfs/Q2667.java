package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 단지내 전단지 붙이기
 * BFS
 */
public class Q2667 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(s -> stoi(s)).toArray();
        }

        solution(n,map);
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    private static void solution(int n, int[][] map) {
        List<Integer> numbers = new ArrayList<>();
        Queue<Point> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][n];
        int index = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;

                if( !visit[i][j] && map[i][j] != 0 ) {
                    q.add(new Point(i, j));
                    visit[i][j] = true;
                    count ++;

                    while( !q.isEmpty() ) {
                        Point now = q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = now.x + dx[k];
                            int ny = now.y + dy[k];

                            if( boundary(nx,ny,n) && !visit[nx][ny] && map[nx][ny] != 0 ) {
                                visit[nx][ny] = true;
                                map[nx][ny] = index;
                                q.add(new Point(nx,ny));
                                count ++;
                            }
                        }
                    }

                    index ++;
                    numbers.add(count);
                }
            }
        }

        Collections.sort(numbers);
        System.out.println(numbers.size());
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
    }

    private static boolean boundary(int x, int y, int n) {
        return x>=0 && x<n && y>=0 && y<n;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
