package baekjoon.binarysearch;

import java.util.*;

/* 랜선 자르기 */
/* 이분탐색(YES/NO) */
public class Q1654 {
    /***
     *
     * @param a - 가지고 있는 랜선 배열
     * @param k - 랜선 갯수
     * @param mid - 최대 랜선 길이(기준값)
     * @return
     *
     * true  - 구하려는 k개 보다 나오는 랜선 갯수가 크다 -> mid(기준값) 최대 랜선 길이를 늘려야 한다(분모) -> l = mid + 1
     * false - 구하려는 k개 보다 나오는 랜선 갯수가 작다 -> mid(기준값) 최대 랜선 길이를 줄여야 한다(분모) -> r = mid - 1
     *
     * 주의: long 값으로 해서 풀어야 한다. (범위 설정)
     */
    private static boolean check(long[] a, int k, long mid) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            cnt += a[i]/mid;
        }

        return cnt >= k ? true : false;
    }

    public static long solution(long[] a, int k) {
        long answer = 0;

        Arrays.sort(a);

        long l = 1;
        long r = a[a.length-1];
        while( l <= r ) {
            long mid = (l+r)/2;

            if( check(a,k,mid) ) { // mid = 랜선 길이
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

        //System.out.println(n + " " + k);
        //System.out.println(Arrays.toString(a));

        System.out.println(solution(a,k));

    }
}
