package baekjoon.bfs;

import java.io.*;
import java.util.*;

/***
 * 완전탐색 - Queue 사용하기
 *
 * 퍼즐
 */
public class Q1525 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int start = 0;
        for(int i=0; i<3; i++) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();
            for(int j=0; j<3; j++) {
                if( row[j] == 0 ) {
                    row[j] = 9;
                }
                start = start*10 + row[j];
            }
        }

        System.out.println(solve(start));
    }

    public static int solve(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(start, 0);

        while( !q.isEmpty() ) {
            int now = q.poll();
            String s = Integer.toString(now);
            int z = s.indexOf("9");
            int x = z/3;
            int y = z%3;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if( boundary(nx, ny) ) {
                    StringBuilder next = new StringBuilder(s);
                    // System.out.println(next.toString());

                    char temp = next.charAt(x*3+y);
                    next.setCharAt(x*3+y, next.charAt(nx*3+ny));
                    next.setCharAt(nx*3+ny, temp);

                    int num = stoi(next.toString());
                    if( !map.containsKey(num) ) {
                        map.put(num, map.get(now)+1);
                        q.add(num);
                    }
                }
            }
        }

        if( map.containsKey(123456789) )
            return map.get(123456789);
        else
            return -1;
    }

    public static boolean boundary(int x, int y) {
        return x>=0 && x<3 && y>=0 && y<3;
    }

    public static void print(int[][] a) {
        for(int i=0; i<3; i ++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
