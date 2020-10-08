package baekjoon.dp02.sol01;

import java.util.Scanner;

/***
 * 점프
 *
 * DP2 문제풀이 1
 */
public class Q1890 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] a = new int[n][n];
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
            // a[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();
            // System.out.println(Arrays.toString(a[i]));
        }

        d[0][0] = 1; // (n,n)까지 갈 수 있는 경우의 수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if( a[i][j] == 0 ) continue;

                if( i+a[i][j] < n ) {
                    d[i+a[i][j]][j] += d[i][j];
                }
                if( j+a[i][j] < n ) {
                    d[i][j+a[i][j]] += d[i][j];
                }
            }
        }

        System.out.println(d[n-1][n-1]);
    }

    private static int go(int[][] a, int[][] d, int i, int j, int n) {
        if( i == n && j == n )
            return 1;
        if( d[i][j] != 0 )
            return d[i][j]; // memorization

        int tmp = go(a,d,i+a[i][j], j, n) + go(a,d,i,j+a[i][j], n);
        if( d[i][j] == -1 || d[i][j] > tmp )
            d[i][j] = tmp;

        return d[i][j];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
