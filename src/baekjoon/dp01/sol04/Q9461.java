package baekjoon.dp01.sol04;

import java.util.Scanner;

/***
 * DP 1 문제풀이 4
 *
 * 파도반 수열
 */
public class Q9461 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while( t-- > 0 ) {
            int n = sc.nextInt();

            System.out.println(solution(n));
        }
    }

    public static long solution(int n) {
        long[] p = new long[101];
        p[1] = 1;
        p[2] = 1;
        p[3] = 1;
        p[4] = 2;
        p[5] = 2;

        for (int i = 6; i <= n; i++) {
            p[i] = p[i-1] + p[i-5];
        }

        return p[n];
    }
}
