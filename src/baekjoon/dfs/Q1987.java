package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/***
 * 완전탐색 - 재귀호출
 *
 * 알파벳
 */
public class Q1987 {
    static class Point {
        int x;
        int y;
        int c;
        public Point(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer = 0;
    static int r, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        r = stoi(inputs[0]);
        c = stoi(inputs[1]);

        char[][] map = new char[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        boolean[] alphabet = new boolean[26];
        alphabet[map[0][0]-'A'] = true;
        dfs(map, alphabet, new Point(0,0,1));

        System.out.println(answer);
    }

    public static void dfs(char[][] map, boolean[] alphabet, Point point) {
        if( answer < point.c )
            answer = point.c;

        for (int k = 0; k < 4; k++) {
            int nx = point.x + dx[k];
            int ny = point.y + dy[k];
            int nc = point.c + 1;

            if( boundary(nx,ny) && !alphabet[map[nx][ny]-'A'] ) {
                alphabet[map[nx][ny]-'A'] = true;
                dfs(map, alphabet, new Point(nx,ny, nc));
                alphabet[map[nx][ny]-'A'] = false;
            }
        }
    }

    public static boolean boundary(int x, int y) {
        return x>=0 && x<r && y>=0 && y<c;
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
