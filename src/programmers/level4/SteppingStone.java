package programmers.level4;

import java.util.Arrays;

public class SteppingStone {
    /***
     * check 함수
     * @param a - 바위 좌표 배열
     * @param n - 제거해야할 바위 갯수
     * @param mid - 돌다리 사이 최소 거리
     * @param d - 다리 끝지점
     * @return
     *
     * true (제거되는 돌 갯수가 기준보다 많다) -> 두 돌다리 사이 거리 기준(mid)를 줄여야 된다. -> r = mid - 1
     * false(제거되는 돌 갯수가 기준보다 적다) -> 두 돌다리 사이 거리 기준(mid)을 늘려야 한다. -> l = mid + 1
     */
    private static boolean check(int[] a, int n, int mid, int d) {
        int cnt = 0;
        int last = 0;

        for (int i = 0; i < a.length; i++) {
            int gap =  i == a.length ? d - last : a[i] - last;
            if( gap < mid ) { // gap이 기준(mid) 최소거리가 작은 경우 제거 대상.
                cnt ++;
            } else {
                last = a[i];
            }
        }

        return cnt <= n ? false : true;
    }

    public static int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);
        int l = 1;
        int r = distance;
        while( l <= r ) {
            int mid = (l+r)/2;

            if( check(rocks,n,mid,distance) ) {
                r = mid - 1;
            } else {
                l = mid + 1;
                answer = mid;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int distance = 25; int[] rocks = {2,14,11,21,17}; int n = 2;

        System.out.println(solution(distance,rocks,n));
    }
}
