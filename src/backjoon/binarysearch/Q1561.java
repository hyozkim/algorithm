package backjoon.binarysearch;

import java.util.Scanner;

/* 놀이공원(이분탐색) */
// 답을 보면 이해되지만
// 내가 처음부터 풀려고 하면 설계를 어떻게 해야할지 어떻게 접근해야하는지 조차 어렵다.
public class Q1561 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int [] a = new int[m];
        for(int i=0; i<m; i++) {
            a[i] = sc.nextInt();
        }

        if( n<=m ) {
            System.out.println(n);
            return;
        }

        long left = 0;
        long right = 2000000000L * 100000L;

        while( left <= right ) {
            long mid = (left+right)/2;

            long begin = 0, end = 0;
            end = m;
            for(int i=0; i<m; i++) {
                end += mid/a[i];
            }
            begin = end;
            for(int i=0; i<m; i++) {
                if( mid % a[i] == 0 ) {
                    begin -= 1;
                }
            }
            begin += 1;

            // 이분탐색
            if( n < begin ) {
                right = mid - 1;
            } else if( n > end ) {
                left = mid + 1;
            } else { // 이 경우는 어떤 경우?
                for(int i=0; i<m; i++) {
                    if( mid % a[i] == 0 ) {
                        if( n == begin ) {
                            System.out.println(i+1);
                            return;
                        }
                        begin += 1;
                    }

                }
            }
        }
    }

}
