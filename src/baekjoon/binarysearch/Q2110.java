package baekjoon.binarysearch;

import java.util.*;

/* 공유기 설치 */
/* YES/NO 문제 */
public class Q2110 {

    /***
     * check 함수
     *
     * @param a - 집 좌표 배열
     * @param m - 공유기 갯수
     * @param mid - 공유기 사이의 최대 거리
     * @return
     *
     * true (공유기 수가 기준보다 많다) -> 두 공유기 사이 거리를 늘려도 된다. -> l = mid + 1
     * false(공유기 수가 기준보다 적다) -> 두 공유기 사이 거리를 줄여야 한다. -> r = mid - 1
     */
    public static boolean check(int[] a, int m, int mid) {
        int cnt = 1;
        int last = a[0];
        for(int i=0; i<a.length; i++) {
            if( a[i] - last >= mid ) {
                cnt ++;
                last = a[i];
            }
        }

        return cnt >= m ? true : false;
    }

    public static void main(String[] args) {
        int ans = 1;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];

        for(int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }

        Arrays.sort(a);

        int l = 1;
        int r = a[n-1];
        while( l <= r ) {
            int mid = (l+r)/2;

            if( check(a,m,mid) ) {
                l = mid + 1;
                ans = mid;
            } else {
                r = mid - 1;

            }
        }

        System.out.println(ans);
    }

}
