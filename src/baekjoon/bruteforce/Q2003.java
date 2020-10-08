package baekjoon.bruteforce;

import java.util.Scanner;

/***
 * 수들의 합2
 * 완전 탐색
 */
public class Q2003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int count = 0;
        int l = 0;
        int r = 0;
        while( l < n && r < n) {
            int sum = 0;
            for (int i = l; i <= r; i++) {
                sum += a[i];
            }

            if( sum > m ) {
                l++;
            } else if( sum == m ) {
                l++;
                count ++;
            } else if( sum < m ) {
                r++;
            }
        }

        System.out.println(count);
    }
}
