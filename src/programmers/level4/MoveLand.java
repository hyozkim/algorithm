package programmers.level4;

import java.util.*;

/***
 * Summer/Winter Coding(~2019)
 * 지형 이동
 * MST(Kruskal)
 *
 * 백준 17472번 다리만들기2 문제와 유사
 */
public class MoveLand {
    public static void main(String[] args) {
        int[][] land = {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}}; int height = 3;
        //int[][] land = {{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11},{ 2, 1, 2, 1 }}; int height = 1;

        System.out.println(solution(land, height));
    }

    static int n;
    static ArrayList<Edge> ladder;
    public static int solution(int[][] land, int height) {
        n = land.length;
        map = new int[n][n];
        visited = new boolean[n][n];

        // TODO Layer 만들기
        int num = makeIsland(land, height);
        // System.out.println(num);
        //print(map);

        // TODO min 비용 계산하기
        ladder = new ArrayList<>();
        makeLadder(land);
        Collections.sort(ladder);
        // for(Edge e : ladder) { System.out.println(e.s + " " + e.e + " " + e.c); }

        // TODO MST
        return mst(num);
    }

    private static int mst(int num) {
        int[] p = new int[num+1];
        for(int i=0; i<=num; i++) {
            p[i] = i;
        }

        int mst = 0;
        int cnt = 0;
        for(Edge e : ladder) {
            // System.out.println(e.s + " " + e.e + " " + e.c);
            int x = find(p, e.s);
            int y = find(p, e.e);

            if( x != y ) {
                union(p,x,y);
                mst += e.c;
                cnt ++;
            }

            if( cnt == num - 1 )
                break;
        }

        return mst;
    }

    private static void makeLadder(int[][] land) {
        int curr_num = map[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                curr_num = map[i][j];

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if( !boundary(nx,ny) )  continue;
                    if( map[nx][ny] == curr_num ) continue;

                    if( map[nx][ny] != curr_num ) {
                        ladder.add(new Edge(curr_num, map[nx][ny], Math.abs(land[nx][ny]-land[i][j]))); // s, e, cost
                        continue;
                    }
                }
            }
        }
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int [][] map;
    static boolean[][] visited;
    private static int makeIsland(int[][] land, int height) {
        int numbering = 1;

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if( map[i][j] == 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    map[i][j] = numbering;

                    Queue<Point> q = new LinkedList<>();
                    q.add(new Point(i,j));

                    while( !q.isEmpty() ) {
                        Point now = q.poll();

                        for(int k=0; k<4; k++) {
                            int nx = now.x + dx[k];
                            int ny = now.y + dy[k];

                            if( boundary(nx,ny) && !visited[nx][ny]
                                    && Math.abs(land[nx][ny] - land[now.x][now.y]) <= height ) {
                                map[nx][ny] = numbering;
                                visited[nx][ny] = true;
                                q.add(new Point(nx,ny) ) ;
                            }
                        }
                    }
                    numbering++;
                }
            }
        }

        return numbering-1;
    }

    static int find(int[] p, int a) {
        if( p[a] == a )
            return a;
        return p[a] = find(p,p[a]);
    }

    static void union(int[] p, int a, int b) {
        int x = find(p,a);
        int y = find(p,b);

        p[x] = y;
    }

    private static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean boundary(int x, int y) {
        return x>=0 && y>=0 && x<n && y<n;
    }

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int s;
        int e;
        int c;

        public Edge(int s,int e,int c) {
            this.s = s;
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.c - edge.c; // 오름차순
        }
    }
}
