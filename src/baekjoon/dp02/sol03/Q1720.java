package baekjoon.dp02.sol03;

import java.util.Scanner;

/***
 * 타일 코드
 * 다이나믹 프로그래밍
 *
 * DP2 문제풀이 3 동영상
 */
public class Q1720 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 대칭 O
        long[] a = new long[31];
        a[1] = 1L;
        a[2] = 3L;
        for(int i=3; i<=30; i++) {
            a[i] = a[i-1] + a[i-2]*2L;
        }

        // 대칭 X
        long[] d = new long[31];
        d[1] = 1L;
        d[2] = 3L;
        for(int i=3; i<=30; i++) {
            long b = 0L;
            if( i % 2 == 1 ) {
                b = a[(i-1)/2];
            } else {
                b = a[i/2] + (2*a[(i-2)/2]);
            }
            d[i] = (a[i]+b)/2;
        }

        System.out.println(d[N]);
    }
}
