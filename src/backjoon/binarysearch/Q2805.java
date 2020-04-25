package backjoon.binarysearch;

import java.util.*;

/* 나무 자르기 */
/* 이분탐색(YES/NO) */
public class Q2805 {
    /***
     *
     * @param a - 가지고 있는 나무 길이 배열
     * @param k - 자르고 남은 나무 길이
     * @param mid - 최대 나무 길이(기준값)
     * @return
     *
     * true  - 구하려는 k길이보다 나오는 나무 길이가 더 크다 -> mid(기준값) 최대 나무 길이를 늘려야 한다 -> l = mid + 1
     * false - 구하려는 k길이보다 나오는 나무 길이가 더 작다 -> mid(기준값) 최대 나무 길이를 줄여야 한다 -> r = mid - 1
     *
     * 주의: long 값으로 해서 풀어야 한다. (범위 설정)
     */
    private static boolean check(long[] a, int k, long mid) {
        long ret = 0;

        for (int i = 0; i < a.length; i++) {
            ret += (a[i] >= mid ? a[i] - mid : 0);
        }

        return ret >= k ? true : false;
    }

    public static long solution(long[] a, int k) {
        long answer = 0;

        Arrays.sort(a);

        long l = 1L;
        long r = a[a.length-1];
        while( l <= r ) {
            long mid = (l+r)/2;

            if( check(a,k,mid) ) { // mid = 절단할 나무의 높이
                answer = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }

        System.out.println(solution(a,k));

    }
}
