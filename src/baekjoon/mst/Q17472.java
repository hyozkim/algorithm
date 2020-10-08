package baekjoon.mst;

import java.util.*;

/***
 * 백준 다리 만들기 2
 *
 * MST(Minimum Spanning Tree)
 * Kruskal(크루스칼) Algorithm
 *
 * (2019 하반기 NHN 4번 문제 풀이 접근 방법 유사)
 */
public class Q17472 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int n;
    static int m;
    static int[][] map ;
    static ArrayList<Edge> bridge;

    static class Edge {
        int start;
        int end;
        int cost;
        public Edge() {
            this(0,0,0);
        }
        public Edge(int s,int e, int c) {
            this.start = s;
            this.end = e;
            this.cost = c;
        }

    }
    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean boundary(int x , int y) {
        return x>=0 && x<n && y>=0 && y<m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i][j] = sc.nextInt();
                //System.out.print(map[i][j]);
            }
            //System.out.println();
        }

        // 0. 섬만들기
        ArrayList<ArrayList<Point>> islands = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];

        int numbering = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if( map[i][j] != 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    map[i][j] = numbering;

                    Queue<Point> q = new LinkedList<>();
                    ArrayList<Point> island = new ArrayList<>();
                    q.add(new Point(i,j));
                    island.add(new Point(i,j));

                    while(!q.isEmpty()) {
                        Point now = q.remove();

                        for(int k=0; k<4; k++) {
                            int nx = now.x + dx[k];
                            int ny = now.y + dy[k];

                            if( boundary(nx,ny) && map[nx][ny] != 0 && !visited[nx][ny] ) {
                                map[nx][ny] = numbering;
                                visited[nx][ny] = true;
                                q.add(new Point(nx,ny) ) ;
                                island.add(new Point(nx,ny) ) ;
                            }
                        }
                    }
                    numbering++;
                    islands.add(island);
                }
            }
        }
		/* print
		System.out.println(islands.size());
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}*/

        // 1. 만들 수 있는 모든 경우의 수의 다리
        bridge = new ArrayList<>();
        for( ArrayList<Point> island : islands ) {
            getBridge(island);
        }

        bridge.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.cost-o2.cost;
            }
        });

        int mst = 0;
        // 2. MST
        int size = islands.size();
        int[] parent = new int[size+1];
        for(int i=0; i<=size; i++)
            parent[i] = i;

        int cnt = 0;
        for(Edge e : bridge) {
            int x = find(parent,e.start);
            int y = find(parent,e.end);

            if( x != y ) {
                union(parent,x,y);
                mst += e.cost;
                cnt ++;
            }

            if( cnt == size - 1 )
                break;
        }

        if( cnt != size - 1 )
            System.out.println("-1");
        else
            System.out.println(mst);
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

    public static void getBridge(ArrayList<Point> one) {
        // 기준
        int num = map[one.get(0).x][one.get(0).y];

        for(int i=0; i<one.size(); i++) {
            int r = one.get(i).x;
            int c = one.get(i).y;

            for(int k=0; k<4; k++) {
                int dist = 0;

                while( true ) {
                    r = r + dx[k];
                    c = c + dy[k];
                    dist ++;

                    if( !boundary(r,c) )	break;
                    if( map[r][c] == num )	break;

                    if( map[r][c] != 0 ) {
                        if( dist - 1 > 1 ) {
                            // System.out.println(num  + " " + map[r][c] + " " + (dist-1));
                            bridge.add(new Edge(num,map[r][c],dist-1));
                        }
                        break;
                    }
                }
            }
        }
    }
}

