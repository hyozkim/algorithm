package programmers.level3;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 경주로 건설
 * 2020 카카오 인턴십
 */
public class ConstructRoad {
    public static void main(String[] args) {
        //int[][] board = {{0, 0, 0},{0, 0, 0},{0, 0, 0}};
        int[][] board = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};

        System.out.println(solution(board));
    }

    // 위, 아래, 왼, 오른
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public static int solution(int[][] board) {
        int min = Integer.MAX_VALUE;
        int n = board.length;

        int[][] map = new int[n][n];

        Queue<Point> q = new LinkedList<>();
        board[0][0] = 1;
        q.add(new Point(0,0, 0, -1));

        while( !q.isEmpty() ) {
            Point point = q.poll();

            if( point.x == n-1 && point.y == n-1 ) {
                min = Math.min(point.cost, min);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                int cost = point.cost;
                int dir = point.dir;

                if( boundary(nx,ny,n) && board[nx][ny] != 1 ) {
                    int newCost = plus(cost,i,dir);

                    if( map[nx][ny] == 0 ) {
                        map[nx][ny] = newCost;
                        q.add(new Point(nx,ny, newCost, i));

                    } else if( newCost <= map[nx][ny] ) {
                        map[nx][ny] = newCost;
                        q.add(new Point(nx,ny, newCost, i));

                    }
                }
            }
        }

        return min;
    }

    private static int plus(int cost, int curr, int prev) {
        int temp = cost;

        if( !(prev == -1 || prev == curr) ) {
            temp += 500;
        }
        temp += 100;

        return temp;
    }

    private static boolean boundary(int x, int y, int n) {
        return x >= 0 && y>= 0 && x < n && y < n;
    }

    static class Point {
        int x;
        int y;
        int cost;
        int dir;
        public Point(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }

    }

}
