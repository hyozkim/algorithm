package swsamsung.d4;

import java.io.*;
import java.util.*;

public class P1767 {

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // direction 0: North, 1: South, 2: East, 3: West
    private static void build(int[][] cores, List<Point> points, int index, int direction) {
        int m = cores.length;
        int x = points.get(index).x;
        int y = points.get(index).y;

        if( direction == 0 ) {
            while( --x >= 0 ) {
                cores[x][y] = -1;
            }
        } else if( direction == 1 ) {
            while( ++x < m ) {
                cores[x][y] = -1;
            }
        } else if( direction == 2 ) {
            while( ++y < m ) {
                cores[x][y] = -1;
            }
        } else if( direction == 3 ) {
            while( --y >= 0 ) {
                cores[x][y] = -1;
            }
        }
    }

    private static void remove(int[][] cores, List<Point> points, int index, int direction) {
        int m = cores.length;
        int x = points.get(index).x;
        int y = points.get(index).y;

        if( direction == 0 ) {
            while( --x >= 0 ) {
                cores[x][y] = 0;
            }
        } else if( direction == 1 ) {
            while( ++x < m ) {
                cores[x][y] = 0;
            }
        } else if( direction == 2 ) {
            while( ++y < m ) {
                cores[x][y] = 0;
            }
        } else if( direction == 3 ) {
            while( --y >= 0 ) {
                cores[x][y] = 0;
            }
        }
    }

    private static boolean isPossible(int[][] cores, List<Point> points, int index, int direction) {
        int m = cores.length;

        int x = points.get(index).x;
        int y = points.get(index).y;

        if( direction == 0 ) {
            while( --x >= 0 ) {
                if( cores[x][y] != 0 )
                    return false;
            }
        } else if( direction == 1 ) {
            while( ++x < m ) {
                if( cores[x][y] != 0 )
                    return false;
            }
        } else if( direction == 2 ) {
            while( ++y < m ) {
                if( cores[x][y] != 0 )
                    return false;
            }
        } else if( direction == 3 ) {
            while( --y >= 0 ) {
                if( cores[x][y] != 0 )
                    return false;
            }
        }

        return true;
    }

    private static void dfs(int m, List<Point> points, int[][] cores, int index, int core_cnt, int line) {
        if( index == points.size() ) {
            if( core_cnt == index ) { // 코어수가 같으면 최소값
                min = Math.min(min, line);
            }
            return;
        }

        if (isPossible(cores, points, index, 0)) {
            build(cores, points, index, 0);
            dfs(m, points, cores, index + 1, core_cnt + 1, points.get(index).x + line);
            remove(cores, points, index, 0);

        }
        if (isPossible(cores, points, index, 1)) {
            build(cores, points, index, 1);
            dfs(m, points, cores, index + 1, core_cnt + 1, (m - points.get(index).x) + line);
            remove(cores, points, index, 1);

        }
        if (isPossible(cores, points, index, 2)) {
            build(cores, points, index, 2);
            dfs(m, points, cores, index + 1, core_cnt + 1, (m - points.get(index).y) + line);
            remove(cores, points, index, 2);

        }
        if (isPossible(cores, points, index, 3)) {
            build(cores, points, index, 3);
            dfs(m, points, cores, index + 1, core_cnt + 1, points.get(index).y + line);
            remove(cores, points, index, 3);

        }

    }

    private static boolean boundary(int m, int[][] cores, int x, int y, int direction) {
        if( direction == 0 ) {
            while( --x >= 0 ) {
                if( cores[x][y] != 0 )
                    return false;
            }
        } else if( direction == 1 ) {
            while( ++x < m ) {
                if( cores[x][y] != 0 )
                    return false;
            }
        } else if( direction == 2 ) {
            while( ++y < m ) {
                if( cores[x][y] != 0 )
                    return false;
            }
        } else if( direction == 3 ) {
            while( --y >= 0 ) {
                if( cores[x][y] != 0 )
                    return false;
            }
        }

        return true;
    }

    public static int solution(int m, int[][] cores) {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < cores.length; i++) {
            for (int j = 0; j < cores[i].length; j++) {
                if( cores[i][j] == 1 ) {
                    if( !boundary(m, cores, i, j, 0) && !boundary(m, cores, i, j, 1)
                            && !boundary(m, cores, i, j, 2) && !boundary(m, cores, i, j, 3) ) { // 4방향이 막힌 경우, 제거!
                        cores[i][j] = 0;
                        continue;
                    }
                    points.add(new Point(i,j));
                }
            }
        }

        min = Integer.MAX_VALUE;
        dfs(m-1, points, cores,0, 0, 0);

        return min;
    }

    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {

            int m = Integer.parseInt(br.readLine());

            int[][] cores = new int[m][m];
            for (int j = 0; j < m; j++) {
                cores[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
            }

            System.out.println("#"+(i) + " " + solution(m, cores));
        }
    }
}
