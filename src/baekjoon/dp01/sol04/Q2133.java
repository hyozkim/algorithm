package baekjoon.dp01.sol04;

import java.util.Scanner;

/***
 * DP 1 문제풀이 4
 *
 * 타일 채우기 ( 3 x n 크기)
 */
public class Q2133 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if( n % 2 == 1 ) {
            System.out.println(0);
            return;
        }

        long[] a = new long[n+1];
        a[0] = 1; a[2] = 3;
        for (int i = 4; i <= n; i+=2) {
            a[i] += a[i-2]*3; // (기본) 다음 스텝은 3배
            for (int j = i-4; j >= 0; j--) {
                a[i] += 2 * a[j]; // 4개마다 2가지 방법이 추가된다.
            }
        }

        System.out.println(a[n]);
    }
}
