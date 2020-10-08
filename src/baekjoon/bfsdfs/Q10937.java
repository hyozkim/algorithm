package baekjoon.bfsdfs;

import java.util.*;

/***
 * 두부 모판 자르기
 * dfs 로 풀다가 gg..
 */
public class Q10937 {
    static int size ;
    static int answer = 0;

    private static void horizon(boolean[][] visit, int i, int j) {
        visit[i][j] = true;
        visit[i][j+1] = true;
    }
    private static void unhorizon(boolean[][] visit, int i, int j) {
        visit[i][j] = false;
        visit[i][j+1] = false;
    }

    private static void vertical(boolean[][] visit, int i, int j) {
        visit[i][j] = true;
        visit[i+1][j] = true;
    }
    private static void unvertical(boolean[][] visit, int i, int j) {
        visit[i][j] = false;
        visit[i+1][j] = false;
    }

    private static boolean boundary(int n, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    final static int[] AA = {'A','A'};
    final static int[] AB = {'A','B'};
    final static int[] BA = {'B','A'};
    final static int[] AC = {'A','C'};
    final static int[] CA = {'C','A'};

    final static int[] BB = {'B','B'};
    final static int[] BC = {'B','C'};
    final static int[] CB = {'C','B'};

    final static int[] CC = {'C','C'};


    private static int calc(char[][] a, int x, int y, int type) {
        int sum = 0;
        if( type == 0 ) { // 가로
            if( AA == new int[]{a[x][y], a[x][y+1]} ) {
                sum += 100;
            } else if( AB == new int[]{a[x][y], a[x][y+1]} || BA == new int[]{a[x][y], a[x][y+1]} ) {
                sum += 70;
            } else if( AC == new int[]{a[x][y], a[x][y+1]} || CA == new int[]{a[x][y], a[x][y+1]} ) {
                sum += 40;

            } else if( BB == new int[]{a[x][y], a[x][y+1]} ) {
                sum += 50;

            } else if( BC == new int[]{a[x][y], a[x][y+1]} || CB == new int[]{a[x][y], a[x][y+1]} ) {
                sum += 30;

            } else if( CC == new int[]{a[x][y], a[x][y+1]} ) {
                sum += 20;
            }
        }

        if( type == 1 ) { // 세로
            if( Arrays.equals(AA,new int[]{a[x][y], a[x+1][y]} ) ) {
                sum += 100;
            } else if( Arrays.equals(AB,new int[]{a[x][y], a[x+1][y]}) || Arrays.equals(BA, new int[]{a[x][y], a[x+1][y]}) ) {
                sum += 70;
            } else if( Arrays.equals(AC, new int[]{a[x][y], a[x+1][y]} ) || Arrays.equals(CA, new int[]{a[x][y], a[x+1][y]} ) ) {
                sum += 40;

            } else if( Arrays.equals(BB, new int[]{a[x][y], a[x+1][y]} ) ) {
                sum += 50;

            } else if( Arrays.equals(BC, new int[]{a[x][y], a[x+1][y]} ) || Arrays.equals(CB,new int[]{a[x][y], a[x+1][y]} ) ) {
                sum += 30;

            } else if( Arrays.equals(CC, new int[]{a[x][y], a[x+1][y]} ) ) {
                sum += 20;
            }
        }
        return sum;
    }

    private static void dfs(char[][] a, boolean[][] visit, int s, int sum) {
        if( s > size ) {
            answer = Math.max(sum,answer);
            return ;
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if( !visit[i][j] ) {
                    visit[i][j] = true;

                    if( boundary(a.length, i, j+1) ) { // 가로
                        // horizon(visit, i, j);
                        visit[i][j+1] = true;
                        sum += calc(a,i,j,0);
                        dfs(a, visit, s+2, sum);
                        visit[i][j+1] = false;
                        sum -= calc(a,i,j,0);
                    }

                    if( boundary(a.length, i+1, j) ) { // 세로
                        //vertical(visit, i, j);
                        visit[i+1][j] = true;
                        sum += calc(a,i,j,1);
                        dfs(a, visit, s+2, sum);
                        visit[i+1][j] = false;
                        sum -= calc(a,i,j,1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        //System.out.println(n);

        char[][] a = new char[n][n];
        for (int i = 0; i < n; i++) {
            String input = sc.next();
            a[i] = input.toCharArray();
            //System.out.println(Arrays.toString(a[i]));
        }

        size = n*n;
        boolean[][] visit = new boolean[n][n];

        dfs(a,visit, 0, 0);

        System.out.println(answer)  ;
    }
}
